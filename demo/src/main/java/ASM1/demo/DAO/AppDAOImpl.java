package ASM1.demo.DAO;

import ASM1.demo.entity.Role;
import ASM1.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{


    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void deleteUserById(int theId) {
        User user = entityManager.find(User.class,theId);

        user.setRole(null);

        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<User> findUsersByRoleId(int theId) {
                // create query
        TypedQuery<User> query = entityManager.createQuery(
                "from User where role.id = :data", User.class);
        query.setParameter("data", theId);

        // execute query
        List<User> courses = query.getResultList();

        return courses;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
