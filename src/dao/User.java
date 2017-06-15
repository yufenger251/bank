package dao;

import java.io.Serializable;

public class User implements Serializable{
		private String cardId;
		private String password;
		private Double deposit;
		private String username;
		public User(String cardId,String password){
			this.cardId =cardId;
			this.deposit = 0.0;
			this.password=password;
		}
		public User(){
			
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}

		public String getCardId() {
			return cardId;
		}
		public void setCardId(String cardId) {
			this.cardId = cardId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Double getDeposit() {
			return deposit;
		}
		public void setDeposit(Double deposit) {
			this.deposit = deposit;
		}
		
}
