package final_ims_project.Models;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    
    //Class Variables
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String name; 
    private double price;
    private int inStock;
    private int min;
    private int max;
    private static int productCount = 0;
    private int productPartCount = this.associatedParts.size();
    
    public Product(){}
    public Product(ObservableList<Part> associatedParts, int productID, String name, double price, int inStock, int min, int max) {
        this.associatedParts = associatedParts;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.productCount += 1;
    }
    
    //Getters and Setters     
    public int getProductCount(){return this.productCount;}
    
    public int getProductPartCount(){return this.associatedParts.size();}
    
    public void setName(String name){this.name = name;}
    
    public String getName(){return this.name;}
    
    public void setPrice (double price){this.price = price;}
    
    public double getPrice(){return this.price;}
    
    public void setInStock(int inStock){this.inStock = inStock;}
    
    public int getInStock(){return this.inStock;}
    
    public void setMin(int min){this.min = min;}
    
    public int getMin(){return this.min;}
    
    public void setMax(int max){this.max = max;}
    
    public int getMax(){return this.max;}
    
    public void setProductID(int productID){this.productID = productID;}
    
    public int getProductID(){return this.productID;}

    public void addAssociatedPart(Part part) {
        
        this.associatedParts.add(part);
    }
    
    public boolean removeAssocatedPart(int partID) {        
        try{
            for(Part part : associatedParts){
                if(partID == part.getPartID()){
                    this.associatedParts.remove(partID);
                    break;
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Part lookUpAssociatedPart(int partID) {
        try{
            for(Part part : associatedParts){
                if(partID == part.getPartID()){
                    return part;
                }
            }
        }catch(Exception e){
            return null;
        }
        return null;
    }
    
    public String printPartsInProduct(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Product Parts: ");
        
        if(associatedParts.size()>0){
            for(Part part : associatedParts){
               sb.append(part.getName());
               sb.append(" ");
            }
            return sb.toString();
        }else{
            return "No associated parts";
        }
    }
    
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n Product Id: ");
        sb.append(this.getProductID());
        sb.append("\n Name: ");
        sb.append(this.getName());
        sb.append("\n Price: ");
        sb.append(this.getPrice());
        sb.append("\n In Stock: ");
        sb.append(this.getInStock());
        sb.append("\n Min: ");
        sb.append(this.getMin());
        sb.append("\n Max: ");
        sb.append(this.getMax());
        sb.append("\n Product Count: ");
        sb.append(this.getProductCount());
        sb.append("\n Part Count: ");
        sb.append(this.getProductPartCount());
        return sb.toString();
    }
      
}
