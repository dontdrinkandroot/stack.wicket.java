package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.persistence.service.DaoEntityService;
import net.dontdrinkandroot.stack.wicket.domain.dao.UserDao;
import net.dontdrinkandroot.stack.wicket.model.Role;
import net.dontdrinkandroot.stack.wicket.model.User;
import net.dontdrinkandroot.stack.wicket.model.User_;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
public class DaoUserService extends DaoEntityService<User, Long> implements UserService, ApplicationListener<ContextRefreshedEvent>
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
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        User admin = this.getDao().find(User_.username, "admin");
        if (null == admin) {
            admin = new User("admin");
            admin.addRole(Role.ADMIN);
            admin.setPassword(this.passwordEncoder.encode("admin"));
            this.getDao().persist(admin);
        }
    }

    @Override
    protected UserDao getDao()
    {
        return (UserDao) super.getDao();
    }
}
