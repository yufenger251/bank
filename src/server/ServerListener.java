package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {
	int num=0;
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(1234); // �˿�
			while (true) {
				
				Socket socket = serverSocket.accept();// 
				System.out.println("-------");
				new ReceiveSocket(socket,num).start();// 
				num++;
				System.out.println("new ReceiveSocket-------");
			}
		} catch (IOException e) {
			System.out.println("no");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ServerListener().start(); // 
	}
}
