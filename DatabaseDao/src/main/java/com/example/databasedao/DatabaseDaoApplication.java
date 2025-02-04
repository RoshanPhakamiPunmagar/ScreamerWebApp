package com.example.databasedao;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

/**
 * Author: Roshan Phakami Punmagar
 * File Name: DatabaseDaoApplication.java
 * Date: 16/9/2024
 * Purpose:
 * start and seed database
 * ******************************************************
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DatabaseDaoApplication {

    public static void main(String[] args) {
        try{
        //set up discovery client and configure port and name for discovery
        SpringApplicationBuilder DatabaseService = new SpringApplicationBuilder(DatabaseDaoApplication.class);
        DatabaseService.properties("server.port=8000");
        DatabaseService.properties("spring.application.name=database");
        //set url to discovery server
        DatabaseService.properties("eureka.client.service-url.defaultZone=http://localhost:8761/eureka/");
        DatabaseService.properties("eureka.instance.prefer-ip-address=true");

        DatabaseService.run(args);
        }catch(Exception  e)
        {
            System.out.println("--------------------------ERROR---------------------------");
            System.out.println("Cannot connect to discovery server");
            System.out.println("--------------------------ERROR---------------------------");
        }
    }

}

@Component
@Data
@Transactional
class AppInit implements ApplicationRunner {

    //inject database repos
    private final MovieController movieController;
    private final MoviesRepository moviesRepository;
    private final CustomerRepository customerRepository;
    private final CustomerController customerController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create and save movie entries
        Movies m = new Movies();
        m.setTitle("Getout");
        m.setSubGenre("Action");
        m.setDescription("When African-American man goes to visit Caucasian parents something's off");
        m.setUrl("https://www.youtube.com/embed/y1OhC9h3flY?si=lmulPcEtFJcbg92Z");
        moviesRepository.save(m);

        Movies m1 = new Movies();
        m1.setTitle("Hereditary");
        m1.setSubGenre("Comedy");
        m1.setDescription("Annie is devastated along with her family following her mother’s death. Odd things begin happening as the truth about Annie's family starts to emerge.");
        m1.setUrl("https://www.youtube.com/embed/-sM8Jrcbxdc?si=fCDhEViO7quuwUvv");
        moviesRepository.save(m1);

        // Additional movie entries
        Movies m2 = new Movies();
        m2.setTitle("The Conjuring");
        m2.setSubGenre("Mystery");
        m2.setDescription("Rod and Carolyn find their pet dog dead and experience a spirit that harms their daughter.");
        m2.setUrl("https://www.youtube.com/embed/JhMWopjJiI8?si=4iyYIe1xsWrDdaYG");
        moviesRepository.save(m2);

        Movies m3 = new Movies();
        m3.setTitle("Say it");
        m3.setSubGenre("Action");
        m3.setDescription("A Chinese immigrant must connect with different versions of herself to save the universe.");
        m3.setUrl("https://www.youtube.com/embed/JjBYmLxmT_U?si=EXJZCg7_CSgZUA8I");
        moviesRepository.save(m3);

        Movies m4 = new Movies();
        m4.setTitle("Interstellar");
        m4.setSubGenre("Melodrama");
        m4.setDescription("A NASA pilot turned farmer must find a new Earth.");
        m4.setUrl("https://www.youtube.com/embed/s_M1t0HE-Kk?si=oN8dQ9vY6YGPaufW");
        moviesRepository.save(m4);

        Movies m5 = new Movies();
        m5.setTitle("The Exorcist");
        m5.setSubGenre("Si-Fi");
        m5.setDescription("An actress notices dangerous changes in her 12-year-old daughter.");
        m5.setUrl("https://www.youtube.com/embed/bSxuXQCEC7M?si=aPw7kcVFWW-QwMER");
        moviesRepository.save(m5);

        // Additional movie entries
        Movies m6 = new Movies();
        m6.setTitle("Drag Me To Hell");
        m6.setSubGenre("Mystery");
        m6.setDescription("When loan officer Christine Brown refuses an old lady an extension on her loan, the lady places a curse of the Lamia upon Christine. Soon, Christine's life is turned into a nightmare..");
        m6.setUrl("https://www.youtube.com/embed/UWFdTB9GIaQ?si=eYbuHZsGwyGyEeYy");
        moviesRepository.save(m6);

        Movies m7 = new Movies();
        m7.setTitle("Shutter");
        m7.setSubGenre("Mystery");
        m7.setDescription("Jane and Tun flee the site of the collision quickly after running over a girl in a car. But they become uneasy when photographer Tun finds odd shadows in his images.");
        m7.setUrl("https://www.youtube.com/embed/VMIB8b_mE-Q?si=tWnMsoD6wEaIvPPX");
        moviesRepository.save(m7);

        Movies m8 = new Movies();
        m8.setTitle("The Wailing");
        m8.setSubGenre("Mystery");
        m8.setDescription("When an outsider visits a village, its inhabitants experience a mysterious epidemic. A police officer then tries to solve the mystery behind the outbreak to save his sick daughter.");
        m8.setUrl("https://www.youtube.com/embed/43uAputjI4k?si=xCUh51ATGqvJdDJs");
        moviesRepository.save(m8);

        Movies m9 = new Movies();
        m9.setTitle("The Conjuring");
        m9.setSubGenre("Paranormal");
        m9.setDescription("A crew of an online horror show decides to go to an asylum and stream from the inside to gain more viewers. Things take an unexpected turn when they begin to experience more than they imagined.");
        m9.setUrl("https://www.youtube.com/embed/AAjQTfXQePw?si=QYL44ek2Tmil24sr");
        moviesRepository.save(m9);

        Movies m10 = new Movies();
        m10.setTitle("MahaJodi");
        m10.setSubGenre("Comedy");
        m10.setDescription("A light-hearted comedy about the adventures of a quirky family.");
        m10.setUrl("https://www.youtube.com/embed/JhMWopjJiI8?si=4iyYIe1xsWrDdaYG");
        moviesRepository.save(m10);

        // Create a new Customer object for Roshan
        Customer c2 = new Customer();
        c2.setName("Roshan");
        c2.setEmail("roshan@gmail.com");
        c2.setPassword("roshan123");
        c2.setRoll("ROLE_ADMIN");

        customerRepository.save(c2);
        customerController.addCustomer(c2);

        // Create a new Customer object for Anmol
        Customer c1 = new Customer();
        c1.setName("Anmol");
        c1.setEmail("anmol@gmail.com");
        c1.setPassword("anmol123");
        c1.setRoll("ROLE_ADMIN");

        customerRepository.save(c1);
        customerController.addCustomer(c1);

        // Create a new Customer object for Caleb
        Customer c3 = new Customer();
        c3.setName("Caleb");
        c3.setEmail("caleb@gmail.com");
        c3.setPassword("caleb123");
        c3.setRoll("ROLE_USER");

        customerRepository.save(c3);
        customerController.addCustomer(c3);

        // Create a new Customer object for Rutvi
        Customer c4 = new Customer();
        c4.setName("Rutvi");
        c4.setEmail("rutvi@gmail.com");
        c4.setPassword("rutvi123");
        c4.setRoll("ROLE_USER");
        customerRepository.save(c4);
        customerController.addCustomer(c4);
    }
}
