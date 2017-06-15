package servletA;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import dao.User;

public class TCPClient {
	Socket cs;
	private OutputStream os;
	private ObjectOutputStream oos;
	private  ObjectInputStream is;
	private boolean flag;
	
	public TCPClient() {
		try {
				cs = new Socket("192.168.43.37", 1234);// 创建端口
			//	cs = new Socket("127.0.0.1", 1234);// 创建端口
				os = cs.getOutputStream();
			} catch (UnknownHostException e) {
				System.err.println("Failed to get Server hostname");
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Failed to create socket");
				System.exit(1);
			}
//			os.close();
//			cs.close();
//			// �ر� client socket
//			System.out.println("client is closed...");
	//	} //catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public boolean find(String cardId, String pwd) {
		try{
		User u = new User(cardId,pwd);
		oos = new ObjectOutputStream(os);
		oos.writeObject("1");
		oos.writeObject(u);
		
		is = new ObjectInputStream(cs.getInputStream());
		if(is.readObject().equals("true")) {
			flag = true;
		}
		 else {
			 flag = false;
		}
//		
//		oos.close();
//		os.close();
//		cs.close();
//		// �ر� client socket
//		System.out.println("client is closed...");
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public User selectUserById(String cardId) {
		User u = null;
		try{
			oos = new ObjectOutputStream(os);
			oos.writeObject("2");
			oos.writeObject(cardId);
			ObjectInputStream is = new ObjectInputStream(
					cs.getInputStream());
		    u = (User)is.readObject();
		    System.out.print("");
			}catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return u;
	}

	public void saveDeposit(User u) {
		try{
			oos = new ObjectOutputStream(os);
			oos.writeObject("3");
			oos.writeObject(u);
			}catch (IOException e) {
				e.printStackTrace();
			}
		return;
	}

	public void getDeposit(User u) {
		try{
			oos = new ObjectOutputStream(os);
			oos.writeObject("3");
			oos.writeObject(u);
			
			}catch (IOException e) {
				e.printStackTrace();
			}
		return;
	}
}
