package net.dontdrinkandroot.stack.wicket.domain.service;

import net.dontdrinkandroot.stack.wicket.domain.model.User;
import net.dontdrinkandroot.stack.wicket.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
@Transactional(readOnly = true)
public class RepositoryUserService implements UserService
{
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Inject
    public RepositoryUserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails userDetails = this.userRepository.findByUsername(username);
        if (null == userDetails) {
            throw new UsernameNotFoundException(String.format("No user found for name: %s", username));
        }

        return userDetails;
    }

    @Override
    @Transactional
    public void setPassword(User user, String password)
    {
        user.setPassword(this.passwordEncoder.encode(password));
    }

    @Override
    @Transactional
    public User save(User user)
    {
        return this.userRepository.save(user);
    }
}
