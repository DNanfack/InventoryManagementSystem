package InventoryManagementSystem.Models;

public abstract class Part {
    
    // The aprt's name
    private String name;
    //The part's id, inv level, min and max values.
    private int partId, inv, min, max;
    //The part's price.
    private double price;

    /**
     * @param name 
     * Usage: Sets the part's name
     */
    public void setName(String name) {
        this.name = name;
    }
   
    /**
     * @return name
     * Usage: Returns the part's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param price 
     * Usage: Sets the price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return price
     * Usage: Returns the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * @param inv 
     * Usage: Sets the inv
     */
    public void setInv(int inv) {
        this.inv = inv;
    }
    
    /**
     * @return inv
     * Usage: Returns the inv
     */
    public int getInv() {
        return inv;
    }

    /**
     * @param min 
     * Usage: Sets the min
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * @return min
     * Usage: Returns the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param max 
     * Usage: Sets the max
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * @return max
     * Usage: Returns the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param partId 
     * Usage: Sets the partId
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }
    
    /**
     * @return partId
     * Usage: Returns the partId
     */
    public int getPartId() {
        return partId;
    }
}
