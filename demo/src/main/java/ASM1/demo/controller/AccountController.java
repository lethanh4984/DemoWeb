package ASM1.demo.controller;

import ASM1.demo.entity.Donation;
import ASM1.demo.entity.UserDonation;
import ASM1.demo.service.DonationService;
import ASM1.demo.service.UserDonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AccountController {

    private DonationService donationService ;
    private UserDonationService userDonationService;

    public AccountController(DonationService donationService,UserDonationService userDonationService) {
        this.donationService = donationService;
        this.userDonationService=userDonationService;
    }

    @GetMapping("/")
    public String donations(Model theModel){

        List<Donation> donationList = donationService.donations();

        theModel.addAttribute("donationList",donationList);

        return "public/home";
    }


    @GetMapping("/showDonationInfo")
    public String showDonation(){

        return "public/detail";
    }

    @PostMapping("/userDonation")
    public String userDonation(@RequestParam("name") String name,
                               @RequestParam("money") int money,
                               @RequestParam("messageOfUser") String messageOfUser,
                               @RequestParam("donationId")int donationId){

        UserDonation userDonation = new UserDonation();

        userDonation.setName(name);
        userDonation.setMoney(money);
        userDonation.setText(messageOfUser);

        Donation donation = donationService.findById(donationId);

        //set donationId theo donation
        userDonation.setDonation(donation);


        userDonationService.save(userDonation);


        return "public/home";
    }
}
