| Task                           | Command                            |
|--------------------------------| ---------------------------------- |
| Run the app during development | `./mvnw spring-boot:run`           |
| Build JAR for deployment       | `./mvnw clean package`             |
| Skip tests during build        | `./mvnw clean package -DskipTests` |
| Clean & build project          | `./mvnw clean install`             |
| Check dependencies             | `./mvnw dependency:tree`           |

## Dependency Injection in Action

### Repository Injection

```
@Service
public class EmployeeService {
EmployeeRpository emprepo ;
    public EmployeeService(EmployeeRpository emprepo){
        this.emprepo = emprepo;
    }
}
```


> EmployeeService depends on EmployeeRepository.

> Spring sees the @Service annotation and creates a bean for EmployeeService.

> When constructing it, Spring injects (@Autowired implicitly) the EmployeeRepository bean.

> You didn’t write new EmployeeRepository(). Spring handles that.


### Service Injection

```
@RestController
@RequestMapping("/api/employee")
public class EmployeeRessource {
    EmployeeService serv;

    public EmployeeRessource(EmployeeService serv ){
        this.serv = serv;
    }
}
```


> EmployeeRessource depends on EmployeeService.

> Spring sees @RestController, creates the bean, and injects the EmployeeService bean automatically.

### L'injection dans le Service/Controller

Quand tu écris ton constructeur avec une interface :

```
@RestController
@RequestMapping("/api/bank")
public class BankResource {

    private final IBank bank; // dépend de l'interface

    public BankResource(IBank bank) { // Spring injecte une implémentation
        this.bank = bank;
    }

    @PostMapping("/transfer")
    public boolean transfer(@RequestParam String from,
                            @RequestParam String to,
                            @RequestParam double amount) {
        return bank.transfert(from, to, amount); // appel de l'implémentation réelle
    }
}

```


Ici bank est de type IBank (interface).

Mais Spring se dit : “Ok, dans mon conteneur, j’ai un bean qui implémente IBank → c’est BankService. Donc je vais injecter un objet de type BankService dans ce champ.”

Donc quand tu appelles bank.transfert(...), en réalité c’est la méthode de BankService qui s’exécute.

### Comment Spring choisit l’implémentation ?

* Spring scanne le projet.

* Il voit BankService annoté avec @Service.

* Il remarque que BankService implémente IBank.

* Quand une classe demande un IBank, Spring donne l’objet BankService.

⚠️ S’il y a plusieurs implémentations d’IBank (par ex. BankService, FakeBankService), Spring a un doute → il faut préciser :

* soit avec @Primary (l’implémentation par défaut).

* soit avec @Qualifier("nomDuBean") dans le constructeur.
 

### @Query : écrire la requête JPQL ou SQL

#### Tu peux écrire directement une requête avec l’annotation @Query.

```angular2html
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.stock < :threshold AND a.price > :minPrice")
    List<Article> findLowStockExpensiveArticles(@Param("threshold") int threshold,
                                                @Param("minPrice") double minPrice);
}
```

* Ici, Article est ton Entity et on utilise JPQL (pas SQL pur).

* Spring crée l’implémentation automatiquement.

#### Requêtes natives SQL
Si tu veux du vrai SQL (pas JPQL), tu peux faire :

```angular2html
@Query(value = "SELECT * FROM article WHERE stock < ?1 AND price > ?2", nativeQuery = true)
List<Article> findLowStockExpensiveArticlesNative(int threshold, double minPrice);
```
* nativeQuery = true → Spring exécute exactement le SQL que tu écris.

### Run MySQL container
```
   docker run --name some-mysql \
   -p 3306:3306 \
   -e MYSQL_ROOT_PASSWORD=my-secret-pw \
   -d mysql:latest
```

## Documentation : Spring AOP
### Introduction

La programmation orientée aspect (AOP) est un paradigme qui permet de séparer les préoccupations transversales (logging, sécurité, transactions, etc.) de la logique métier principale.
Au lieu d’ajouter du code de logging ou de sécurité dans chaque méthode, on définit ces comportements dans des aspects, et Spring s’occupe de les appliquer automatiquement.

### Concepts clés

*  Aspect → Module qui contient du code transversal (logging, sécurité, transactions).
→ Défini par une classe annotée @Aspect.

* Join point → Point d’exécution du programme où un aspect peut intervenir.
→ Exemple : l’appel d’une méthode publique dans un service.

* Pointcut → Expression qui définit où (quels join points) un aspect doit s’appliquer.
→ Exemple : execution(* com.example.magasin.service..*(..)) cible toutes les méthodes publiques des services.

* Advice (Greffon) → Le code à exécuter avant, après, ou autour du join point.

* * @Before → exécuté avant la méthode cible

* * @AfterReturning → exécuté après la méthode, si elle réussit

* * @AfterThrowing → exécuté si une exception est lancée

* * @Around → entoure l’appel, permet de bloquer ou modifier l’exécution

* Weaving (Tissage) → Processus qui insère les aspects dans le code cible. Avec Spring, cela se fait dynamiquement au runtime via des proxies.

* Cross-cutting concerns (Préoccupations transversales) → Fonctions communes à plusieurs modules qui ne concernent pas directement la logique métier.
→ Exemple : sécurité, journalisation, gestion des transactions.

#### Exemple d’Aspect 1 : Logging
````
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public * com.example.magasin.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("TRACE -> Classe: " + className + ", Méthode: " + methodName);
    }
}
````

Cet aspect trace toutes les méthodes publiques des services avant leur exécution.

#### Exemple d’Aspect 2 : Sécurité
````
@Aspect
@Component
public class SecurityAspect {

    @Around("execution(* com.example.magasin.service.StoreService.*(..))")
    public Object checkSecurity(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("SECURITY -> Vérification des droits pour: " + pjp.getSignature().getName());

        boolean authorized = true; // simulation d'une règle de sécurité
        if (!authorized) {
            throw new RuntimeException("Accès refusé !");
        }

        return pjp.proceed(); // exécute la méthode si autorisé
    }
}
````

Cet aspect intercepte toutes les méthodes de StoreService et applique un contrôle de sécurité avant leur exécution.

#### Bénéfices de Spring AOP

Séparation des responsabilités → la logique métier reste claire, les préoccupations techniques sont externalisées.

Maintenabilité → un seul aspect gère un comportement global (ex : logs), pas besoin de répéter du code.

Réutilisabilité → un aspect peut être appliqué à plusieurs beans sans modification.

Flexibilité → on peut ajouter ou modifier un aspect sans toucher au code métier.

#### Schéma récapitulatif
````
+-----------------+       +-------------------+
|     Service     | <---> |   Repository JPA  |
+-----------------+       +-------------------+
|
V
[ Join point ]
|
+-----------------+
|     Aspect      |
| (Log / Security)|
+-----------------+
````

Avec AOP, ton code métier (banque, magasin, fournisseur, client) reste concentré sur les scénarios tandis que les aspects (logging, sécurité) sont ajoutés automatiquement par Spring.