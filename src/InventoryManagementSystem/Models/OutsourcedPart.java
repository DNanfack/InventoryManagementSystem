package InventoryManagementSystem.Models;

public class OutsourcedPart extends Part{
    
    //the company that created the part.
    private String companyName;
    
    /**
     * @param partId
     * @param name
     * @param price
     * @param inv
     * @param min
     * @param max
     * @param companyName 
     * Usage: Creates an OutsourcedPart
     */
    public OutsourcedPart(int partId, String name, double price, int inv, int min, int max, String companyName){
        this.setPartId(partId);
        this.setName(name);
        this.setPrice(price);
        this.setInv(inv);
        this.setMin(min);
        this.setMax(max);
        this.setCompanyName(companyName);
    }
    
    /**
     * @param companyName 
     * Usage: Sets the company name. 
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    /**
     * @return companyName
     * Usage: Returns the companyName.
     */
    public String getCompanyName(){
        return companyName;
    }
    
}
