package dao;

import models.Backpack;
import models.Surfpack;
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
public class Sql2oSurfpackDaoTest {

    private Connection conn;
    private Sql2oSurfpackDao surfpackDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        surfpackDao = new Sql2oSurfpackDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addBackPackSetsId() throws Exception {
        Surfpack surfpack = setupSurfpack();
        surfpackDao.add(surfpack);
        int originalBackpackId =  surfpack.getId();
        assertEquals(1, originalBackpackId);
    }
    @Test
    public void addedSurfpacksAreReturnedFromGetAll() throws Exception {
        Surfpack surfpack = setupSurfpack();
        Surfpack surfpack1= setupSurfpack();
        surfpackDao.add(surfpack);
        surfpackDao.add(surfpack1);

        Surfpack[] allSurfpacks = {surfpack, surfpack1};
        assertEquals(Arrays.asList(allSurfpacks), surfpackDao.getAll());
        assertEquals(2, surfpackDao.getAll().size());
    }

    @Test
    public void updateChangesSurfpackContent() throws Exception {
        Surfpack surfpack = setupSurfpack();
        surfpackDao.add(surfpack);
        surfpackDao.update(surfpack,"banjo bros", "pleasureBag", "hard working commute pack", 5, 5, 15234, 99.99);
        Surfpack updatedSurfpack = surfpackDao.findById(surfpack.getId());
        assertNotEquals(surfpack, updatedSurfpack.getModel());
    }

    @Test
    public void deleteByIdDeletesCorrectSurfpack() throws Exception {
        Surfpack testSurfpack = setupSurfpack();
        surfpackDao.add(testSurfpack);
        surfpackDao.deleteById(testSurfpack.getId());
        assertEquals(0, surfpackDao.getAll().size());
    }


    public Surfpack setupSurfpack (){
        return new Surfpack("banjo nros", "commuteaction", "hard working commute pack", 5, 5, 15234, 99.99);
    }

}