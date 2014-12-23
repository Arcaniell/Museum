package config;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.FileInputStream;

public class DBUnitConfig extends DBTestCase{

    protected IDatabaseTester tester;

    public DBUnitConfig(String name) {
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "org.postgresql.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:postgresql://localhost:5432/museum" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "postgres" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "postgres" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
                "public" );
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(
                new FileInputStream("/home/yura/IdeaProjects/Museum/museumApp/service/src/" +
                        "test/resources/com.itaSS.entity/exhibit.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

}
