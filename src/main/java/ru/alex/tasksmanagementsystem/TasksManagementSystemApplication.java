package ru.alex.tasksmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.alex.tasksmanagementsystem.configuration.AppSpringConfiguration;


@SpringBootApplication
@Import({AppSpringConfiguration.class})
@EnableJpaAuditing(auditorAwareRef = "auditDateAware")
public class TasksManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksManagementSystemApplication.class, args);
    }

}
