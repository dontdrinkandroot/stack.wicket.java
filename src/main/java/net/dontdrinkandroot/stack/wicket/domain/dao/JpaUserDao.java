package net.dontdrinkandroot.stack.wicket.domain.dao;

import net.dontdrinkandroot.persistence.dao.JpaEntityDao;
import net.dontdrinkandroot.stack.wicket.model.User;
import net.dontdrinkandroot.stack.wicket.model.User_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Repository
public class JpaUserDao extends JpaEntityDao<User, Long> implements UserDao
{
    public JpaUserDao()
    {
        super(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameWithRoles(String username)
    {
        CriteriaBuilder builder = this.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        root.fetch(User_.roles);
        query.where(builder.equal(root.get(User_.username), username));

        return this.findSingleOrNull(query);
    }
}
