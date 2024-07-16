package ASM1.demo.repository;

import ASM1.demo.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDonationRepository extends JpaRepository<UserDonation,Integer> {
}
