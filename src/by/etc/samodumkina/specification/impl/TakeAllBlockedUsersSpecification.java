package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeAllBlockedUsersSpecification extends SQLSpecification {

	public TakeAllBlockedUsersSpecification() {}

	@Override
	public String toSQLQuery() {
		return "select login from blockedusers left join users on blockedusers.blockedUserId = users.user_id";
	}

}
