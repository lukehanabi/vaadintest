package com.vaadin.tutorial.addressbook;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tutorial.addressbook.backend.AddressBook;
import com.vaadin.tutorial.addressbook.backend.ContactService;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;

import javax.servlet.annotation.WebServlet;

/* User Interface written in Java.
 *
 * Define the user interface shown on the Vaadin generated web page by extending the UI class.
 * By default, a new UI instance is automatically created when the page is loaded. To reuse
 * the same instance, add @PreserveOnRefresh.
 */
@Title("Addressbook")
@Theme("valo")
public class AddressbookUI extends UI {







	/* Hundreds of widgets.
	 * Vaadin's user interface components are just Java objects that encapsulate
	 * and handle cross-browser support and client-server communication. The
	 * default Vaadin components are in the com.vaadin.ui package and there
	 * are over 500 more in vaadin.com/directory.
     */
    TextField filter = new TextField();
    Grid contactList = new Grid();
    Button newContact = new Button("New contact");

    // ContactForm is an example of a custom component class
    ContactForm contactForm = new ContactForm();

    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    ContactService service = ContactService.createDemoService();


    /* The "Main method".
     *
     * This is the entry point method executed to initialize and configure
     * the visible user interface. Executed on every browser reload because
     * a new instance is created for each web page loaded.
     */
    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }


    private void configureComponents() {
         /* Synchronous event handling.
         *
         * Receive user interaction events on the server-side. This allows you
         * to synchronously handle those events. Vaadin automatically sends
         * only the needed changes to the web page without loading a new page.
         */
        newContact.addClickListener(e -> contactForm.edit(new AddressBook()));

        filter.setInputPrompt("Filter contacts...");
        filter.addTextChangeListener(e -> refreshContacts(e.getText()));

        String Lucas = null;
        
        contactList.setContainerDataSource(new BeanItemContainer<>(AddressBook.class));
        
        contactList.setSelectionMode(Grid.SelectionMode.SINGLE);
        contactList.addSelectionListener(e
                -> contactForm.edit((AddressBook) contactList.getSelectedRow()));
        refreshContacts();
    }

    /* Robust layouts.
     *
     * Layouts are components that contain other components.
     * HorizontalLayout contains TextField and Button. It is wrapped
     * with a Grid into VerticalLayout for the left side of the screen.
     * Allow user to resize the components with a SplitPanel.
     *
     * In addition to programmatically building layout in Java,
     * you may also choose to setup layout declaratively
     * with Vaadin Designer, CSS and HTML.
     */
    private void buildLayout() {
    	
    	
    	
        HorizontalLayout actions = new HorizontalLayout(filter, newContact);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);
        
        final Label selection = new Label("-");
        actions.addComponent(selection);
        
        MenuBar.Command mycommand = new MenuBar.Command() {
            public void menuSelected(MenuItem selectedItem) {
                selection.setValue("Ordered a " +
                                   selectedItem.getText() +
                                   " from menu.");
                getUI().getPage().setLocation("/5/89");
            }  };
        
        MenuBar barmenu = new MenuBar();
    	actions.addComponent(barmenu);
    	
    	// A top-level menu item that opens a submenu
    	MenuItem drinks = barmenu.addItem("Beverages", null, null);

    	// Submenu item with a sub-submenu
    	MenuItem hots = drinks.addItem("Hot", null, null);
    	hots.addItem("Tea",
    	    new ThemeResource("icons/tea-16px.png"),    mycommand);
    	hots.addItem("Coffee",
    	    new ThemeResource("icons/coffee-16px.png"), mycommand);

    	// Another submenu item with a sub-submenu
    	MenuItem colds = drinks.addItem("Cold", null, null);
    	colds.addItem("Milk",      null, mycommand);
    	colds.addItem("Weissbier", null, mycommand);

    	// Another top-level item
    	MenuItem snacks = barmenu.addItem("Snacks", null, null);
    	snacks.addItem("Weisswurst", null, mycommand);
    	snacks.addItem("Bratwurst",  null, mycommand);
    	snacks.addItem("Currywurst", null, mycommand);
    	        
    	// Yet another top-level item
    	MenuItem servs = barmenu.addItem("Services", null, null);
    	servs.addItem("Car Service", null, mycommand);

        VerticalLayout left = new VerticalLayout(actions, contactList);
        left.setSizeFull();
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
        setContent(mainLayout);
    }

    /* Choose the design patterns you like.
     *
     * It is good practice to have separate data access methods that
     * handle the back-end access and/or the user interface updates.
     * You can further split your code into classes to easier maintenance.
     * With Vaadin you can follow MVC, MVP or any other design pattern
     * you choose.
     */
    void refreshContacts() {
        refreshContacts(filter.getValue());
    }

    private void refreshContacts(String stringFilter) {
        contactList.setContainerDataSource(new BeanItemContainer<>(
                AddressBook.class, service.findAll(stringFilter)));
        contactForm.setVisible(false);
    }




    /*  Deployed as a Servlet or Portlet.
     *
     *  You can specify additional servlet parameters like the URI and UI
     *  class name and turn on production mode when you have finished developing the application.
     */
    @WebServlet(urlPatterns = {"/5/*", "/VAADIN/*"})
    @VaadinServletConfiguration(ui = AddressbookUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }


}
