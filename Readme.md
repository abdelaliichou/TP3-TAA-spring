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