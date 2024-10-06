package ASM1.demo.repository;

import ASM1.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {

    @Query("SELECT u from AppUser u where u.email like %?1%")
    List<AppUser> findByEmailContaining(String info);

    @Query("SELECT u from AppUser u where u.phoneNumber like %?1%")
    List<AppUser> findByPhoneNumberContaining(String phoneNumber);

    AppUser findByUserName(String username);

    AppUser findUserById (int id);

}
