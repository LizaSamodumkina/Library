package by.etc.samodumkina.bean;

public class PreOrder {
	
	private User user;
	private String likedBookId;
	private boolean isOrderToHome;

	public PreOrder() {}

	public PreOrder(User user, String likedBookId, boolean isOrderToHome) {
		this.user = user;
		this.likedBookId = likedBookId;
		this.isOrderToHome = isOrderToHome;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLikedBookId() {
		return likedBookId;
	}

	public void setLikedBookId(String likedBookId) {
		this.likedBookId = likedBookId;
	}

	public boolean isOrderToHome() {
		return isOrderToHome;
	}

	public void setOrderToHome(boolean isOrderToHome) {
		this.isOrderToHome = isOrderToHome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isOrderToHome ? 1231 : 1237);
		result = prime * result + ((likedBookId == null) ? 0 : likedBookId.hashCode());
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
		PreOrder other = (PreOrder) obj;
		if (isOrderToHome != other.isOrderToHome)
			return false;
		if (likedBookId == null) {
			if (other.likedBookId != null)
				return false;
		} else if (!likedBookId.equals(other.likedBookId))
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
		builder.append("PreOrder [user=");
		builder.append(user);
		builder.append(", likedBookId=");
		builder.append(likedBookId);
		builder.append(", isOrderToHome=");
		builder.append(isOrderToHome);
		builder.append("]");
		return builder.toString();
	}
	
	

}
