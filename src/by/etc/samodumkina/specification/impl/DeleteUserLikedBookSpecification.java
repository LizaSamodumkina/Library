package by.etc.samodumkina.specification.impl;

import by.etc.samodumkina.specification.SQLSpecification;

public class DeleteUserLikedBookSpecification extends SQLSpecification {
	private String likedBookId;

	public DeleteUserLikedBookSpecification(String likedBookId) {
		this.likedBookId = likedBookId;
	}

	@Override
	public String toSQLQuery() {
		return "delete from userlikedbooks where id = " + likedBookId;
	}

}
