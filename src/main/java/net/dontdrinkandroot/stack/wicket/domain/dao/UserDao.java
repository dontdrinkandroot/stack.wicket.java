package net.dontdrinkandroot.stack.wicket.domain.dao;

import net.dontdrinkandroot.persistence.dao.EntityDao;
import net.dontdrinkandroot.stack.wicket.model.User;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UserDao extends EntityDao<User, Long>
{
    User findByUsernameWithRoles(String username);
}
