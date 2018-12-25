package by.etc.samodumkina.controller;

public enum JSPPageName {

	CATALOG_PAGE ("/WEB-INF/jsp/main.jsp"),
	ERROR_PAGE ("/WEB-INF/jsp/errorpage.jsp"),
	MAIN_PAGE ("/index.jsp"),
	REGISTRATION_PAGE ("/WEB-INF/jsp/registration.jsp"),
	LIKED_BOOKS_PAGE ("/WEB-INF/jsp/likedbooks.jsp");
	
	private String url;
	
	JSPPageName(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}
}
