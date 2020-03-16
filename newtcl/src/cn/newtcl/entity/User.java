package cn.newtcl.entity;

public class User {

	private Integer id = null;
	private String phone = null;
	private String password = null;
	private String name = null;
	private String email = null;
	private Double balance = null;
	private Integer managerId = null;
	private String payPassword = null;
	private String qqOpenId = null;
	private String avatar = null;
	private String alipayOpenid = null;
	private String wechatOpenid = null;
	private String jurisdiction = null;
	private Integer address = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAlipayOpenid() {
		return alipayOpenid;
	}

	public void setAlipayOpenid(String alipayOpenid) {
		this.alipayOpenid = alipayOpenid;
	}

	public String getWechatOpenid() {
		return wechatOpenid;
	}

	public void setWechatOpenid(String wechatOpenid) {
		this.wechatOpenid = wechatOpenid;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
	
	public String toString() {
		
		return id +"------"+ phone +"------"+ password +"------"+ name +"------"+ email 
				+"------"+ balance +"------"+ managerId +"------"+ payPassword 
				+"------"+ qqOpenId +"------"+ avatar +"------"+ alipayOpenid 
				+"------"+ wechatOpenid +"------"+ jurisdiction ;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
}