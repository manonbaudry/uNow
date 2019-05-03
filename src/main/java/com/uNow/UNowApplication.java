package com.uNow;

import com.uNow.entities.User;
import com.uNow.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class UNowApplication {

    public static void main(String[] args) {
        SpringApplication.run(UNowApplication.class, args);
    }


    @Bean
    CommandLineRunner init (UserRepository userRepository){
        return args -> {
            userRepository.save(new User("Jonathan", "Wadin", "wadin.jonathan@gmail.com", "azerty", "7 rue du Levrier", "0000000000", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userRepository.save(new User("Arnaud", "Bascop", "nonoDu59@gmail.com", "azerty", "52 rua de Heroismo", "0605436459", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userRepository.save(new User("Manon", "Baudry", "man.baudry@gmail.com", "azerty", "52 rua de Heroismo", "0679065306", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        };
    }

}
