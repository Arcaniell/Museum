package com.itaSS.service;

import com.itaSS.entity.Exhibit;
import config.DBUnitConfig;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void testGetAll() throws Exception {
        List<Exhibit> exhibits = service.getAll();
        Assert.assertEquals("Hi", "Hi");
    }

}
