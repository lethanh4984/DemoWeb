package ASM1.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "user")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String  address;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private int status ;

    @Column(name = "note")
    private String note;

    @Setter
    @Getter
    @ManyToMany(/*cascade={CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE},*/fetch = FetchType.EAGER)
    private Set<AppRole> appRole;


    @Column(name = "created")
    private String createdAt;

}
