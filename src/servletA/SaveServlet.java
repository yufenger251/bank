package servletA;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetConnection1;
import dao.User;

public class SaveServlet extends HttpServlet { // �޸�

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { // 
		try {
		String idStr = req.getParameter("cardId");
//		String pwd = req.getParameter("password");
		String deposit = req.getParameter("deposit");
		User u = new User();
		u.setCardId(idStr);
		u.setDeposit(Double.valueOf(deposit));
		GetConnection1 g =new GetConnection1();
		if(g.selectUserById(idStr) == null){
			new TCPClient().saveDeposit(u);
		}else{
				g.saveDeposit(u);
		}
		u = new TCPClient().selectUserById(idStr);
		req.setAttribute("cardId", idStr);
		resp.sendRedirect("/bank_hello/successupdate.jsp");
		System.out.println("has sendRedirect");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}