package by.etc.samodumkina.bean;

public class Book {
	private int id;
	private String name;
	private String authors;
	private String annotation;
	private String description;
	private int colInLabrary;
	
	public Book() {}

	public Book(int id, String name, String authors, String annotation, String description) {
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.annotation = annotation;
		this.description = description;
	}

	public Book(int id, String name, String authors, String annotation, String description, int colInLabrary) {
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.annotation = annotation;
		this.description = description;
		this.colInLabrary = colInLabrary;
	}
	
	public Book (String name, String authors, String annotation, String description, int colInLabrary) {
		this.name = name;
		this.authors = authors;
		this.annotation = annotation;
		this.description = description;
		this.colInLabrary = colInLabrary;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getColInLabrary() {
		return colInLabrary;
	}

	public void setColInLabrary(int colInLabrary) {
		this.colInLabrary = colInLabrary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotation == null) ? 0 : annotation.hashCode());
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + colInLabrary;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Book other = (Book) obj;
		if (annotation == null) {
			if (other.annotation != null)
				return false;
		} else if (!annotation.equals(other.annotation))
			return false;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (colInLabrary != other.colInLabrary)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", authors=");
		builder.append(authors);
		builder.append(", annotation=");
		builder.append(annotation);
		builder.append(", description=");
		builder.append(description);
		builder.append(", colInLabrary=");
		builder.append(colInLabrary);
		builder.append("]");
		return builder.toString();
	}

}
