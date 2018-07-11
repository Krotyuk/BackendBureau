package ftc.shift.sample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;


@SpringBootApplication
public class SpringbootSampleApplication {


    @PostConstruct
    void started() {
        //String timezone = (ZoneId.systemDefault()).toString();
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.systemDefault()));

    }



    public static void main(String[] args) {

        SpringApplication.run(SpringbootSampleApplication.class, args);
    }
}
