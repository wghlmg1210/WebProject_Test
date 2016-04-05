package myself.servlet.session$cookies;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于测试 cookies
 * 
 * @author Administrator
 *
 */
public class CookiesServlet extends HttpServlet {

	private static final long serialVersionUID = 3722309172304013656L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getServletContext();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	public void destroy() {
		System.out.println("destroy()...");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init()...");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(config)...");
	}

}
