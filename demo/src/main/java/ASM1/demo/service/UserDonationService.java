package ASM1.demo.service;

import ASM1.demo.entity.UserDonation;

import java.util.List;

public interface UserDonationService {


    List<UserDonation> findByDonationId(int donationId);

    List<UserDonation> findAll();

    void save(UserDonation userDonation);
}
