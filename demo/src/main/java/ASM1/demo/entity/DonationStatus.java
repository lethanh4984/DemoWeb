package ASM1.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "donation_status")
public class DonationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public DonationStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DonationStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DonatonStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
