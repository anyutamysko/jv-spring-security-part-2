package mate.academy.spring.dao.impl;

import java.util.Optional;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> findByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> findByName = session.createQuery(
                    "FROM Role WHERE roleName = :roleName", Role.class);
            findByName.setParameter("roleName", roleName);
            return findByName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Role with role name " + roleName + " not found", e);
        }
    }
}