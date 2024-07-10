package ASM1.demo.service;

import ASM1.demo.entity.DonationStatus;
import ASM1.demo.repository.DonationStatusRepository;

public interface DonationStatusService {


    DonationStatus findDonationStatusById(int donationStatusId);
    void save(DonationStatus donationStatus);
    DonationStatus findByName(String name);
}
