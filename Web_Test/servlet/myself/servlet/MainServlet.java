package myself.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		System.out.println(req.getSession().getId());
		Cookie[] cookies = req.getCookies();
		for(Cookie co : cookies) {
			System.out.println(co.getName() + " " + co.getValue());
		}
		Cookie cookie = new Cookie("name", "huang");
		cookie.setMaxAge(60000);
		resp.addCookie(cookie);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// init(ServletConfig config)函数中调用init()函数
		// 所以要是重写了init(ServletConfig config)函数 再去重写init()就没有用了
		// config 能获取到servlet name/servlet context/init parameter
		// config.getInitParameterNames();
		// config.getServletName();
		// config.getServletContext();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
