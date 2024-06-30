package ASM1.demo.repository;

import ASM1.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u from User u where u.email like %?1%")
    List<User> findByEmailContaining(String info);

    @Query("SELECT u from User u where u.phoneNumber like %?1%")
    List<User> findByPhoneNumberContaining(String phoneNumber);

}
