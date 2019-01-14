package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class DeleteBlockedUserSpecification extends SQLSpecification {
	private String login;

	public DeleteBlockedUserSpecification(String login) {
		this.login = login;
	}

	@Override
	public String toSQLQuery() {
		return "delete from blockedusers where blockedUserId = (select user_id from users where login = \"" + login +"\")";
	}

}
