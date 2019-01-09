package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeNeedSendOutBookToHomeSpecification extends SQLSpecification {

	private final static String QUERY = "select needsendoutbooks.id, login, bookName, bookAuthors from needsendoutbooks left join users on needsendoutbooks.userIdNS = users.user_id left join books on needsendoutbooks.bookIdNS = books.id where needsendoutbooks.isinreadingroom = 0";

	@Override
	public String toSQLQuery() {
		return QUERY;
	}

}
