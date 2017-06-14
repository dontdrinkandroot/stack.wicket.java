package net.dontdrinkandroot.stack.wicket.wicket.page;

import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SignOutPage extends net.dontdrinkandroot.wicket.bootstrap.page.SignOutPage
{
    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        response.render(new CssUrlReferenceHeaderItem("css/style.css", null, null));
    }
}
