package myself.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web global lister
 * 
 * @author Administrator
 *
 */
public class GlobalLister implements ServletContextListener {

	/**
	 * All ServletContextListeners are notified of context initialization before
	 * any filters or servlets in the web application are initialized.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) {

	}

	/**
	 * All servlets and filters will have been destroyed before any
	 * ServletContextListeners are notified of context destruction.
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {

	}

}
