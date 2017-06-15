package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.SQLException;

import serverjdbc.GetConnection2;
import dao.User;


public class ReceiveSocket extends Thread {
	private static final User String = null;
	Socket cs;
	ObjectOutputStream os;
	InputStream f;
	ObjectInputStream rs = null;
	private int num;
	private String o;

	public ReceiveSocket(Socket s,int num) {
		cs = s;
		this.num = num;
		System.out.print("Accepted Client:" + num);
	}

	public void run() {
	
		try {
			os = new ObjectOutputStream(cs.getOutputStream());
			
			rs = new ObjectInputStream(cs.getInputStream());
			String i =(String) rs.readObject();
			if(i.equals("1")){
				User u = (User)rs.readObject();
				GetConnection2 g =new GetConnection2();
				String b = Boolean.toString(g.find(u.getCardId(), u.getPassword()));
				os.writeObject(b);
			}else if(i.equals("2")){
				String cardId = (String) rs.readObject();
				GetConnection2 g =new GetConnection2();
				User u = g.selectUserById(cardId);
				os.writeObject(u);
			}else if(i.equals("3")){
				User u = (User)rs.readObject();
				GetConnection2 g =new GetConnection2();
				g.getDeposit(u);
			}else{}
			String i1 =(String) rs.readObject();
			if(i1.equals("1")){
				User u = (User)rs.readObject();
				GetConnection2 g =new GetConnection2();
				boolean b = g.find(u.getCardId(), u.getPassword());
				os.writeObject(b);
			}else if(i1.equals("2")){
				String cardId = (String) rs.readObject();
				GetConnection2 g =new GetConnection2();
				User u = g.selectUserById(cardId);
				os.writeObject(u);
			}else if(i1.equals("3")){
				User u = (User)rs.readObject();
				GetConnection2 g =new GetConnection2();
				g.getDeposit(u);
			}else{}
			os.flush();
//			System.out.print("stop server socket");
//			rs.close();
//			f.close();
//			os.close();
		} catch (IOException e1) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
