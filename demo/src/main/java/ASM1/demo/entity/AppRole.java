package ASM1.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@Entity
@Table(name = "role")
public class AppRole implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    public AppRole() {
    }

    public AppRole(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
