package ASM1.demo.service;

import ASM1.demo.entity.Role;
import ASM1.demo.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    User findUser(int id);

   List<User> listUser();

   void deleteById(int theId);

   void save(User theUser);

   List<User> findByEmailContaining(String infix);

   List<User> findByPhoneNumberContaining(String number);

}
