package net.dontdrinkandroot.stack.wicket.wicket.model;

import net.dontdrinkandroot.stack.wicket.wicket.WebSession;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class CurrentUserDetailsModel extends AbstractReadOnlyModel<UserDetails>
{
    @Override
    public UserDetails getObject()
    {
        return WebSession.get().getUser();
    }
}
