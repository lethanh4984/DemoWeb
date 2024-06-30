package ASM1.demo.DTO;

public class UserDTO {

    private String password;

    private String fullName;

    private String userName;

    private String phoneNumber;

    private String address;

    private int roleId;

    private String email;

    public UserDTO(String address, String userName, int roleId, String phoneNumber, String password, String fullName, String email) {
        this.address = address;
        this.userName = userName;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public UserDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
