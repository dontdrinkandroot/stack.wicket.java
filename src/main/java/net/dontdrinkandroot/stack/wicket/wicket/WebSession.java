package net.dontdrinkandroot.stack.wicket.wicket;

import com.giffing.wicket.spring.boot.starter.configuration.extensions.external.spring.security.SecureWebSession;
import org.apache.wicket.request.Request;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class WebSession extends SecureWebSession
{
    public WebSession(Request request)
    {
        super(request);
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
