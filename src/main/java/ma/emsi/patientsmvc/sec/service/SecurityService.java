package ma.emsi.patientsmvc.sec.service;

import ma.emsi.patientsmvc.sec.entities.AppRole;
import ma.emsi.patientsmvc.sec.entities.AppUser;

public interface SecurityService {

    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String rolename, String description);
    void addRoleToUser(String username, String rolename );
    void removeRoleFromUser(String username, String rolename );
    AppUser loadUserByUsername(String username);

}
