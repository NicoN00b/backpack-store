package dao;

import models.Backpack;
import models.Hikepack;
import models.Hikepack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/24/17.
 */
public class Sql2oHikepackDaoTest {

    private Connection conn;
    private Sql2oHikepackDao backPackDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        backPackDao = new Sql2oHikepackDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addBackPackSetsId() throws Exception {
        Hikepack hikepack = setupHikepack();
        int originalBackpackId =  hikepack.getId();
        backPackDao.add(hikepack);
        assertNotEquals(originalBackpackId, hikepack.getId());
    }

    @Test
    public void addedHikePacksAreReturnedFromGetAll() throws Exception {
        Hikepack hikepack = setupHikepack();
        Hikepack hikepack1= setupHikepack();
        backPackDao.add(hikepack);
        backPackDao.add(hikepack1);
        assertEquals(2, backPackDao.getAll().size());
    }
    @Test
    public void HikePackisfoundById() throws Exception {
        Hikepack hikepack = setupHikepack();
        backPackDao.add(hikepack);
        int hikepackId = hikepack.getId();
        assertEquals(1, hikepackId);
    }

    @Test
    public void updateChangesHikepackContent() throws Exception {
        Hikepack hikepack = setupHikepack();
        backPackDao.add(hikepack);
        backPackDao.update(hikepack,"banjo bros", "pleasureBag", "hard working commute pack", 5, 5, 15234, 99.99);
        Hikepack updatedHikepack = backPackDao.findById(hikepack.getId());
        assertNotEquals(hikepack, updatedHikepack.getModel());
    }

    @Test
    public void deleteByIdDeletesCorrectHikepack() throws Exception {
        Hikepack testHikepack = setupHikepack();
        backPackDao.add(testHikepack);
        backPackDao.deleteById(testHikepack.getId());
        assertEquals(0, backPackDao.getAll().size());
    }

    public Hikepack setupHikepack (){
        return new Hikepack("banjo nros", "commuteaction", "hard working commute pack", 5, 5, 15234, 99.99);
    }

}