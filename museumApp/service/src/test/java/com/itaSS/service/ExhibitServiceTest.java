package com.itaSS.service;

import config.DBUnitConfig;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;

import org.junit.Before;
import org.junit.Test;

public class ExhibitServiceTest extends DBUnitConfig{

    private ExhibitService service = new ExhibitService();

    public ExhibitServiceTest(String name) {
        super(name);
    }

    @Before
    protected void setUp() throws Exception{
        tester = new JdbcDatabaseTester("org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/museum", "postgres", "postgres", "public");
        IDataSet dataSet = getDataSet();
        tester.setDataSet(dataSet);
        tester.onSetup();
    }

    protected void tearDown() throws Exception{
        tester.onTearDown();
    }

    @Test
    public void createTest() throws Exception {

    }

}
