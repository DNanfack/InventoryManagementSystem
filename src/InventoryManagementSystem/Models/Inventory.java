package InventoryManagementSystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    // A list of all the parts.
    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    // A list of all the products.
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    
    /**
     * @param partToBeAdded 
     * Usage: Adds the given part to the parts list.
     */
    public static void addPart(Part partToBeAdded){
        parts.add(partToBeAdded);
    }
   
    /**
     * @param partToBeRemoved 
     * Usage: Deletes the given part from the parts list.
     */
    public static void deletePart(Part partToBeRemoved){
        for(int i = 0; i < parts.size(); i++){
            if(parts.get(i).equals(partToBeRemoved)){
                parts.remove(i);
            }
        }
    }
    
    /**
     * @param productToBeAdded 
     * Usage: Adds the given product to the products list.
     */
    public static void addProduct(Product productToBeAdded){
        products.add(productToBeAdded);
    }
    
    /**
     * @param productToBeDeleted 
     * Usage: Removes the given product from the products list.
     */
    public static void removeProduct(Product productToBeDeleted){
        for (int i = 0; i < products.size(); i++){
            if(products.get(i) == productToBeDeleted){
                products.remove(i);
            }
        }
    }
    
    /**
     * @returns the products.
     * Usage: Returns all the products.
     */
    public static ObservableList<Product> getProducts(){
        return products;
    }
    
    /**
     * @returns the parts
     * Usage: Returns all the parts.
     */
    public static ObservableList<Part> getParts(){
        return parts;
    }
}

