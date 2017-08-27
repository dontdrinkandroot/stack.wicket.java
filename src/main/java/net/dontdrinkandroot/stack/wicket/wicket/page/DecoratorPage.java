package net.dontdrinkandroot.stack.wicket.wicket.page;

import net.dontdrinkandroot.stack.wicket.wicket.WebApplication;
import net.dontdrinkandroot.stack.wicket.wicket.component.item.SignInItem;
import net.dontdrinkandroot.stack.wicket.wicket.component.item.UserMenuDropDownItem;
import net.dontdrinkandroot.wicket.bootstrap.behavior.ModalRequestBehavior;
import net.dontdrinkandroot.wicket.bootstrap.component.item.BookmarkablePageLinkItem;
import net.dontdrinkandroot.wicket.bootstrap.component.navbar.Navbar;
import net.dontdrinkandroot.wicket.bootstrap.component.navbar.RepeatingNavbarNav;
import net.dontdrinkandroot.wicket.bootstrap.css.NavbarAlignment;
import net.dontdrinkandroot.wicket.bootstrap.css.NavbarPosition;
import net.dontdrinkandroot.wicket.bootstrap.page.BootstrapPage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DecoratorPage<T> extends BootstrapPage<T>
{
    public static final String MODAL_ID = "modal";

    public static final String NAVBAR_ID = "navbar";

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        Navbar navbar = new Navbar(NAVBAR_ID)
        {
            @Override
            protected Component createBrand(String id)
            {
                return DecoratorPage.this.createBrand(id);
            }

            @Override
            protected void populateCollapseItems(RepeatingView collapseItemView)
            {
                RepeatingNavbarNav navbarLeft = new RepeatingNavbarNav(collapseItemView.newChildId())
                {
                    @Override
                    protected void populateItems(RepeatingView itemView)
                    {
                        DecoratorPage.this.populateNavbarLeftItems(itemView);
                    }
                };
                collapseItemView.add(navbarLeft);

                RepeatingNavbarNav navbarRight = new RepeatingNavbarNav(collapseItemView.newChildId())
                {
                    @Override
                    protected void populateItems(RepeatingView itemView)
                    {
                        DecoratorPage.this.populateNavbarRightItems(itemView);
                    }
                };
                navbarRight.setAlignment(NavbarAlignment.RIGHT);
                collapseItemView.add(navbarRight);
            }
        };
        navbar.setPosition(NavbarPosition.FIXED_TOP);
        this.add(navbar);

        WebMarkupContainer modal = new WebMarkupContainer(MODAL_ID);
        modal.setOutputMarkupId(true);
        this.add(modal);

        this.add(new ModalRequestBehavior(MODAL_ID));
    }

    protected Component createBrand(String id)
    {
        BookmarkablePageLink brand = new BookmarkablePageLink(id, WebApplication.get().getHomePage());
        brand.setBody(Model.of("Home"));

        return brand;
    }

    protected void populateNavbarLeftItems(RepeatingView itemView)
    {
        itemView.add(new BookmarkablePageLinkItem<Void>(
                itemView.newChildId(),
                Model.of("UserPage"),
                UserPage.class
        ));
        itemView.add(new BookmarkablePageLinkItem<Void>(
                itemView.newChildId(),
                Model.of("AdminPage"),
                AdminPage.class
        ));
    }

    protected void populateNavbarRightItems(RepeatingView itemView)
    {
        itemView.add(new UserMenuDropDownItem(itemView.newChildId()));
        itemView.add(new SignInItem(itemView.newChildId()));
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        response.render(new CssUrlReferenceHeaderItem("css/style.css", null, null));
    }
}
