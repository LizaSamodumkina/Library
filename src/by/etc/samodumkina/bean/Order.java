package by.etc.samodumkina.bean;

public class Order {
	
	private String id;
	private User user;
	private Book book;
	private String orderDate;
	private String dateOfExpiry;
	private String isExpired;
	private String isReplace;

	public Order() {
		user = new User();
		book = new Book();
	}
	
	public Order(String id, User user, Book book, String orderDate, String dateOfExpiry, String isExpired,
			String isReplace) {
		this.id = id;
		this.user = user;
		this.book = book;
		this.orderDate = orderDate;
		this.dateOfExpiry = dateOfExpiry;
		this.isExpired = isExpired;
		this.isReplace = isReplace;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUserName(String userName) {
		this.user.setLogin(userName);
	}
	
	public String getUserName() {
		return this.user.getLogin();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setBookName(String bookName) {
		this.book.setName(bookName);
	}
	
	public String getBookName() {
		return this.book.getName();
	}
	
	public void setBookAuthors(String authors) {
		this.book.setAuthors(authors);
	}
	
	public String getBookAuthors() {
		return this.book.getAuthors();
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public String getIsReplace() {
		return isReplace;
	}

	public void setIsReplace(String isReplace) {
		this.isReplace = isReplace;
	}

	public String getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((dateOfExpiry == null) ? 0 : dateOfExpiry.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isExpired == null) ? 0 : isExpired.hashCode());
		result = prime * result + ((isReplace == null) ? 0 : isReplace.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
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
		Order other = (Order) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (dateOfExpiry == null) {
			if (other.dateOfExpiry != null)
				return false;
		} else if (!dateOfExpiry.equals(other.dateOfExpiry))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isExpired == null) {
			if (other.isExpired != null)
				return false;
		} else if (!isExpired.equals(other.isExpired))
			return false;
		if (isReplace == null) {
			if (other.isReplace != null)
				return false;
		} else if (!isReplace.equals(other.isReplace))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
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
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", book=");
		builder.append(book);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", dateOfExpiry=");
		builder.append(dateOfExpiry);
		builder.append(", isExpired=");
		builder.append(isExpired);
		builder.append(", isReplace=");
		builder.append(isReplace);
		builder.append("]");
		return builder.toString();
	}
}
