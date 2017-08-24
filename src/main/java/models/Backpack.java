package models;

/**
 * Created by Guest on 8/24/17.
 */
public class Backpack {
    private int id;
    public String brand;
    public String model;
    public  String description;
    public int waterResistance;
    public int durability;
    public int productId;
    public double price;


    public Backpack( String brand, String model, String description, int waterResistance, int durability, int productId, double price) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.waterResistance = waterResistance;
        this.durability = durability;
        this.productId = productId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWaterResistance() {
        return waterResistance;
    }

    public void setWaterResistance(int waterResistance) {
        this.waterResistance = waterResistance;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
