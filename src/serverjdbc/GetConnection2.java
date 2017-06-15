package serverjdbc;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import dao.User;

public class GetConnection2 {
	ServerSocket ss;
	Socket cs;
	Connection con = null; 
	private Statement stmt = null; 
	private ResultSet rs = null; 
	private ResultSetMetaData rsmd = null;
	private static int maxId; 
	private PreparedStatement pstat;
	String sql = "";
	
	public GetConnection2() throws ClassNotFoundException, SQLException{
		this.getCon();
	}
	Connection getCon() throws ClassNotFoundException 
			, SQLException {
		String url = "jdbc:mysql://localhost:3306/atm_6";
		System.out.println(url);
		String user = "root";
		String password = "yufenger";

		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("������ɹ�!");
		con = DriverManager.getConnection(url, user, password);
		//System.out.println("������ݿ�ɹ�!");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		//System.out.println("����Statement�ɹ�!");
		return con;
	}

	/**
	 * �û�ע��
	 */
	public void addUser(User user) {
		String sql = "insert into user values(?,?,?,?)";
		try {
			pstat = con.prepareStatement(sql);
			pstat.setString(1, user.getCardId());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getUsername());
			pstat.setDouble(4, user.getDeposit());

			pstat.executeUpdate();
			// pstat.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��ʾ��������Ա����Ϣ
	// **********************************************************
	public ResultSet display() {
		try {
			rs = stmt.executeQuery("select * from user");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean find(String cardId, String string) {
		boolean valid = false;
		try {
			rs = stmt.executeQuery("select * from user where cardId='" + cardId
					+ "' and password='" + string + "'");
			System.out.println("select * from user where cardId='" + cardId
					+ "' and password='" + string + "'" + "rs:" + rs);
			if (rs.next()) { // ѭ����ر�rs
				valid = true;
				System.out.println("valid+" + valid);
			}
			Connection conn = getCon();
		} catch (Exception e) {
		}
		System.out.println("valid2+" + valid);
		return valid;
	}

	// �޸�
	public boolean saveDeposit(User xin) throws SQLException,
			ClassNotFoundException {
		boolean flag = false;
		String sql = "update user set deposit=deposit+? where cardId =?";
		Connection conn = getCon();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, xin.getDeposit());
			pst.setString(2, xin.getCardId());
			int count = pst.executeUpdate();
			pst.close(); 
			return count > 0 ? true : false; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean getDeposit(User xin) throws SQLException,
			ClassNotFoundException {
		boolean flag = false;
		String sql = "update user set deposit=deposit-? where cardId =?";
		Connection conn = getCon();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, xin.getDeposit());
			pst.setString(2, xin.getCardId());
			int count = pst.executeUpdate();
			pst.close(); 
			return count > 0 ? true : false; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void delete(Connection conn, int id) throws SQLException {
		String sql = "delete from user where cardId=?";
		try {
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, id);
			
			pstat.executeUpdate();
		} finally {
			if (null != pstat)
				pstat.close();
			if (null != conn)
				conn.close();
		}
	}

	public User selectUserById(String cardId) { 
		String sql = "select * from user where cardId = '" + cardId + "'";
		User user = null;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				user = new User();
				user.setCardId(rst.getString("cardId"));
				user.setUsername(rst.getString("username"));
				user.setPassword(rst.getString("password"));
				user.setDeposit(rst.getDouble("deposit"));
			}
			rst.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user; 
	}
}
