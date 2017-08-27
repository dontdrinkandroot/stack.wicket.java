package net.dontdrinkandroot.stack.wicket.wicket.page;

import net.dontdrinkandroot.stack.wicket.domain.model.Role;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@AuthorizeInstantiation(Role.USER)
public class UserPage extends DecoratorPage<Void>
{
}
