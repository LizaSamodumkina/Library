package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeAdminOrderStorySpecification extends SQLSpecification {
	
	private final static String SELECT = "select orderId, login, bookName, bookAuthors, date, (adddate(date, 30)) as new_date, isReplace from bookorderstory left join users on bookorderstory.userId = users.user_id left join books on bookorderstory.bookId = books.id";

	public TakeAdminOrderStorySpecification() {}

	@Override
	public String toSQLQuery() {
		return SELECT;
	}
}
