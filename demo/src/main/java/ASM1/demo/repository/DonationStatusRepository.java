package ASM1.demo.repository;

import ASM1.demo.entity.DonationStatus;
import ASM1.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationStatusRepository extends JpaRepository<DonationStatus,Integer> {

    @Query("SELECT u from DonationStatus u where u.name like %?1%")
    DonationStatus findByDonationStatusName(String name);
}
