package com.goparty.data.model;

import java.util.Date;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserData {
	 private String id;
	 private String userName;
	 private String password; 
	 private String nickName;
	 private String phone;
	 
	 private Date birthdate;
	 
	 private String gender;
	 private String location;
	 private String signature;
	 private String photo;
	 private String weChat;
	 private String QQ;
	 private String weibo;
	 
	@Id 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	@Override
	public String toString() {
		return "UserData [id=" + id + ", userName=" + userName + ", password="
				+ password + ", nickName=" + nickName + ", phone=" + phone
				+ ", birthdate=" + birthdate + ", gender=" + gender
				+ ", location=" + location + ", signature=" + signature
				+ ", photo=" + photo + ", weChat=" + weChat + ", QQ=" + QQ
				+ ", weibo=" + weibo + "]";
	}
}
