package InventoryManagementSystem.Models;

public class InhousePart extends Part{
    
    // The machine id that created the part
    private static int machineId;
    
    /**
     * @param partId
     * @param name
     * @param price
     * @param inv
     * @param min
     * @param max
     * @param machineId 
     * Usage: Creates an InHouse part.
     */
    public InhousePart(int partId, String name, double price, int inv, int min, int max, int machineId){
        this.setPartId(partId);
        this.setName(name);
        this.setPrice(price);
        this.setInv(inv);
        this.setMin(min);
        this.setMax(max);
        this.setMachineId(machineId);
    }
    
    /**
     * @param machineId 
     * Usage: Sets the machine id.
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    
    /**
     * Usage: Gets the machine id.
     */
    public int getMachineId(){
        return machineId;
    }
}
