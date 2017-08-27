package net.dontdrinkandroot.stack.wicket.wicket;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import net.dontdrinkandroot.stack.wicket.wicket.page.HomePage;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignInPage;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignOutPage;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.stereotype.Component;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Component
public class WebApplication extends WicketBootSecuredWebApplication
{
    @Override
    protected void init()
    {
        super.init();

        this.mountPage("signin", SignInPage.class);
        this.mountPage("signout", SignOutPage.class);
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
    {
        return WebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass()
    {
        return SignInPage.class;
    }
}
