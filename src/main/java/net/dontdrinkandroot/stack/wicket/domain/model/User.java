package net.dontdrinkandroot.stack.wicket.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId()
    {
        return this.id;
    }

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @Column
    private LocalDateTime credentialsExpiry;

    @Column
    private LocalDateTime accountExpiry;

    @Column(nullable = false)
    private boolean locked = false;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column
    private String roles;

    public User()
    {
        /* Default constructor */
    }

    public User(String username)
    {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    public void addRole(String role)
    {
        Collection<String> roles = this.getRoles();
        if (!roles.contains(role)) {
            roles.add(role);
        }

        this.setRoles(roles);
    }

    public void removeRole(String role)
    {
        Collection<String> roles = this.getRoles();
        roles.remove(role);

        this.setRoles(roles);
    }

    public Collection<String> getRoles()
    {
        List<String> roles = new ArrayList<>();
        if (null != this.roles) {
            roles.addAll(Arrays.asList(this.roles.split(",")));
        }

        return roles;
    }

    public void setRoles(Collection<String> roles)
    {
        this.roles = String.join(",", roles);
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return (null == this.accountExpiry || this.accountExpiry.isAfter(LocalDateTime.now()));
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return (null == this.credentialsExpiry || this.credentialsExpiry.isAfter(LocalDateTime.now()));
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LocalDateTime getCredentialsExpiry()
    {
        return this.credentialsExpiry;
    }

    public void setCredentialsExpiry(LocalDateTime credentialsExpiry)
    {
        this.credentialsExpiry = credentialsExpiry;
    }

    public LocalDateTime getAccountExpiry()
    {
        return this.accountExpiry;
    }

    public void setAccountExpiry(LocalDateTime accountExpiry)
    {
        this.accountExpiry = accountExpiry;
    }

    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
