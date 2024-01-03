package Resevation;

public class ResevationVO {

	private String name;
	private String chars;

	public ResevationVO(String name, String chars) {
		this.name = name;
		this.chars = chars;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getChars() {
		return chars;
	}


	public void setChars(String chars) {
		this.chars = chars;
	}


	@Override
	public String toString() {
		return "ResevationVO [name=" + name + ", chars=" + chars + "]";
	}
	
	
}
