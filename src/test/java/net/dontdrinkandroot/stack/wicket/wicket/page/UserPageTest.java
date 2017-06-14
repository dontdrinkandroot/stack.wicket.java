package net.dontdrinkandroot.stack.wicket.wicket.page;

import net.dontdrinkandroot.stack.wicket.domain.service.UserService;
import net.dontdrinkandroot.stack.wicket.wicket.test.AbstractWicketTest;
import org.junit.Test;

import javax.inject.Inject;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class UserPageTest extends AbstractWicketTest
{
    @Inject
    private UserService userService;

    @Test
    public void testPermissions()
    {
        this.assertLoginRequired(UserPage.class);
        this.assertPageAccessible(UserPage.class, this.userService.loadUserByUsername("user"));
        this.assertPageInaccessible(UserPage.class, this.userService.loadUserByUsername("admin"));
    }
}
