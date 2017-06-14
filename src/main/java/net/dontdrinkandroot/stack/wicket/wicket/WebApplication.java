package net.dontdrinkandroot.stack.wicket.wicket;

import net.dontdrinkandroot.stack.wicket.wicket.page.HomePage;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignInPage;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignOutPage;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class WebApplication extends AuthenticatedWebApplication implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Override
    protected void init()
    {
        super.init();

        this.getMarkupSettings().setStripWicketTags(true);
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this, this.applicationContext, true));

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}
