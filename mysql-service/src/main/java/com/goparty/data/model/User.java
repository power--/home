package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name = "user")
@Entity
@Table(name="gp_user")
public class User {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)// //can't create table automatically after add this
	 private String id;
	 
	 private String phone;
	 private String loginId;
	 private String password; 
	 private String nickName;
	 
	 @DateTimeFormat(pattern="yyyy-mm-dd")
	 private Date birthdate;
	 
	 private String gender;
	 private String location;
	 private String signature;
	 private String photo;
	 private String weChat;
	 private String qq;
	 private String weibo;
	
	 //@Transient
	 @ManyToMany(fetch = FetchType.EAGER)    
	 @JoinTable(name="gp_user_friend",joinColumns=@JoinColumn(name="userId"),
	                    inverseJoinColumns=@JoinColumn(name="friendId"))
	 private List<User> friends;
	  
	 @Transient
	 private String token;
	 
	 
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
 
	 
//	@ManyToMany(fetch = FetchType.LAZY,mappedBy="attendees",cascade=CascadeType.ALL)
//	 private List<Event> events;
//	 
//	 
//	public List<Event> getEvents() {
//		return events;
//	}
//	public void setEvents(List<Event> events) {
//		this.events = events;
//	}
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
