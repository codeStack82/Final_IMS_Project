
package final_ims_project;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Ty
 */
public class AlertMsg {
 
    // Parts
    public static void errorPart(int errCode){
        
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("Error creating new Part");
        alert.setHeaderText("Can not add Part");
        
        switch(errCode){
            case 1:{
                alert.setContentText("Part ID field can not be null\n" + 
                        "Part ID must be of type: int");
                break;
            }
            case 2:{
                alert.setContentText("Part name field can not be null\n"+ 
                        "Part name must be of type: String");
                break;
            }
            case 3:{
                alert.setContentText("Part quantity field can not be null and must not be less than 0\n"+ 
                        "Part quantity must be of type: int");
                break;
            }
            case 4:{
                alert.setContentText("Part price field can not be null and must be greater than 0.00\n"+ 
                        "Part price must be of type: decimal");
                break;
            }
            case 5:{
                alert.setContentText("Part max field can not be null and must be greater than 0\n"+ 
                        "Part max must be of type: int");
                break;
            }
            case 6:{
                alert.setContentText("Part min field can not be null and must be greater than 0\n"
                        +"Part min must be of type: int");
                break;
            }
            case 7:{
                alert.setContentText("Part machine ID field can not be null\n"+ 
                        "Part machine id must be of type: int");
                break;
            }
            case 8:{
                alert.setContentText("Part company name field can not be null\n"+ 
                        "Part company name must be of type: String");
                break;
            }
            case 9:{
                alert.setContentText("Part max qunatity can not be less than "
                        + "the min qunatity and must be greater than 0 \nPart min "
                        + "qunatity can not be more than the max");
                break;
            }
            case 10:{
                alert.setContentText("Part inventory quantity must be between "
                        + "the min and max values and greater than 0");
                break;
            }
            case 11:{
                alert.setContentText("Part price must be greater than $0.00");
                break;
            }
            default: {
                alert.setContentText("Unknown Error");
            }
        
        }
        alert.showAndWait();
    }
    
    public static void partNotSelected(){
        Alert alert = new Alert(AlertType.INFORMATION);
        
        alert.setTitle("Part Selection ");
        alert.setHeaderText("A part must be selected from the table view!");        
        alert.showAndWait();
    }
    
    public static void partSearch(String alertMsg){
    
        Alert alert = new Alert(AlertType.INFORMATION);
        
        alert.setTitle("Part Search");
        alert.setHeaderText("Part Search Results:\n");   
        alert.setContentText(alertMsg);
        
        alert.showAndWait();
    
    }
    
    public static void modifyPartError(){
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("Modify Part");
        alert.setHeaderText("Error: Part not modified!");
        alert.showAndWait();
    }
    
    public static boolean modifyPartConfirm(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Modify Part Confirmation");
        alert.setHeaderText("Are you sure you want to modify this Part?");
    
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
    // Products
    public static void errorProduct(int errCode){
        
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("Error creating new Product");
        alert.setHeaderText("Can not add new Product");
        
        switch(errCode){
            case 0:{
               alert.setContentText("Product ID field can not be null\n" + 
                        "Product ID must be of type: int");
                break;
            }
            case 1:{
                alert.setContentText("Product name field can not be null\n"+ 
                        "Product name must be of type: String");
                break;
            }
            case 2:{
                alert.setContentText("Product quantity field can not be null and not less than 0\n"+ 
                        "Product quantity must be of type: int");
                break;
            }
            case 3:{
                alert.setContentText("Product price field can not be null and must be greater than 0.00\n"+ 
                        "Product price must be of type: decimal");
                break;
            }
            case 4:{
                alert.setContentText("Product max field can not be null and must be greater than 0\n"+ 
                        "Product max must be of type: int");
                break;
            }
            case 5:{
                alert.setContentText("Product min field can not be null and must be greater than 0\n"
                        +"Product min must be of type: int");
                break;
            } case 6:{
                alert.setContentText("Product max qunatity can not be less than "
                        + "the min \nProduct min qunatity can not be more than the max");
                break;
            }
            case 7:{
                alert.setContentText("Product inventory quantity must be between "
                        + "the min and max values and greater than 0");
                break;
            }
            case 8:{
                alert.setContentText("Product price must be greater than $0.00");
                break;
            }case 9:{
                alert.setContentText("Product must have at least 1 associated part."
                        + "\nPlease, add a part to the list!");
                break;
            }
            case 10:{
                alert.setContentText("Product was not created");
                break;
            }
            default: {
                alert.setContentText("Unknown Error: Product was not created!");
            }
        
        }
        alert.showAndWait();
    }
    
    public static void productNotSelected(){
        Alert alert = new Alert(AlertType.INFORMATION);
        
        alert.setTitle("Product Selection ");
        alert.setHeaderText("A Product must be selected from the table view!");        
        alert.showAndWait();
    }
    
    public static void productSearch(String alertMsg){
    
        Alert alert = new Alert(AlertType.INFORMATION);
        
        alert.setTitle("Product Search");
        alert.setHeaderText("Product Search Results:\n");   
        alert.setContentText(alertMsg);
        
        alert.showAndWait();
    }
    
    public static void modifyProductError(){
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("Modify Product");
        alert.setHeaderText("Error: Product not modified!");
        alert.showAndWait();
    }
    
    public static boolean modifyProductConfirm(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Modify Product Confirmation");
        alert.setHeaderText("Are you sure you want to modify this Product?");
    
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
    // General
    public static boolean addConfirm(String alertMsg){
         
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Add Confirmation");
        alert.setHeaderText("Are you sure you want to add this " + alertMsg );
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    
    }
    
    public static boolean cancelConfirm(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure you want to Cancel?");
        // option != null.
      Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
    public static boolean deleteConfirm(String alertMsg){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this "+ alertMsg + "?");
    
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
    public static boolean resetConfirm(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Reset Confirmation");
        alert.setHeaderText("Are you sure you want to reset this form?");
    
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    public static void generalAlert(String alertMsg){
        
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("General Error");
        alert.setHeaderText("Unknown Issue");
        
        alert.setContentText(alertMsg);
        
        alert.showAndWait();
    
    }
    
}
