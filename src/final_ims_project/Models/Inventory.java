package final_ims_project.Models;

import final_ims_project.AlertMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory{
    
    // Observable lists
    ObservableList<Part> parts = FXCollections.observableArrayList();
    ObservableList<Product> products = FXCollections.observableArrayList();
    
    // Product methods----------------------------------------------------------
    public void addProduct(Product product){
        if(product != null){
            this.products.add(product);
        }
        
    }
    
    public boolean removeProduct(int productID)throws Exception {
        
        boolean isDeleted = false;
        
        if(!products.isEmpty()){ //check if prod list empty
            
            for(Product prod : products){
                
                if(productID == prod.getProductID()){
                    
                    if(prod.associatedParts.size() > 1){ //check if has assoc parts
                        AlertMsg.generalAlert("Product has more than one associated part that "
                                + "must\n be removed prior to deleting this "
                                + "Product");
                        return false;
                    }else{
                        isDeleted = products.remove(prod);
                        return true;
                    }
                }
            }
        }
  
        return isDeleted;
    }
    
    public Product lookupProduct(int productID) {
        
        if(!products.isEmpty()){
            
            for( Product prod : products){
                 if(productID == prod.getProductID()){
                     return prod;
                 }
            }
       }
       return null;
    }

    public void updateProduct(int productID){

        try{
           for(Product prod : products){
                if(productID == prod.getProductID()){
                   prod.setProductID(productID);
                }
           }
        }catch(Exception e){
           
        }
    }
    
    public void updateProduct(Product oldProd, Product modProd){

        try{
            for(Product prod : products){
                if(oldProd.getProductID() == modProd.getProductID()){
                    products.add(modProd);
                    products.remove(oldProd);
                    break;
                }
            }
        }catch(Exception e){
           
        }
    }
    
    public ObservableList<Product> getProducts(){
        return this.products;
    }
    
    public String productsToString(){
        StringBuilder sb = new StringBuilder("Parts Inventory information: \n");
        if(!products.isEmpty()){
            for(Product prod : products ){
                sb.append(prod.getName());
                sb.append("\n");
            }
            return sb.toString();
        }else{
            sb.append("Products list null");
            return sb.toString();
        }
    }

    // Part methods-------------------------------------------------------------
    public void addPart(Part part){
        if(part != null){
            this.parts.add(part);
        }
    }

    public boolean deletePart(Part part){
        boolean isDeleted = false;
        
        if(part != null){
            isDeleted = parts.remove(part);
        }

        return isDeleted;
    }
   
    public Part lookUpParts(int partID){

        if(!parts.isEmpty()){
            for( Part part : parts){
                    if(partID == part.getPartID()){
                        return part;
                    }
           }
        }
        return null;
    }
    
    public void updatePart(int partID){

        for( Part part : parts){
             if(partID == part.getPartID()){
                 System.out.println(part.toString());
             }
        }
        
    }
    
    public void updatePart(Part oldPart, Part modifiedPart){
        
        try{
           for(Part part : parts){
               
                if(oldPart.getPartID() == part.getPartID()){
                    
                    if(modifiedPart instanceof InHouse && oldPart instanceof InHouse){
                        InHouse updateInPart = (InHouse)oldPart;
                        InHouse modPart = (InHouse)modifiedPart;
                        
                        updateInPart.setMachineID(modPart.getMachineID());
                        updateInPart.setName(modPart.getName());
                        updateInPart.setPrice(modPart.getPrice());
                        updateInPart.setInStock(modPart.getInStock());
                        updateInPart.setMax(modPart.getMax());
                        updateInPart.setMin(modPart.getMin());
                        
                    }else if(oldPart instanceof InHouse && modifiedPart instanceof OutSourced){
                        OutSourced newPart = new OutSourced();
                        OutSourced modPart = (OutSourced)modifiedPart;
           
                        newPart.setPartID(oldPart.getPartID());
                        newPart.setCompanyName(modPart.getCompanyName());
                        newPart.setName(modPart.getName());
                        newPart.setPrice(modPart.getPrice());
                        newPart.setInStock(modPart.getInStock());
                        newPart.setMax(modPart.getMax());
                        newPart.setMin(modPart.getMin());
                        
                        parts.add(newPart);
                        parts.remove(oldPart);
                    }else if(modifiedPart instanceof OutSourced && oldPart instanceof OutSourced){
                        OutSourced updateOutPart = (OutSourced)oldPart;
                        OutSourced modPart = (OutSourced)modifiedPart;
                        
                        updateOutPart.setCompanyName(modPart.getCompanyName());
                        updateOutPart.setName(modPart.getName());
                        updateOutPart.setPrice(modPart.getPrice());
                        updateOutPart.setInStock(modPart.getInStock());
                        updateOutPart.setMax(modPart.getMax());
                        updateOutPart.setMin(modPart.getMin());
                        
                    }else if(oldPart instanceof OutSourced && modifiedPart instanceof InHouse){
                        
                        InHouse newPart = new InHouse();
                        InHouse modPart = (InHouse)modifiedPart;
  
                        newPart.setPartID(oldPart.getPartID());
                        newPart.setMachineID(modPart.getMachineID());
                        newPart.setName(modPart.getName());
                        newPart.setPrice(modPart.getPrice());
                        newPart.setInStock(modPart.getInStock());
                        newPart.setMax(modPart.getMax());
                        newPart.setMin(modPart.getMin());
                        
                        parts.add(newPart);
                        parts.remove(oldPart);                        
                    }
                }
           }
        }catch(Exception e){
           
        }
    }
    
    public ObservableList<Part> getParts(){
        return parts;
    }
    
    public String partsToString(){
        StringBuilder sb = new StringBuilder("Parts Inventory information: \n");
        
        if(!parts.isEmpty()){
            for(Part part : parts ){
                sb.append(part.getName());
                sb.append("\n");
            }
            return sb.toString();
        }else{
            sb.append("Parts list null");
            return sb.toString();
        }
    }
}
