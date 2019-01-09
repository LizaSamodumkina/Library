package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeNeedSendOutBookToReadingRoomByUserSpecification extends SQLSpecification {
	
	private String login;

	public TakeNeedSendOutBookToReadingRoomByUserSpecification(String login) {
		this.login = login;
	}

	@Override
	public String toSQLQuery() {
		return  "select needsendoutbooks.id, login, bookName, bookAuthors from needsendoutbooks left join users on needsendoutbooks.userIdNS = users.user_id left join books on needsendoutbooks.bookIdNS = books.id where needsendoutbooks.isinreadingroom = 1 and login = \"" + login + "\"";
	}

}
