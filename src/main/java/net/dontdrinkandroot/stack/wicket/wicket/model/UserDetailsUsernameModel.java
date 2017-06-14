package net.dontdrinkandroot.stack.wicket.wicket.model;

import net.dontdrinkandroot.wicket.model.AbstractChainedReadonlyModel;
import org.apache.wicket.model.IModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class UserDetailsUsernameModel extends AbstractChainedReadonlyModel<UserDetails, String>
{
    public UserDetailsUsernameModel(IModel<? extends UserDetails> parent)
    {
        super(parent);
    }

    @Override
    public String getObject()
    {
        return this.getParentObject().getUsername();
    }
}
