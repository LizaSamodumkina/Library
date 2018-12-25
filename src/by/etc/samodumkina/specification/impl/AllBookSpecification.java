package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class AllBookSpecification extends SQLSpecification{
	private final static String SELECT = "select * from books";

	@Override
	public String toSQLQuery() {
		return SELECT;
	}

}
