package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.stack.wicket.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UserService extends UserDetailsService
{
    void setPassword(User user, String password);

    User save(User user);
}
