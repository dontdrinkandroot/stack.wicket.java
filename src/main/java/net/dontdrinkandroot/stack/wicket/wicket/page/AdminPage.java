package net.dontdrinkandroot.stack.wicket.wicket.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@AuthorizeInstantiation({"ADMIN"})
public class AdminPage extends DecoratorPage<Void>
{
}
