package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository)
    {
        return args -> {
            patientRepository.save(new Patient(null,"hassan", new Date(),false,12));
            patientRepository.save(new Patient(null,"mehdi", new Date(),false,20));
            patientRepository.save(new Patient(null,"mohamed", new Date(),true,15));
            patientRepository.save(new Patient(null,"achraf", new Date(),true,1));


            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });


        };



    }
    @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("da3bol", "1234", "1234");
            securityService.saveNewUser("moha1", "1234", "1234");
            securityService.saveNewUser("zak", "1234", "1234");
            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("moha1", "USER");
            securityService.addRoleToUser("zak", "USER");
            securityService.addRoleToUser("da3bol", "ADMIN");
        };
    }






}
