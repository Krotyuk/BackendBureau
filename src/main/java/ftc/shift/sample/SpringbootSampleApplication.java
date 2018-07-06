package ftc.shift.sample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ftc.shift.sample.repositories.InMemoryTaskRepository;


@SpringBootApplication
public class SpringbootSampleApplication {

    static InMemoryTaskRepository inMemory = new InMemoryTaskRepository();


    public static void main(String[] args) {

        SpringApplication.run(SpringbootSampleApplication.class, args);
    }
}
