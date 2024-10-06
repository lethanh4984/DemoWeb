package ASM1.demo.DTO;

import ASM1.demo.entity.AppRole;
import ASM1.demo.entity.AppUser;
import ASM1.demo.service.role.RoleService;

public class UserMapper {

    private RoleService roleService;

    public UserDTO toUserDTO(AppUser appUser){
        UserDTO userDTO = new UserDTO();

        userDTO.setPassword(appUser.getPassword());
        userDTO.setUserName(appUser.getUserName());
        userDTO.setAddress(appUser.getAddress());
        userDTO.setEmail(appUser.getEmail());
        userDTO.setFullName(appUser.getFullName());
        userDTO.setPhoneNumber(appUser.getPhoneNumber());

        for(AppRole role:appUser.getAppRole()){
            userDTO.setRoleId(role.getId());
        }



        return userDTO;
    }
}
