package uff.dew.svp.db;


public class DatabaseFactory {
    
    private static Database databaseInstance;
    
    private DatabaseFactory() {
    }

    public static void produceSingletonDatabaseObject(String hostname, int port, 
            String username, String password, String databaseName, String type) throws DatabaseException {
        databaseInstance = getDatabase(hostname, port, username, password, databaseName, type);
    }
    
    public static Database getDatabase(String hostname, int port, 
            String username, String password, String databaseName, String type) throws DatabaseException {
        Database database = null;
        if (type.equals(Constants.DB_TYPE_SEDNA)) {
            database = new SednaDatabase(hostname, port, username, password, databaseName);            
        }
        else if (type.equals(Constants.DB_TYPE_BASEX)) {
            database = new BaseXDatabase(hostname, port, username, password, databaseName);
        } else {
            throw new DatabaseException("Database type not recognized!");
        }
        return database;
    }
    
    public static Database getSingletonDatabaseObject() {
    	return databaseInstance;
    }
}
