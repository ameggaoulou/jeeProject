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
        appUser.getUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
    AppRole appRole =appRoleRepository.findByRoleName(roleName);
        if (appRole != null)throw new RuntimeException("Role"+roleName+"ALL READY EXIST");
        appRole= new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole=appRoleRepository.save(appRole);

        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("not found");
        appUser.getAppRoles().add(appRole);





    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw new RuntimeException("not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
