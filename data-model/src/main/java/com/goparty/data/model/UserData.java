package com.goparty.data.model;

import java.io.Serializable;
import java.util.Date;












import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql; 
import org.springframework.data.mongodb.core.mapping.Document;


@Entity
@NoSql(dataType="users", dataFormat=DataFormatType.MAPPED)
//@Table(name = "users")
@Document(collection = "users")
public class UserData implements Serializable{
     @Id
     @GeneratedValue
     @Field(name="_id")
	 private String id; 
	 private String userName;

	 private String password; 
	 @Field(name="nickName")
	 private String nickName;
	 private String phone;
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date birthdate;
	 
	 private String gender;
	 private String location;
	 private String signature;
	 private String photo;
	 private String weChat;
	 private String QQ;
	 private String weibo;
	 
	  @Id
	     @GeneratedValue
	     @Field(name="_id")
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
