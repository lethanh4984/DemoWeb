package ASM1.demo.controller;

import ASM1.demo.DTO.UserDTO;
import ASM1.demo.DTO.UserMapper;
import ASM1.demo.entity.*;
import ASM1.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class DemoController {

    private UserService userService;

    private RoleService roleService;

    private DonationService donationService;

    private DonationStatusService donationStatusService;

    private UserDonationService userDonationService;


    public DemoController(RoleService roleService, UserService userService, DonationService donationService,
                          DonationStatusService donationStatusService,UserDonationService userDonationService) {
        this.roleService = roleService;
        this.userService = userService;
        this.donationService = donationService;
        this.donationStatusService =donationStatusService;
        this.userDonationService = userDonationService;
    }

    @GetMapping("/home")
    public String adminPage() {
        return "admin/home";
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the User from db
        List<User> theUser = userService.listUser();

        // add to the spring model
        theModel.addAttribute("users", theUser);

        return "admin/account";

    }


    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute User user) {


        UserMapper userMapper = new UserMapper();

        UserDTO userDTO = userMapper.toUserDTO(user);

        Role role = roleService.findRole(userDTO.getRoleId());

        User newUser = new User();

        newUser.setRole(role);
        newUser.setUserName(userDTO.getUserName());
        newUser.setAddress(userDTO.getAddress());
        newUser.setEmail(userDTO.getEmail());
        newUser.setFullName(userDTO.getFullName());
        newUser.setPassword(userDTO.getPassword());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());

        userService.save(newUser);

        return "redirect:/admin/list";
    }




    @GetMapping("/search")
    public String searchInfo(Model theModel, String searchInfo) {
        List<User> userSearchByEmail = userService.findByEmailContaining(searchInfo);

        List<User> userSearchByPhone = userService.findByPhoneNumberContaining(searchInfo);

        theModel.addAttribute("users", userSearchByEmail);

        theModel.addAttribute("users", userSearchByPhone);

        return "/admin/usersSearch";
    }

    @PostMapping("/lock")
    public String lockFunction(@RequestParam("userId") int id) {

        User user = userService.findUser(id);

        user.setStatus(0);

        userService.save(user);

        return "redirect:/admin/list";
    }


    @PostMapping("/unlock")
    public String unlockFunction(@RequestParam("userId") int id) {
        User user = userService.findUser(id);

        user.setStatus(1);

        userService.save(user);

        return "redirect:/admin/list";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {

        User user = userService.findUser(theId);

        //set foreign key = null to delete
        user.setRole(null);

        userService.deleteById(theId);

        return "redirect:/admin/list";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") int id,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address,
                             @RequestParam("role.id") int roleId
    ) {
        Role role = roleService.findRole(roleId);
        User tempUser = userService.findUser(id);
        tempUser.setFullName(fullName);
        tempUser.setPhoneNumber(phoneNumber);
        tempUser.setAddress(address);

        tempUser.setRole(role);

        userService.save(tempUser);

        return "redirect:/admin/list";
    }


    //show list donation from database
    @GetMapping("/donation")
    public String showDonation(Model theModel) {

        // create model attribute to bind form data
        List<Donation> donations = donationService.donations();

        theModel.addAttribute("donationList", donations);

        return "admin/donation";
    }


    //create new donation
    @PostMapping("/saveDonation")
    public String saveDonation(@ModelAttribute Donation donation){

        Donation newDonation = new Donation();

        newDonation.setCode(donation.getCode());
        newDonation.setName(donation.getName());
        newDonation.setPhoneNumber(donation.getPhoneNumber());
        newDonation.setStartDate(donation.getStartDate());
        newDonation.setEndDate(donation.getEndDate());
        newDonation.setOrganizationName(donation.getOrganizationName());
        newDonation.setDescription(donation.getDescription());

        DonationStatus donationStatus= donationStatusService.findDonationStatusById(donation.getDonationStatus().getId());

        newDonation.setDonationStatus(donationStatus);


        donationService.save(newDonation);

        return  "redirect:/admin/donation";
    }

    //update donation
    @PostMapping("/updateDonation")
    public String updateDonation(@RequestParam("code")String code,
                                 @RequestParam("name") String name,
                                 @RequestParam("start") String startDate,
                                 @RequestParam("end") String endDate,
                                 @RequestParam("organizationName") String organnizationName,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("description") String description,
                                 @RequestParam("donationId")int id){

        Donation tempDonation = donationService.findById(id);

        tempDonation.setName(name);
        tempDonation.setCode(code);
        tempDonation.setDescription(description);
        tempDonation.setPhoneNumber(phoneNumber);
        tempDonation.setStartDate(startDate);
        tempDonation.setEndDate(endDate);
        tempDonation.setOrganizationName(organnizationName);


        donationService.save(tempDonation);

        return  "redirect:/admin/donation";
    }

    @PostMapping("/deleteDonation")
    public String delete(@RequestParam("donationId") int donationId){
        //Note: if status == 0 show info of donation ; if status == 1 hide info

        Donation donation = donationService.findById(donationId);

        //set up status to show and hide info
        if(donation.getStatus() == 0){
            donation.setStatus(1);
        }

        donationService.save(donation);

        return  "redirect:/admin/donation";
    }

    @GetMapping("/donation/{donationId}")
    public String showDonation(Model model, @PathVariable("donationId") int donationId){
        Donation donation = donationService.findById(donationId);
//        List<Donation> donations = donationService.donations();

        List<UserDonation> userDonationList= userDonationService.findByDonationId(donationId);
        model.addAttribute("userDonations",userDonationList);
        model.addAttribute("donation",donation);

        return "admin/detail";
    }

    @PostMapping("/donation/status")
    public String showStatus(@RequestParam("donationId") int id){
        Donation donation = donationService.findById(id);

        DonationStatus donationStatus = new DonationStatus(2,"mới tạo");


        switch (donation.getDonationStatus().getName()) {
            case "Mới tạo" -> {
                donation.setDonationStatus(null);
                
                donationStatus.setName("Đang quyên góp");

                String name = donationStatus.getName();

                DonationStatus newDonationStatus= donationStatusService.findByName(name);

                donation.setDonationStatus(newDonationStatus);
            }
            case "Đang quyên góp" -> {
                donation.setDonationStatus(null);

                donationStatus.setName("Kết thúc quyên góp");

                String name = donationStatus.getName();

                DonationStatus newDonationStatus= donationStatusService.findByName(name);

                donation.setDonationStatus(newDonationStatus);

            }
            case "Kết thúc quyên góp" -> {
                donation.setDonationStatus(null);

                donationStatus.setName("Đóng quyên góp");

                String name = donationStatus.getName();

                DonationStatus newDonationStatus= donationStatusService.findByName(name);

                donation.setDonationStatus(newDonationStatus);

            }
        }
        donationService.save(donation);

        return "redirect:/admin/donation";
    }

}
