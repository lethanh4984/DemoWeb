package ASM1.demo.controller;

import ASM1.demo.entity.Donation;
import ASM1.demo.entity.User;
import ASM1.demo.entity.UserDonation;
import ASM1.demo.service.DonationService;
import ASM1.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AccountController {

    private DonationService donationService ;

    public AccountController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/donations")
    public String donations(Model theModel){

        List<Donation> donationList = donationService.donations();

        theModel.addAttribute("donationList",donationList);

        return "public/home";
    }

}
