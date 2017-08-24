package models;

/**
 * Created by Guest on 8/24/17.
 */
public class Backpack {
    public int id;
    public String brand;
    public String model;
    public  String description;
    public int waterResistance;
    public int durability;
    public int productId;
    public double price;

    public String type;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Backpack backpack = (Backpack) o;

        if (id != backpack.id) return false;
        if (waterResistance != backpack.waterResistance) return false;
        if (durability != backpack.durability) return false;
        if (productId != backpack.productId) return false;
        if (Double.compare(backpack.price, price) != 0) return false;
        if (!brand.equals(backpack.brand)) return false;
        if (!model.equals(backpack.model)) return false;
        if (!description.equals(backpack.description)) return false;
        return type.equals(backpack.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + waterResistance;
        result = 31 * result + durability;
        result = 31 * result + productId;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + type.hashCode();
        return result;
    }
}
