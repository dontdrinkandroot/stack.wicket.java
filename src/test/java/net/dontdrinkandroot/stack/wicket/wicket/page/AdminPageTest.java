package net.dontdrinkandroot.stack.wicket.wicket.page;

import net.dontdrinkandroot.stack.wicket.domain.service.UserService;
import net.dontdrinkandroot.stack.wicket.wicket.test.AbstractWicketTest;
import org.junit.Test;

import javax.inject.Inject;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AdminPageTest extends AbstractWicketTest
{
    @Inject
    private UserService userService;

    @Test
    public void testPermissions()
    {
        this.assertLoginRequired(AdminPage.class);
        this.assertPageAccessible(AdminPage.class, this.userService.loadUserByUsername("admin"));
        this.assertPageInaccessible(AdminPage.class, this.userService.loadUserByUsername("user"));
    }
}
