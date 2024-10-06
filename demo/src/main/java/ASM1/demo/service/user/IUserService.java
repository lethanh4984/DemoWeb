package ASM1.demo.service.user;

import ASM1.demo.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    AppUser findUser(int id);

   List<AppUser> listUser();

   void deleteById(int theId);

   void save(AppUser theAppUser);

   List<AppUser> findByEmailContaining(String infix);

   List<AppUser> findByPhoneNumberContaining(String number);

}
