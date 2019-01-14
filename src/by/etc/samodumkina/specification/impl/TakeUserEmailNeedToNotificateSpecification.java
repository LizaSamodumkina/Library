package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class TakeUserEmailNeedToNotificateSpecification extends SQLSpecification{

	public TakeUserEmailNeedToNotificateSpecification() {}

	@Override
	public String toSQLQuery() {
		return "select users.e_mail from takenbooks left join users on takenbooks.userIdTB = users.user_id where CURDATE() > adddate(date, 20)";
	}

}
