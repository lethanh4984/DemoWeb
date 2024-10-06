package ASM1.demo.service.user;


import ASM1.demo.entity.AppUser;
import ASM1.demo.entity.UserPrinciple;
import ASM1.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }

    @Override
    public AppUser findUser(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<AppUser> listUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int theId) {

        userRepository.deleteById(theId);
    }

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public List<AppUser> findByEmailContaining(String searchString) {

        return userRepository.findByEmailContaining("%" + searchString + "%");

    }

    @Override
    public List<AppUser> findByPhoneNumberContaining(String number) {
       return userRepository.findByPhoneNumberContaining("%" + number + "%");
    }



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUserName(userName);

        if(appUser ==null){
            throw new UsernameNotFoundException("user cant find");
        }
        System.out.println("dang build user detail");
        return UserPrinciple.build(appUser);
    }
}
