package ASM1.demo.service.donation;

import ASM1.demo.entity.Donation;

import java.util.List;

public interface DonationService {

    void save(Donation donation);

    void deleteById(int id);

    Donation findById(int id);

    List<Donation> donations();


}
