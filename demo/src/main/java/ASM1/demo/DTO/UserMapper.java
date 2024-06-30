package ASM1.demo.DTO;

import ASM1.demo.entity.User;

public class UserMapper {

    public UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setPassword(user.getPassword());
        userDTO.setUserName(user.getUserName());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRoleId(user.getRole().getId());


        return userDTO;
    }
}
