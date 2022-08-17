package by.itac.project01.bean;

public enum Role {
	
	ADMIN("admin"),
	USER("user"),
	GUEST("guest");

	private String title;

	Role(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	
}
