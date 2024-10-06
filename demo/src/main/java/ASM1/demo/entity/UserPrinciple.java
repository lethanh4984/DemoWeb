package ASM1.demo.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserPrinciple implements UserDetails {

    @Serial
    private static final long serialVersionUID =1L;
    private int id;
    @Getter
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> role;

    public UserPrinciple(int id, String userName, String password, Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.userName = userName;
    }

    public UserPrinciple(String userName, String password, Collection<? extends GrantedAuthority> role) {
        this.password = password;
        this.role = role;
        this.userName = userName;
    }


    public static UserPrinciple build(AppUser appUser){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AppRole appRole : appUser.getAppRole()) {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
        }
        System.out.println("dang build user principle. Chay tiep buoc nao");
        return new UserPrinciple(appUser.getId(), appUser.getUserName(), appUser.getPassword(), authorities);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
