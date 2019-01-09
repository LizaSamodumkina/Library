package by.etc.samodumkina.bean;

public class NeedSendOutBook {
	
	private String id;
	private User user;
	private Book book;

	public NeedSendOutBook() {
		user = new User();
		book = new Book();
	}

	public NeedSendOutBook(String id, User user, Book book) {
		this.id = id;
		this.user = user;
		this.book = book;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUserName(String userName) {
		user.setLogin(userName);
	}
	
	public String getUserName() {
		return user.getLogin();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setBookName(String bookName) {
		book.setName(bookName);
	}
	
	public String getBookName() {
		return book.getName();
	}
	
	public void setBookAuthors(String AuthorsName) {
		book.setAuthors(AuthorsName);
	}
	
	public String getBookAuthors() {
		return book.getAuthors();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeedSendOutBook other = (NeedSendOutBook) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NeedSendOutBook [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", book=");
		builder.append(book);
		builder.append("]");
		return builder.toString();
	}
}

