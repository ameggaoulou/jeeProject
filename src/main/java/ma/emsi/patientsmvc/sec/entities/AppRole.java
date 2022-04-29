package ma.emsi.patientsmvc.sec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleid;
    @Column(unique = true)
    private String rolename;
    private  String description;

}
