package dao;

import models.Backpack;

import java.util.List;

/**
 * Created by Guest on 8/24/17.
 */
public interface BackPackDao {
    //create
    void add (Backpack backpack);

//    //read
    List getAll();
//
//
   Backpack findById(int id);
//
//    //update
    void update(Backpack backpack, String newBrand, String newModel, String newDescription, int newWaterResistance, int newDurability, int newProductId, double newPrice);
//
//    //delete
//    void deleteById(int id);
//

}
