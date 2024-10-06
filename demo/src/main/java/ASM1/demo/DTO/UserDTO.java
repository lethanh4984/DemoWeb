package ASM1.demo.DTO;

import lombok.Data;

@Data
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
}
