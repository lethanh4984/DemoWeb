package ASM1.demo.repository;

import ASM1.demo.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
}
