package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeUserOrderSpecification extends SQLSpecification{
	private String login;
	private final static String SELECT_BEGIN = "select orderId, login, bookName, bookAuthors, date, (adddate(date, 30)) as new_date, (adddate(date, 30) < CURDATE()) as p from bookorderstory left join users on bookorderstory.userId = users.user_id left join books on bookorderstory.bookId = books.id where isReplace = 0 and login = \"";
	private final static String SELECT_END = "\"";

	public TakeUserOrderSpecification(String login) {
		this.login = login;
	}

	@Override
	public String toSQLQuery() {
		return SELECT_BEGIN + login + SELECT_END; 
	}

}
