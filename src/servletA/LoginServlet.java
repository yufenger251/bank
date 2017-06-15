package servletA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GetConnection1;
import dao.User;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cardId = new String(request.getParameter("cardId").getBytes(
				"ISO8859_1"), "GBK");
		String pwd = new String(request.getParameter("password").getBytes(
				"ISO8859_1"), "UTF-8");
		User user = new User(cardId, pwd);
		boolean isLogin;
		System.out.println("heh");
		try {
		GetConnection1 g =new GetConnection1();
		
			if(!g.find(cardId, pwd)){
				TCPClient t =new TCPClient();
				isLogin = t.find(cardId, pwd);
				user =new TCPClient().selectUserById(cardId);
			}else{
				isLogin = g.find(cardId, pwd);
				user = g.selectUserById(cardId);
			}
		System.out.println(isLogin);
		if (isLogin) {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String m = inetAddress.getHostAddress();
			int n = request.getRemotePort();
			 System.out.println(m+"*********"+ n);
			HttpSession session = request.getSession();
			 session.setAttribute("address", m);
			 session.setAttribute("port", n);
			session.setAttribute("user", user);
			session.setAttribute("cardId", cardId);
			session.setAttribute("password", user.getPassword());
			session.setAttribute("username", user.getUsername());
			System.out.println(user.getUsername());
			session.setAttribute("deposit", user.getDeposit());
		//	 response.sendRedirect("success.jsp");
		//	request.getRequestDispatcher("success.jsp").forward(request,
			//		response);
			request.getRequestDispatcher("/ShowServlet").forward(request,
			 response);
		} else
			response.sendRedirect("login.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void init() throws ServletException {
	}

}
