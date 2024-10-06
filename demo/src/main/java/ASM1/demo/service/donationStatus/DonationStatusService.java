package ASM1.demo.service.donationStatus;

import ASM1.demo.entity.DonationStatus;

public interface DonationStatusService {

    DonationStatus findDonationStatusById(int donationStatusId);
    void save(DonationStatus donationStatus);
    DonationStatus findByName(String name);
}
