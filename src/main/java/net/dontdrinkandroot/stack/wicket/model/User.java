package net.dontdrinkandroot.stack.wicket.model;

import net.dontdrinkandroot.persistence.entity.GeneratedLongIdEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
public class User extends GeneratedLongIdEntity implements UserDetails
{
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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

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
        return this.roles;
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    public void removeRole(Role role)
    {
        this.roles.remove(role);
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
