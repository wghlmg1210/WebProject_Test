package myself.servlet.session$cookies;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用于测试 cookies
 * 
 * @author Administrator
 *
 */
public class CookiesServlet extends HttpServlet {

	private static final long serialVersionUID = 3722309172304013656L;

	@Override
	/**
	 * session是在程序调用getSession()函数之后才创建的。
	 * sessionId是存放在name=JSESESSIONID的cookie中的（默认方式）。
	 * session调用invalidate函数之后再去调用该session的其他的函数将会抛出IllegalStateException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				System.out
				.println(cookie.getName() + " : " + cookie.getValue());
			}
		}
		HttpSession session = request.getSession();
		Integer count = (Integer) session.getAttribute("count");
		if (count == null || count != 2) {
			if (count == null) {
				session.setAttribute("count", 1);
			} else {
				session.setAttribute("count", count + 1);
			}
		} else {
			session.invalidate();
			System.out.println(session.getAttribute("name"));
			System.out.println("invalidate");
		}
		System.out.println(session);
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
	/** 该函数是在init(Config config)中被调用的，用于对扩展开放
	 * 当程序中重写了init(Config config)函数又没有调用init()函数，则该函数将不被使用 */
	public void init() throws ServletException {
		System.out.println("init()...");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(config)...");
	}

}
