package ASM1.demo.DAO;

import ASM1.demo.entity.User;

import java.util.List;

public interface AppDAO {

    void saveUser(User user);

    void deleteUserById(int theId);

    List<User> findUsersByRoleId(int theId);

    void updateUser(User user);

    User findUserById(int theId);
}
