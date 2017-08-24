package dao;

import models.Backpack;
import models.Bikepack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/24/17.
 */
public class Sql2oBikepackDaoTest {

    private Connection conn;
    private Sql2oBikepackDao backPackDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        backPackDao = new Sql2oBikepackDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addBackPackSetsId() throws Exception {
        Bikepack bikepack = setupBikepack();
        int originalBackpackId =  bikepack.getId();
        backPackDao.add(bikepack);
        assertNotEquals(originalBackpackId, bikepack.getId());
    }


    public Bikepack setupBikepack (){
        return new Bikepack("banjo nros", "commuteaction", "hard working commute pack", 5, 5, 15234, 99.99, true);
    }

}