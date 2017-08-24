package dao;

import models.Backpack;
import models.Bikepack;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/24/17.
 */
public class Sql2oBikepackDao implements BackPackDao {

    private final Sql2o sql2o;

    public Sql2oBikepackDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Backpack backpack) {
        String sql = "INSERT INTO backpacks (brand, model, description, waterResistance, durability, productId, price, type) VALUES (:brand, :model, :description, :waterResistance, :durability, :productId, :price, :type)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(backpack)
                    .executeUpdate()
                    .getKey();
            backpack.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM backpacks WHERE type = 'bikepack'")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Bikepack.class);
        }
    }

    @Override
    public Bikepack findById(int id ) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM backpacks WHERE id = :id  AND type = 'bikepack'")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Bikepack.class);
        }
    }

    @Override
    public void update(Backpack backpack, String newBrand, String newModel, String newDescription, int newWaterResistance, int newDurability, int newProductId, double newPrice){
        String sql = "UPDATE backpacks SET (brand, model, description, waterResistance, durability, productId, price) = (:brand, :model, :description, :waterResistance, :durability, :productId, :price) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("brand", newBrand)
                    .addParameter("model", newModel)
                    .addParameter("description", newDescription)
                    .addParameter("waterResistance", newWaterResistance)
                    .addParameter("durability", newDurability)
                    .addParameter("productId", newProductId)
                    .addParameter("price", newPrice)
                    .addParameter("id", backpack.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from backpacks WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
