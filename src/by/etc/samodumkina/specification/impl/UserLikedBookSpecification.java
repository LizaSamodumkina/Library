package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class UserLikedBookSpecification extends SQLSpecification {
	private String login;

	public UserLikedBookSpecification(String login) {
		this.login = login;
	}

	@Override
	public String toSQLQuery() {
		return "select userlikedbooks.id as id, bookName, bookAuthors, annotation from userlikedbooks left join users on userlikedbooks.userIdLB = users.login left join books on userlikedbooks.bookIdLB = books.id where users.login = \"" + this.login + "\"";
	}

}
