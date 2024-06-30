package ASM1.demo.service;

import ASM1.demo.entity.Role;
import ASM1.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRole(int roleId) {
        Optional<Role> result = roleRepository.findById(roleId);

        Role role = null;

        if(result.isPresent()){
            role=result.get();
        }else {
            throw new RuntimeException("Did not find role id - " + roleId);
        }

        return role;
    }
}
