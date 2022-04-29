package ma.emsi.patientsmvc.sec.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.sec.entities.AppRole;
import ma.emsi.patientsmvc.sec.entities.AppUser;
import ma.emsi.patientsmvc.sec.repositories.AppRoleRepository;
import ma.emsi.patientsmvc.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword))throw new RuntimeException("password incorrect");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.getUserid(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String rolename, String description) {
    AppRole appRole =appRoleRepository.findByRolename(rolename);
        if (appRole != null)throw new RuntimeException("Role"+rolename+"ALL READY EXIST");
        appRole= new AppRole();
        appRole.setRolename(rolename);
        appRole.setDescription(description);
        AppRole savedAppRole=appRoleRepository.save(appRole);

        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        appUser.getAppRoles().add(appRole);





    }

    @Override
    public void removeRoleFromUser(String username, String rolename) {

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return null;
    }
}
