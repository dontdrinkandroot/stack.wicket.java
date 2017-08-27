package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.stack.wicket.domain.model.Role;
import net.dontdrinkandroot.stack.wicket.domain.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private UserService userService;

    private Environment environment;

    @Inject
    public DatabaseInitializer(UserService userService, Environment environment)
    {
        this.userService = userService;
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        List<String> activeProfiles = Arrays.asList(this.environment.getActiveProfiles());

        try {
            this.userService.loadUserByUsername("admin");
        } catch (UsernameNotFoundException e) {
            User admin = new User("admin");
            admin.addRole(Role.ADMIN);
            this.userService.setPassword(admin, "admin");
            this.userService.save(admin);
        }

        if (activeProfiles.contains("test") || activeProfiles.contains("development")) {
            try {
                this.userService.loadUserByUsername("user");
            } catch (UsernameNotFoundException e) {
                User user = new User("user");
                user.addRole(Role.USER);
                this.userService.setPassword(user, "user");
                this.userService.save(user);
            }
        }
    }
}
