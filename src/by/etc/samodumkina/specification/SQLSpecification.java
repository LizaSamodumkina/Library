package by.etc.samodumkina.specification;

public abstract class SQLSpecification implements Specification {
	public abstract String toSQLQuery();
	
	@Override
	public String toQuery(){
		return toSQLQuery();
	};
}
