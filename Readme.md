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


#### Run MySQL container
   docker run --name some-mysql \
   -p 3306:3306 \
   -e MYSQL_ROOT_PASSWORD=my-secret-pw \
   -d mysql:latest