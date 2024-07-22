package ASM1.demo.service;

import ASM1.demo.entity.UserDonation;
import ASM1.demo.repository.UserDonationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDonationServiceImpl implements UserDonationService {

    private UserDonationRepository userDonationRepository;

    public UserDonationServiceImpl(UserDonationRepository userDonationRepository) {
        this.userDonationRepository = userDonationRepository;
    }

    @Override
    public List<UserDonation> findByDonationId(int donationId) {
        List<UserDonation> userDonationList = userDonationRepository.findAll();

        List<UserDonation> newUserDonationList = new ArrayList<>();

        for(UserDonation userDonation: userDonationList){
            if(userDonation.getDonation().getId() == donationId){
                newUserDonationList.add(userDonation);
            }
        }

        return newUserDonationList;
    }

    @Override
    public List<UserDonation> findAll() {
        return userDonationRepository.findAll();
    }

    @Override
    public void save(UserDonation userDonation) {
        userDonationRepository.save(userDonation);
    }
}
