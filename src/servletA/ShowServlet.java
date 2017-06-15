package servletA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetConnection1;
import dao.User;

public class ShowServlet extends HttpServlet { // ��ʾȫ�����

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("req:"+req);
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cardId = req.getParameter("cardId");
		System.out.println("cardId"+cardId);
		
		GetConnection1 g = null;
		try {
			g = new GetConnection1();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = null;
		if(g.selectUserById(cardId) == null){
			 u = new TCPClient().selectUserById(cardId);
		}else{
			 u = g.selectUserById(cardId);
		}
		req.getSession().setAttribute("user", u);
		req.getSession().setAttribute("cardId", u.getCardId());
		req.getSession().setAttribute("username", u.getUsername());
		req.getSession().setAttribute("deposit", u.getDeposit());
		System.out.println(u.getDeposit());
		req.getSession().setAttribute("password", u.getPassword());
		System.out.println(u.getPassword());
		resp.sendRedirect("/bank_hello/show.jsp");
//		req.getRequestDispatcher("/show.jsp").forward(req, resp);
	}
}
