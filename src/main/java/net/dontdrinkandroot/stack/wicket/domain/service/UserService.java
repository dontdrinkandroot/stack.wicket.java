package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.persistence.service.EntityService;
import net.dontdrinkandroot.stack.wicket.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UserService extends UserDetailsService, EntityService<User, Long>
{
    void setPassword(User user, String password);
}
