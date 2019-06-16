package com.uNow;

import com.uNow.entities.*;
import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.FriendRequestRepository;
import com.uNow.repositories.FriendShipRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class UNowApplication {

    public static void main(String[] args) {
        SpringApplication.run(UNowApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository, ActivityRepository activityRepository, FriendShipRepository friendShipRepository, FriendRequestRepository friendRequestRepository) {
        return args -> {
            userRepository.save(new User("Jonathan", "Wadin", "wadin.jonathan@gmail.com", "azerty", "7 rue du Levrier", "0000000000"));
            userRepository.save(new User("Arnaud", "Bascop", "nonoDu59@gmail.com", "azerty", "52 rua de Heroismo", "0605436459"));
            userRepository.save(new User("Manon", "Baudry", "man.baudry@gmail.com", "azerty", "52 rua de Heroismo", "0679065306"));
            userRepository.save(new User("Jackie", "Kennedy", "ccvolvic@hotmail.com", "azerty", "Saint Amand", "0631440224"));

            activityRepository.save(new Activity(userRepository.findById(1L), ActivityType.WORK, new Date(), userRepository.findById(1L).getLocation(), 0));
            activityRepository.save(new Activity(userRepository.findById(2L), ActivityType.SPORT, new Date(), userRepository.findById(2L).getLocation(), 0));
            activityRepository.save(new Activity(userRepository.findById(3L), ActivityType.SPORT, new Date(), userRepository.findById(3L).getLocation(), 0));

            friendShipRepository.save(new FriendShip(userRepository.findById(1L), userRepository.findById(2L)));
            friendShipRepository.save(new FriendShip(userRepository.findById(3L), userRepository.findById(1L)));
            friendShipRepository.save(new FriendShip(userRepository.findById(3L), userRepository.findById(4L)));

            friendRequestRepository.save(new FriendRequest(userRepository.findById(1L), userRepository.findById(4L)));

        };
    }


}
