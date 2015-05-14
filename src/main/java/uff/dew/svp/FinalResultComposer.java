package uff.dew.svp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import uff.dew.svp.db.Database;
import uff.dew.svp.db.DatabaseException;
import uff.dew.svp.db.DatabaseFactory;
import uff.dew.svp.fragmentacaoVirtualSimples.Query;
import uff.dew.svp.fragmentacaoVirtualSimples.SubQuery;
import uff.dew.svp.strategy.CompositionStrategy;
import uff.dew.svp.strategy.ConcatenationStrategy;
import uff.dew.svp.strategy.TempCollectionStrategy;

public class FinalResultComposer {

    private OutputStream output;
    private Database database;
    
    private CompositionStrategy compositionStrategy;
    
    private boolean forceTempCollectionMode = false;
    
    public FinalResultComposer(OutputStream output) {
        this.output = output;
    }
    
    /**
     * Sets the local database information where the subquery will be executed
     * 
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param databaseName
     * @param type
     * @throws DatabaseException
     */
    public void setDatabaseInfo(String hostname, int port, String username, String password,
            String databaseName, String type) throws DatabaseException {
        database = DatabaseFactory.getDatabase(hostname,port,username,
                password,databaseName,type);
//        Catalog.get().setDatabaseObject(DatabaseFactory.getSingletonDatabaseObject());
    }
    
    public void setForceTempCollectionExecutionMode(boolean flag) {
        this.forceTempCollectionMode = flag;
    }
    
    /**
     * Set execution context
     * 
     * @param context
     */
    public void setExecutionContext(ExecutionContext context) {
        Query q = context.getQueryObj();
        SubQuery sbq = context.getSubQueryObj();
        String orderby = q.getOrderBy();
        Hashtable<String,String> aggrFunctions = q.getAggregateFunctions();
        if (forceTempCollectionMode) {
            compositionStrategy = new TempCollectionStrategy(database,q,sbq,output);
        }
        else if (orderby != null && !orderby.equals("")) {
            compositionStrategy = new TempCollectionStrategy(database,q,sbq,output);
        } 
        else if (aggrFunctions != null && aggrFunctions.size() > 0) {
            compositionStrategy = new TempCollectionStrategy(database,q,sbq,output);
        }
        else {
            compositionStrategy = new ConcatenationStrategy(output,sbq);
        }
    }
    
    /**
     * 
     * @param is
     */
    public void loadPartial(InputStream is) throws IOException {
        compositionStrategy.loadPartial(is);
    }
    
    public void combinePartialResults() throws IOException {
        compositionStrategy.combinePartials();
    }
    
    /**
     * used in tests to clean resources
     */
    public void cleanup() {
        compositionStrategy.cleanup();
    }
}
