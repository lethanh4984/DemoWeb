package ASM1.demo.service.donationStatus;

import ASM1.demo.entity.DonationStatus;
import ASM1.demo.repository.DonationStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationStatusImpl implements DonationStatusService {

    private DonationStatusRepository donationRepository;

    public DonationStatusImpl(DonationStatusRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public DonationStatus findDonationStatusById(int donationStatusId) {
        Optional<DonationStatus> result = donationRepository.findById(donationStatusId);

        DonationStatus donationStatus = null;

        if(result.isPresent()){
            donationStatus=result.get();
        }else {
            throw new RuntimeException("Did not find role id - " + donationStatusId);
        }

        return donationStatus;
    }

    @Override
    public void save(DonationStatus donationStatus) {
        donationRepository.save(donationStatus);
    }

    @Override
    public DonationStatus findByName(String name) {

        return donationRepository.findByDonationStatusName(name);

    }
}
