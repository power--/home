package com.goparty.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import com.goparty.data.constant.ParticipanceType;

@XmlRootElement(name = "user")
@Entity
@Table(name="gp_user")
@JsonIgnoreProperties({"loginId","password","token"})
public class User {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)// //can't create table automatically after add this
	 private String id;
	 
	 private String phone;
	 private String loginId;
	 private String password; 
	 private String nickName;
	 
//	 @DateTimeFormat(pattern="yyyy-mm-dd")
	 private Date birthdate;
	 
	 private String gender;
	 private String location;
	 private String signature;
	 private String photo;
	 private String weChat;
	 private String qq;
	 private String weibo;
	 
	 @Transient
	 private String token;	 
	 @Transient	 
	 private boolean admin;
	 @Transient
	 private ParticipanceType participance;
	   
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
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
 
 
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
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
 
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	
	
	public ParticipanceType getParticipance() {
		return participance;
	}
	public void setParticipance(ParticipanceType participance) {
		this.participance = participance;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", loginId=" + loginId
				+ ", password=" + password + ", nickName=" + nickName
				+ ", birthdate=" + birthdate + ", gender=" + gender
				+ ", location=" + location + ", signature=" + signature
				+ ", photo=" + photo + ", weChat=" + weChat + ", qq=" + qq
				+ ", weibo=" + weibo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
