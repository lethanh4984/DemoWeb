package ASM1.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String  address;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private int status ;

    @Column(name = "note")
    private String note;

    @OneToOne(cascade={CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "role_id")
    private Role role;


    @Column(name = "created")
    private String createdAt;

    public User() {
    }

    public User(String address, String createdAt, String email, String fullName, int id, String note, String phoneNumber, Role role, String password, int status, String userName) {
        this.address = address;
        this.createdAt = createdAt;
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.note = note;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.password = password;
        this.status = status;
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String created) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", role=" + role +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
