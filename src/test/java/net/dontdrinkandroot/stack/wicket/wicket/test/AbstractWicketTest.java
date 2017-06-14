package net.dontdrinkandroot.stack.wicket.wicket.test;

import net.dontdrinkandroot.stack.wicket.wicket.WebApplication;
import net.dontdrinkandroot.stack.wicket.wicket.WebSession;
import net.dontdrinkandroot.stack.wicket.wicket.page.SignInPage;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public abstract class AbstractWicketTest extends AbstractIntegrationTest
{
    @Autowired
    protected WebApplication wicketApplication;

    protected WicketTester wicketTester;

    @Before
    public void before()
    {
        this.wicketTester = new WicketTester(this.wicketApplication);
    }

    @After
    public void tearDown()
    {
        this.wicketTester.destroy();
    }

    protected void assertLoginRequired(Class<? extends Page> pageClass)
    {
        this.wicketTester.startPage(pageClass);
        this.wicketTester.assertRenderedPage(SignInPage.class);
    }

    protected void assertPageAccessible(Class<? extends Page> pageClass, UserDetails user)
    {
        this.signIn(user);
        this.wicketTester.startPage(pageClass);
        this.wicketTester.assertRenderedPage(pageClass);
    }

    protected void signIn(UserDetails user)
    {
        WebSession.get().signIn(user.getUsername(), user.getUsername());
    }

    protected void assertPageInaccessible(Class<? extends Page> pageClass, UserDetails user)
    {
        this.signIn(user);
        try {
            this.wicketTester.startPage(pageClass);
            Assert.fail("UnauthorizedInstantiationException expected");
        } catch (UnauthorizedInstantiationException e) {
            /* Expected */
        }
    }
}
