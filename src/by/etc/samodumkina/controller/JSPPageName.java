package by.etc.samodumkina.controller;

public enum JSPPageName {

	CATALOG_PAGE ("/WEB-INF/jsp/main.jsp"),
	ERROR_PAGE ("/WEB-INF/jsp/errorpage.jsp"),
	MAIN_PAGE ("/index.jsp"),
	REGISTRATION_PAGE ("/WEB-INF/jsp/registration.jsp"),
	LIKED_BOOKS_PAGE ("/WEB-INF/jsp/likedbooks.jsp"),
	ORDERS_STORY_PAGE ("/WEB-INF/jsp/ordersstory.jsp"), 
	ORDERS_PAGE ("/WEB-INF/jsp/orders.jsp"),
	ADMIN_MAIN_PAGE("/WEB-INF/jsp/adminMainPage.jsp"),
	ADMIN_ORDER_STORY_PAGE("/WEB-INF/jsp/adminOrderStory.jsp"),
	ADMIN_ADD_NEW_BOOK_PAGE("/WEB-INF/jsp/adminAddNewBook.jsp"),
	ADMIN_DELETE_BOOK_PAGE("/WEB-INF/jsp/adminDeleteBook.jsp");
	
	private String url;
	
	JSPPageName(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}
}
