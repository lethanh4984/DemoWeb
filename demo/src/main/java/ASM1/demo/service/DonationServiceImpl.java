package ASM1.demo.service;

import ASM1.demo.entity.Donation;
import ASM1.demo.entity.Role;
import ASM1.demo.repository.DonationRepository;
import org.springframework.stereotype.Service;

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

        if(result.isPresent()){
            donation=result.get();
        }else {
            throw new RuntimeException("Did not find role id - " + id);
        }

        return donation;
    }

    @Override
    public List<Donation> donations() {
        return donationRepository.findAll();
    }
}
