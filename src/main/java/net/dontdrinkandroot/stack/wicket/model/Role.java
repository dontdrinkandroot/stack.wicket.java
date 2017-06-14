package net.dontdrinkandroot.stack.wicket.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public enum Role implements GrantedAuthority
{
    ADMIN,
    USER;

    public static final String ROLE_ADMIN = ADMIN.getAuthority();
    public static final String ROLE_USER = USER.getAuthority();

    @Override
    public String getAuthority()
    {
        return this.name();
    }
}
