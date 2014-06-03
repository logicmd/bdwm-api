package us.hk.bdwm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] {
                "resources/applicationContext.xml"
            });
        ((AbstractApplicationContext) context).registerShutdownHook();
        SpringApplication.run(Application.class, args);
    }
}
