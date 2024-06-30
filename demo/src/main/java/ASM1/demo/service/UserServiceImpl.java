package ASM1.demo.service;


import ASM1.demo.entity.Role;
import ASM1.demo.entity.User;
import ASM1.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }

    @Override
    public User findUser(int id) {
        Optional<User> result = userRepository.findById(id);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + id);
        }

        return theUser;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int theId) {

        userRepository.deleteById(theId);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findByEmailContaining(String searchString) {

        return userRepository.findByEmailContaining("%" + searchString + "%");

    }

    @Override
    public List<User> findByPhoneNumberContaining(String number) {
       return userRepository.findByPhoneNumberContaining("%" + number + "%");
    }
}
