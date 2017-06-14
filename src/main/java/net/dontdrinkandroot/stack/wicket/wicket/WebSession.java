package net.dontdrinkandroot.stack.wicket.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class WebSession extends AuthenticatedWebSession
{
    private Logger logger = LoggerFactory.getLogger(WebSession.class);

    @SpringBean(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    public WebSession(Request request)
    {
        super(request);
        Injector.get().inject(this);
    }

    @Override
    public Roles getRoles()
    {
        Roles roles = new Roles();
        this.getRolesIfSignedIn(roles);

        return roles;
    }

    private void getRolesIfSignedIn(Roles roles)
    {
        if (this.isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            this.addRolesFromAuthentication(roles, authentication);
        }
    }

    private void addRolesFromAuthentication(Roles roles, Authentication authentication)
    {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }

    @Override
    protected boolean authenticate(String username, String password)
    {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.isAuthenticated()) {
                this.bind();
                return true;
            }
        } catch (AuthenticationException e) {
            this.logger.warn("Could not authenticate", e);
            /* Noop */
        }

        return false;
    }

    public UserDetails getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication
                || null == authentication.getPrincipal()
                || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public static WebSession get()
    {
        return (WebSession) org.apache.wicket.protocol.http.WebSession.get();
    }
}
