package com.lanqiao.shopping.domain;


/**
 * 数据库里面的Users表的结构
 * 
 * 也就是用户对象
 * @author Administrator
 *
 */
public class Users {
	private String uuid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private String birthday;//生日改成字符串
	private String sex;
	private int state; //#状态：0=未激活，1=已激活
	private String code; //#激活码
	public Users(String uuid, String username, String password, String name, String email, String telephone,
			String birthday, String sex, int state, String code) {
		super();
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [uuid=" + uuid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex
				+ ", state=" + state + ", code=" + code + "]";
	}
	
	
}
