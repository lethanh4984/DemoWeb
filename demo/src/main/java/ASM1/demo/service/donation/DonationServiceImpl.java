package ASM1.demo.service.donation;

import ASM1.demo.entity.Donation;
import ASM1.demo.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void deleteById(int id) {
        donationRepository.deleteById(id);
    }

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public Donation findById(int id) {
        Optional<Donation> result = donationRepository.findById(id);

        Donation donation = null;

        if (result.isPresent()) {
            donation = result.get();
        } else {
            throw new RuntimeException("Did not find role id - " + id);
        }

        return donation;
    }

    @Override
    public List<Donation> donations() {

        List<Donation>  donationList= donationRepository.findAll();

        List<Donation> donationListByStatus= new ArrayList<>();

        for (Donation donation : donationList) {
            if (donation.getStatus()==0){
                donationListByStatus.add(donation);
            }
        }

        return donationListByStatus;
    }

//    @Override
//    public List<Donation> findAllByStatus(int status) {
//
//    }
}
