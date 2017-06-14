package net.dontdrinkandroot.stack.wicket.wicket.component.item;

import net.dontdrinkandroot.stack.wicket.wicket.model.CurrentUserDetailsModel;
import net.dontdrinkandroot.stack.wicket.wicket.model.UserDetailsUsernameModel;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignOutPage;
import net.dontdrinkandroot.wicket.bootstrap.component.item.BookmarkablePageLinkItem;
import net.dontdrinkandroot.wicket.bootstrap.component.item.RepeatingDropdownItem;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeActions;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@AuthorizeActions(actions = {@AuthorizeAction(action = Action.RENDER, roles = {"USER", "ADMIN"})})
public class UserMenuDropDownItem extends RepeatingDropdownItem<UserDetails>
{
    public UserMenuDropDownItem(String id)
    {
        super(id, new UserDetailsUsernameModel(new CurrentUserDetailsModel()), new CurrentUserDetailsModel());
    }

    @Override
    protected void populateItems(RepeatingView itemView)
    {
        itemView.add(new BookmarkablePageLinkItem<Void>(
                itemView.newChildId(),
                Model.of("Sign Out"),
                SignOutPage.class
        ));
    }
}
