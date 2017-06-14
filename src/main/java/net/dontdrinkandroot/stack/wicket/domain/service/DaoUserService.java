package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.persistence.service.DaoEntityService;
import net.dontdrinkandroot.stack.wicket.domain.dao.UserDao;
import net.dontdrinkandroot.stack.wicket.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service("userService")
public class DaoUserService extends DaoEntityService<User, Long> implements UserService
{
    private PasswordEncoder passwordEncoder;

    protected DaoUserService()
    {
        /* Reflection instantiation */
    }

    @Inject
    public DaoUserService(UserDao userDao, PasswordEncoder passwordEncoder)
    {
        super(userDao);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails userDetails = this.getDao().findByUsernameWithRoles(username);
        if (null == userDetails) {
            throw new UsernameNotFoundException(String.format("No user found for name: %s", username));
        }

        return userDetails;
    }

    @Override
    protected UserDao getDao()
    {
        return (UserDao) super.getDao();
    }

    @Override
    @Transactional
    public void setPassword(User user, String password)
    {
        user.setPassword(this.passwordEncoder.encode(password));
    }
}
