package application;

public class CastVO {
	
	private String name;
	private String role;
	
	 
	public CastVO() {}
	
	public CastVO(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "CastVO [name=" + name + ", role=" + role + "]";
	}
	
	

}
