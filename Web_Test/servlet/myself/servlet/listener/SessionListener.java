package myself.servlet.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent httpsessionbindingevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent httpsessionbindingevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(
			HttpSessionBindingEvent httpsessionbindingevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		// TODO Auto-generated method stub

	}

}
