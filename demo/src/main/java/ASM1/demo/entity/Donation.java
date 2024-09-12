package ASM1.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "created")
    private String created;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private int money;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "status")
    private int status;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "donation_status_id")
    private DonationStatus donationStatus;


    public Donation(String code, String created, String description, DonationStatus donationStatus, String endDate, int id, String image, int money, String name, String organizationName, String phoneNumber, String startDate, int status) {
        this.code = code;
        this.created = created;
        this.description = description;
        this.donationStatus = donationStatus;
        this.endDate = endDate;
        this.id = id;
        this.image = image;
        this.money = money;
        this.name = name;
        this.organizationName = organizationName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "code='" + code + '\'' +
                ", id=" + id +
                ", created='" + created + '\'' +
                ", description='" + description + '\'' +
                ", endDate='" + endDate + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", organizationName='" + organizationName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", donationStatus=" + donationStatus +
                '}';
    }

    public DonationStatus getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(DonationStatus donationStatus) {
        this.donationStatus = donationStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Donation() {
    }

}
