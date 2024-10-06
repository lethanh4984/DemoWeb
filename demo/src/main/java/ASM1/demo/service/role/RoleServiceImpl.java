package ASM1.demo.service.role;

import ASM1.demo.entity.AppRole;
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
    public AppRole findRole(int roleId) {
        Optional<AppRole> result = roleRepository.findById(roleId);

        AppRole appRole = null;

        if(result.isPresent()){
            appRole =result.get();
        }else {
            throw new RuntimeException("Did not find role id - " + roleId);
        }

        return appRole;
    }
}
