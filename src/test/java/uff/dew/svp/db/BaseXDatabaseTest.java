package uff.dew.svp.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.xquery.XQException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uff.dew.svp.SvpTestQueries;

public class BaseXDatabaseTest {
    
    private static final String TEMP_COLLECTION = "temp";
    private static final String XML_FILES_MD_DIR = System.getProperty("user.dir") + "/test/xmark/md";
    private static final String XML_SINGLE_FILE = System.getProperty("user.dir") + "/test/xmark/sd/auction.xml";
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DatabaseFactory.produceSingletonDatabaseObject("localhost", 1984, "admin", "admin", "testsvp", "BASEX");
        // create test database
        // in basex, the collections are created as databases
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        database.deleteCollection("testsvp");
        database.createCollection("testsvp");
        database.loadFileInCollection("testsvp", XML_SINGLE_FILE);
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        database.deleteCollection(TEMP_COLLECTION);
        database.deleteCollection("testsvp");
    }

    @Test
    public void testExecuteQuery() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        
        try {
            // TODO implement a way to check this
            database.executeQuery(SvpTestQueries.query_sd_regular);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong!");
        }
    }

    @Test
    public void testExecuteQueryAsString() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();

        try {
            // TODO implement a way to check this
            database.executeQueryAsString(SvpTestQueries.query_sd_regular);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong!");
        }
    }
        
    @Test
    public void testCreateCollection() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        try {
            database.createCollection(TEMP_COLLECTION);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong");
        }
    }
    
    @Test
    public void testDeleteCollection() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        try {
            database.deleteCollection(TEMP_COLLECTION);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong");
        }
    }

    @Test
    public void testCreateCollectionWithContent() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        try {
            database.createCollectionWithContent(TEMP_COLLECTION,XML_FILES_MD_DIR);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong");
        }
    }

    @Test
    public void testGetCardinality() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        int cardinality = database.getCardinality("/site/people/person", "auction.xml", null);
        assertEquals(255, cardinality);
    }

    @Test
    public void testLoadFileInCollection() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        try {
            database.createCollection(TEMP_COLLECTION);
            database.loadFileInCollection(TEMP_COLLECTION,XML_SINGLE_FILE);
        } catch (XQException e) {
            e.printStackTrace();
            fail("wrong");
        }
    }

    @Test
    public void testGetParentElement() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        String[] parent = database.getParentElement("person", null, "auction.xml");
        assertEquals("people", parent[0]);
        parent = database.getParentElement("site", null, "auction.xml");
        assertEquals("",parent[0]);
    }

    @Test
    public void testGetDocumentsNamesForCollection() {
        Database database = DatabaseFactory.getSingletonDatabaseObject();
        String[] documents = database.getDocumentsNamesForCollection("testsvp");
        assertEquals("auction.xml",documents[0]);
    }
}

