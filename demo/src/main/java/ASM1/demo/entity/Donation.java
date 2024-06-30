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
                '}';
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
}
