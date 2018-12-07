package by.etc.samodumkina.dao.impl;

import by.etc.samodumkina.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
	private final static DBAccess instance = new DBAccess();
	
    private final static String URL = "jdbc:mysql://localhost:3306/web_sxema";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "sAmodumk1na";
    
    private DBAccess() {}
    
    public static DBAccess getInstance() {
    	return instance;
    }

    public Connection getConnection() throws SQLException, DAOException{
        Connection connection = null;
        
        try {
			Class.forName("org.gjt.mm.mysql.Driver");
			//Driver driver = new FabricMySQLDriver();
	       // DriverManager.registerDriver(driver);
	        
	        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			throw new DAOException("cannot get driver", e);
		}

        return connection;
    }
}
