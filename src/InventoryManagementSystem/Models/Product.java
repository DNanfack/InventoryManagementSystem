package InventoryManagementSystem.Models;

import javafx.collections.ObservableList;

public class Product {
    
    //The parts that are associated with this product
    private ObservableList<Part> associatedParts;
    //The product's id, min, and max value.
    private int productId, inv, min, max;
    //The product's name
    private String name;
    //The product's price
    private double price;
    
    /**
     * @param id
     * @param name
     * @param min
     * @param max
     * @param inv
     * @param price
     * @param parts 
     * Usage: Creates a product.
     */
    public Product (int id, String name, int min, int max, int inv, double price, ObservableList<Part> parts){
        this.productId = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.inv = inv;
        this.price = price;
        this.associatedParts = parts;
    }
    
    /**
     * @return name
     * Usage: Returns the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return price
     * Usage: Returns the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * @return inv
     * Usage: Returns the inv
     */
    public int getInv() {
        return inv;
    }
    
    /**
     * @return min
     * Usage: Returns the min
     */
    public int getMin() {
        return min;
    }
    
    /**
     * @return max
     * Usage: Returns the max
     */
    public int getMax() {
        return max;
    }
    
    /**
     * @param associatedPart 
     * Usage: Adds the given part to the product's parts.
     */
    public void addAssociatedPart(Part associatedPart){
        associatedParts.add(associatedPart);
    }
    
    /**
     * @param associatedPart 
     * Usage: Removes the given part from the product's parts.
     */
    public void removeAssociatedPart(Part associatedPart){
        for(int i = 0; i < associatedParts.size(); i++){
            if(associatedParts.get(i) == associatedPart){
                associatedParts.remove(i);
            }
        }
    }

    /**
     * @return the product id
     * Usage: Returns the product id.
     */
    public int getProductId() {
        return productId;
    }    
    
    /**
     * @return the product's parts
     * Usage: Returns the product's parts
     */
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
}
