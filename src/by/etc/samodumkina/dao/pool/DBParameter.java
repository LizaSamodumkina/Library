package by.etc.samodumkina.dao.pool;

public enum DBParameter {
	DB_DRIVER("db.driver"),
	DB_URL("db.url"),
	DB_USER("db.user"),
	DB_PASSWORD("db.password"),
	DB_POOLSIZE("db.poolsize");
	
	private String value;
	
	private DBParameter(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
