package by.etc.samodumkina.controller;

public enum JSPPageName {

	CATALOG_PAGE ("/jsp/main.jsp"),
	ERROR_PAGE ("/WEB-INF/jsp/errorpage.jsp"),
	MAIN_PAGE ("/index.jsp"),
	REDIRECT_MAIN_PAGE("/WebApp/"),
	REGISTRATION_PAGE ("/WEB-INF/jsp/registration.jsp"),
	LIKED_BOOKS_PAGE ("/jsp/likedbooks.jsp"),
	ORDERS_STORY_PAGE ("/WEB-INF/jsp/ordersstory.jsp"), 
	ORDERS_PAGE ("/WEB-INF/jsp/orders.jsp"),
	ADMIN_MAIN_PAGE("/jsp/adminMainPage.jsp"),
	ADMIN_ORDER_STORY_PAGE("/WEB-INF/jsp/adminOrderStory.jsp"),
	ADMIN_ADD_NEW_BOOK_PAGE("/jsp/adminAddNewBook.jsp"),
	ADMIN_DELETE_BOOK_PAGE("/WEB-INF/jsp/adminDeleteBook.jsp"),
	BLOCKED_USERS_PAGE("/jsp/blockedUsersPage.jsp");
	
	private String url;
	
	JSPPageName(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}
}
