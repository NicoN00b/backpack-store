package dao;

import models.Backpack;
import models.Bikepack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

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

    @Test
    public void addedBikePacksAreReturnedFromGetAll() throws Exception {
        Bikepack bikepack = setupBikepack();
        Bikepack bikepack1= setupBikepack();
        backPackDao.add(bikepack);
        backPackDao.add(bikepack1);

        Bikepack[] allBikePacks = {bikepack, bikepack1};

        assertEquals(Arrays.asList(allBikePacks), backPackDao.getAll());
        assertEquals(2, backPackDao.getAll().size());
    }
     @Test
     public void BikePackisfoundById() throws Exception {
        Bikepack bikepack = setupBikepack();
        Bikepack bikepack1 =setupBikepack();
        backPackDao.add(bikepack);
        backPackDao.add(bikepack1);

        assertEquals(backPackDao.findById(2), bikepack1);
     }

    @Test
    public void updateChangesBikepackContent() throws Exception {
        Bikepack bikepack = setupBikepack();
       backPackDao.add(bikepack);
       backPackDao.update(bikepack,"banjo bros", "pleasureBag", "hard working commute pack", 5, 5, 15234, 99.99);
        Bikepack updatedBikepack = backPackDao.findById(bikepack.getId());
        assertNotEquals(bikepack, updatedBikepack.getModel());
    }




    public Bikepack setupBikepack (){
        return new Bikepack("banjo bros", "commuteaction", "hard working commute pack", 5, 5, 15234, 99.99);
    }

}