package ASM1.demo.repository;

import ASM1.demo.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationStatusRepository extends JpaRepository<DonationStatus,Integer> {

    @Query("SELECT u from DonationStatus u where u.name like %?1%")
    DonationStatus findByDonationStatusName(String name);

//    @Modifying
    //@Modifying là query để sữa dữ liệu
//    @Transactional
    //@Transactional là query để thực hiện nhiều thao tác
//    @Query(nativeQuery = true, value = "call findByDonationStatusName(:name)")
//    void deleteDonaionStatus();
//    nativeQuery = true để gọi  trực tiếp câu lệnh SQL

}
