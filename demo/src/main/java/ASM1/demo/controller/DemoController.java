package ASM1.demo.controller;


import ASM1.demo.DAO.AppDAO;
import ASM1.demo.DTO.UserDTO;
import ASM1.demo.DTO.UserMapper;
import ASM1.demo.entity.Role;
import ASM1.demo.entity.User;
import ASM1.demo.repository.RoleRepository;
import ASM1.demo.service.RoleService;
import ASM1.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//tìm cách thêm roleId từ form
// save hoặc delete bằng DAO trong phan 09-spring-boot-jpa-advanced-mappings

@Controller
@RequestMapping("/admin")
public class DemoController {

    private UserService userService;

    private RoleService roleService;

    private AppDAO appDAO;

    public DemoController(RoleService roleService, UserService userService,AppDAO appDAO) {
        this.roleService = roleService;
        this.userService = userService;
        this.appDAO=appDAO;
    }

    @GetMapping("/home")
    public String adminPage(){
        return "admin/home";
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the User from db
        List<User> theUser = userService.listUser();

        // add to the spring model
        theModel.addAttribute("users", theUser);

        for(User user : theUser){
            System.out.println(user);
        }
        return "admin/account";

    }


    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute User user){
        //save user

        UserMapper userMapper = new UserMapper();

        UserDTO userDTO=userMapper.toUserDTO(user);

        Role role= roleService.findRole(userDTO.getRoleId());

        User newUser = new User();

        newUser.setRole(role);
        newUser.setUserName(userDTO.getUserName());
        newUser.setAddress(userDTO.getAddress());
        newUser.setEmail(userDTO.getEmail());
        newUser.setFullName(userDTO.getFullName());
        newUser.setPassword(userDTO.getPassword());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());

        appDAO.saveUser(newUser);


        return "redirect:/admin/list";
    }


    @GetMapping("/donation")
    public String showDonation(Model theModel) {

        // create model attribute to bind form data
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "admin/donation";
    }

    @GetMapping("/search")
    public String searchInfo(Model theModel, String searchInfo){
        List<User> userSearchByEmail = userService.findByEmailContaining(searchInfo);

        List<User> userSearchByPhone = userService.findByPhoneNumberContaining(searchInfo);

        theModel.addAttribute("users",userSearchByEmail);

        theModel.addAttribute("users",userSearchByPhone);

        return "/admin/usersSearch";
    }

//    @PostMapping("/lock")

//    @GetMapping("/unlock")

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId")int theId ){

        appDAO.deleteUserById(theId);

        return "redirect:/admin/list";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") int id,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("email") String email,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address,
                             @RequestParam("role.id") int roleId
                             ){
        Role role = roleService.findRole(roleId);
        User tempUser = appDAO.findUserById(id);

        tempUser.setFullName(fullName);
        tempUser.setPhoneNumber(phoneNumber);
        tempUser.setAddress(address);

        tempUser.setRole(role);

        appDAO.updateUser(tempUser);

        return "redirect:/admin/list";
    }

}
