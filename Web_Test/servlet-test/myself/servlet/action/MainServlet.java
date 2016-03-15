package myself.servlet.action;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Mr.Huang
 * 
 */
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 2707665498350000025L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		session.invalidate();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
