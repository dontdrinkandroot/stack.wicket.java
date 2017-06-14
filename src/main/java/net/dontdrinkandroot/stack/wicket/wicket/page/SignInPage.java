package net.dontdrinkandroot.stack.wicket.wicket.page;

import net.dontdrinkandroot.stack.wicket.wicket.WebSession;
import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SignInPage extends net.dontdrinkandroot.wicket.bootstrap.page.SignInPage
{
    public SignInPage(PageParameters parameters)
    {
        super(parameters);
    }

    @Override
    protected boolean isSignedIn()
    {
        return WebSession.get().isSignedIn();
    }

    @Override
    protected boolean signIn(String username, String password)
    {
        return WebSession.get().signIn(username, password);
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        response.render(new CssUrlReferenceHeaderItem("css/style.css", null, null));
    }
}
