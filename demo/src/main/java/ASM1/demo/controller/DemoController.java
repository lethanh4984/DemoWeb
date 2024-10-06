package ASM1.demo.controller;

import ASM1.demo.DTO.UserDTO;
import ASM1.demo.DTO.UserMapper;
import ASM1.demo.entity.*;
import ASM1.demo.service.donationStatus.DonationStatusService;
import ASM1.demo.service.donation.DonationService;
import ASM1.demo.service.role.RoleService;
import ASM1.demo.service.user.IUserService;
import ASM1.demo.service.userDonation.UserDonationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/api/admins")
public class DemoController {

    private IUserService userService;

    private RoleService roleService;

    private DonationService donationService;

    private DonationStatusService donationStatusService;

    private UserDonationService userDonationService;

    @Value("${upload_file}")
    public String UPLOAD_DIRECTORY;


    public DemoController(RoleService roleService, IUserService userService, DonationService donationService,
                          DonationStatusService donationStatusService, UserDonationService userDonationService) {
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
        List<AppUser> theAppUser = userService.listUser();

        // add to the spring model
        theModel.addAttribute("users", theAppUser);

        return "admin/account";

    }


    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute AppUser appUser) {


        UserMapper userMapper = new UserMapper();

        UserDTO userDTO = userMapper.toUserDTO(appUser);

        AppRole appRole = roleService.findRole(userDTO.getRoleId());

        AppUser newAppUser = new AppUser();

        newAppUser.setAppRole(Collections.singleton(appRole));
        newAppUser.setUserName(userDTO.getUserName());
        newAppUser.setAddress(userDTO.getAddress());
        newAppUser.setEmail(userDTO.getEmail());
        newAppUser.setFullName(userDTO.getFullName());
        newAppUser.setPassword(userDTO.getPassword());
        newAppUser.setPhoneNumber(userDTO.getPhoneNumber());

        userService.save(newAppUser);

        return "redirect:/api/admins/list";
    }




    @GetMapping("/search")
    public String searchInfo(Model theModel, String searchInfo) {
        List<AppUser> appUserSearchByEmail = userService.findByEmailContaining(searchInfo);

        List<AppUser> appUserSearchByPhone = userService.findByPhoneNumberContaining(searchInfo);

        theModel.addAttribute("users", appUserSearchByEmail);

        theModel.addAttribute("users", appUserSearchByPhone);

        return "/admin/usersSearch";
    }

    @PostMapping("/lock")
    public String lockFunction(@RequestParam("userId") int id) {

        AppUser appUser = userService.findUser(id);

        appUser.setStatus(0);

        userService.save(appUser);

        return "redirect:/api/admins/list";
    }


    @PostMapping("/unlock")
    public String unlockFunction(@RequestParam("userId") int id) {
        AppUser appUser = userService.findUser(id);

        appUser.setStatus(1);

        userService.save(appUser);

        return "redirect:/api/admins/list";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {

        AppUser appUser = userService.findUser(theId);

        //set foreign key = null to delete
        appUser.setAppRole(null);

        userService.deleteById(theId);

        return "redirect:/api/admins/list";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") int id,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address,
                             @RequestParam("role.id") int roleId
                             ) {
        AppRole appRole = roleService.findRole(roleId);
        AppUser tempAppUser = userService.findUser(id);
        tempAppUser.setFullName(fullName);
        tempAppUser.setPhoneNumber(phoneNumber);
        tempAppUser.setAddress(address);

        tempAppUser.setAppRole(Collections.singleton(appRole));


        userService.save(tempAppUser);




        return "redirect:/api/admins/list";
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

        return  "redirect:/api/admins/donation";
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
                                 @RequestParam("donationId")int id,
                                 @RequestParam("image") MultipartFile imageFile,
//                                 @RequestParam("image") MultipartFile[] imageFiles,//List images
                                 Model model) throws IOException {

        Donation tempDonation = donationService.findById(id);

        tempDonation.setName(name);
        tempDonation.setCode(code);
        tempDonation.setDescription(description);
        tempDonation.setPhoneNumber(phoneNumber);
        tempDonation.setStartDate(startDate);
        tempDonation.setEndDate(endDate);
        tempDonation.setOrganizationName(organnizationName);

        //duyet qua List Images
//        for(MultipartFile image:imageFiles){
//        String fileName = imageFile.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(imageFile.getBytes(),new File(UPLOAD_DIRECTORY + fileName));
//        } catch (IOException e) {
//            throw new RuntimeException("No image");
//        }
//        tempDonation.setImage(fileName);
//
//        }


        //upload file
        String fileName = imageFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(imageFile.getBytes(),new File(UPLOAD_DIRECTORY + fileName));
        } catch (IOException e) {
            throw new RuntimeException("No image");
        }
        tempDonation.setImage(fileName);

        donationService.save(tempDonation);

        return  "redirect:/api/admins/donation";
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

        return  "redirect:/api/admins/donation";
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

        return "redirect:/api/admins/donation";
    }


}
