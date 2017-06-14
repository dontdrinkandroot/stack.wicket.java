package net.dontdrinkandroot.stack.wicket.wicket.component.item;

import net.dontdrinkandroot.stack.wicket.wicket.WebSession;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignInPage;
import net.dontdrinkandroot.wicket.bootstrap.component.item.BookmarkablePageLinkItem;
import org.apache.wicket.Page;
import org.apache.wicket.model.Model;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SignInItem extends BookmarkablePageLinkItem<Void>
{
    public <C extends Page> SignInItem(String id)
    {
        super(id, Model.of("Sign In"), SignInPage.class);
    }

    @Override
    protected void onConfigure()
    {
        super.onConfigure();

        this.setVisible(!WebSession.get().isSignedIn());
    }
}
