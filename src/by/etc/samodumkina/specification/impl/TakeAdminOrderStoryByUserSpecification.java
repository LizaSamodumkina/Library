package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeAdminOrderStoryByUserSpecification extends SQLSpecification{
	
	private String login; 
	private final static String SELECT = "select orderId, login, bookName, bookAuthors, date, (adddate(date, 30)) as new_date, isReplace from bookorderstory left join users on bookorderstory.userId = users.user_id left join books on bookorderstory.bookId = books.id where login = \"";
	private final static String SELECT_END = "\"";

	public TakeAdminOrderStoryByUserSpecification(String login) {
		this.login = login;
	}

	@Override
	public String toSQLQuery() {
		return SELECT + login + SELECT_END;
	}

}
