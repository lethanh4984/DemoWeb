package ASM1.demo.repository;

import ASM1.demo.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> findAllByStatus(int status);
}
