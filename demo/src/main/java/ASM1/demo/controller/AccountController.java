package ASM1.demo.controller;

import ASM1.demo.entity.Donation;
import ASM1.demo.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AccountController {

    private DonationService donationService ;

    public AccountController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/")
    public String donations(Model theModel){

        List<Donation> donationList = donationService.donations();

        theModel.addAttribute("donationList",donationList);

        return "public/home";
    }

    @GetMapping("/donationInfo")
    public String showDonationInfo(){



        return "public/detail";
    }
}
