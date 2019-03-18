
package final_ims_project.ViewsAndControllers;

import final_ims_project.AlertMsg;
import final_ims_project.Models.Part;
import final_ims_project.Models.Product;
import static final_ims_project.ViewsAndControllers.FXMLMainViewController.partsInventory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Add Product Controller class
 * @author Ty
 */
public class FXMLAddProductViewController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML private TextField idTextfield;
    @FXML private TextField nameTextfield;
    @FXML private TextField qtyTextfield;
    @FXML private TextField priceTextfield;
    @FXML private TextField maxTextfield;
    @FXML private TextField minTextfield;
    @FXML private TextField estProdCost;
    
    @FXML private Button clearProductPartButton;
    @FXML private TextField searchProductTextfield;

    @FXML private TableView<Part> productListedTableView;
        @FXML private TableColumn<Part,String> productIDColumn;
        @FXML private TableColumn<Part,String> productNameColumn;
        @FXML private TableColumn<Part,String> productQtyColumn;
        @FXML private TableColumn<Part,String> productPriceColumn;
        
    @FXML private TableView<Part> productAddedPartTableView;
        @FXML private TableColumn<Part,String> productIDColumnAdded;
        @FXML private TableColumn<Part,String> productNameColumnAdded;
        @FXML private TableColumn<Part,String> productQtyColumnAdded;
        @FXML private TableColumn<Part,String> productPriceColumnAdded;

    
    @FXML private Button saveProductButton;
    @FXML private Button addPartToProductButton;
    @FXML private Button cancelProductButton;
    @FXML private Button deleteProductButton;

    @FXML public FXMLMainViewController mainView; 

    private ObservableList<Part> partsListed = partsInventory.getParts();
    private ObservableList<Part> partsAdded = FXCollections.observableArrayList();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialze patrs table views
        configureListedPartsTable();
        configureAddedPartsTable(); 
       
        estProdCost.setText("$0.00");
    }    
    
     /**
     * @info   Add Product Form - configure table view
     * @other  Configuration method - has event listener for table filter
     *         ->> complete
     */
    public void configureListedPartsTable(){ 
        //Setup column in table
        productIDColumn.setCellValueFactory( new PropertyValueFactory<>("partID"));
        productNameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        productQtyColumn.setCellValueFactory(  new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Set multi-select
        productListedTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //Build fitler list
        FilteredList<Part> partsFilter = new FilteredList<>(partsInventory.getParts(),p -> true);
        
        //listener for text property change
        searchProductTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            partsFilter.setPredicate(part -> {
                
                 // If filter text is empty, display all parts
                 if (newValue == null || newValue.isEmpty()) {
                     return true;
                 }

                 // Compare part name
                 String nameFilter = newValue.toLowerCase();
                 
      
                 //filter for part name
                 if (part.getName().toLowerCase().contains(nameFilter)) {
                        return true; // Filter matches part
                 }else if(Integer.toString(part.getPartID()).indexOf(newValue) != -1){
                    return true;
                 } 
                 
                 return false; // Does not match.
             });
         });
        
        SortedList<Part> sortedData = new SortedList<>(partsFilter);
        
        sortedData.comparatorProperty().bind(productListedTableView.comparatorProperty());
        
        // Set data in table
        productListedTableView.setItems(sortedData);
    }
    
     /**
     * @info   Add Product Form - configure parts table view
     * @other  Configuration method
     *         ->> complete
     */
    public void configureAddedPartsTable(){ 
        //Setup column in table
        productIDColumnAdded.setCellValueFactory(new PropertyValueFactory<>("partID"));
        productNameColumnAdded.setCellValueFactory(new PropertyValueFactory<>("name"));
        productQtyColumnAdded.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumnAdded.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Load dummy data
        productAddedPartTableView.setItems(partsAdded);
    }
   
    /**
     * @info   Add Product Form - Gets the Add Product form values
     * @return ArrayList String of form values
     * @other  Helper method
     *         ->> complete
     */
    public ArrayList<String> getFormValues(){
        
        ArrayList<String> formValues = new ArrayList<>();
        
        String productID           = idTextfield.getText();
        String productName         = nameTextfield.getText();
        String productQty          = qtyTextfield.getText();
        String productPrice        = priceTextfield.getText();
        String productMax          = maxTextfield.getText();
        String productMin          = minTextfield.getText();

        
        formValues.add(productID);         //0  
        formValues.add(productName);       //1
        formValues.add(productQty);        //2 
        formValues.add(productPrice);      //3 
        formValues.add(productMax);        //4 
        formValues.add(productMin);        //5
   
        return formValues;
    }
    
    /**
     * @info    Add Product form - create and save new product
     * @param   ArrayList String of form values
     * @return  boolean if prod was created
     *          ->> complete
     */
    public boolean saveNewProduct(ArrayList<String> formValues){
    
        boolean wasCreated = false;    
        try{
            //prod id
            if(formValues.get(0).trim().isEmpty()){
                AlertMsg.errorProduct(0);
                return false;
            }
            //prod name
            if(formValues.get(1).trim().isEmpty()){
                AlertMsg.errorProduct(1);
                return false;
            }
            //prod qty
            if(formValues.get(2).trim().isEmpty()){
                AlertMsg.errorProduct(2);
                return false;
            }
            // prod proce
            if(formValues.get(3).trim().isEmpty()){
                AlertMsg.errorProduct(3);
                return false;
            }
            //prod max
            if(formValues.get(4).trim().isEmpty()){
                AlertMsg.errorProduct(4);
                return false;
            }
            //prod min
            if(formValues.get(5).trim().isEmpty()){
                AlertMsg.errorProduct(5);
                return false;
            }
            
            //Check prod min and max
            int productMax = Integer.parseInt(formValues.get(4));
            int productMin = Integer.parseInt(formValues.get(5));
            if(productMin > productMax || productMax < productMin 
                    || productMin < 0 || productMax < 0){
                AlertMsg.errorProduct(6);
                return false;
            }
            
            //Check prod qty
            int productQty = Integer.parseInt(formValues.get(2));
            if(productQty < productMin || productQty > productMax 
                    || productQty < 0){
                AlertMsg.errorProduct(7);
                return false;
            }
            
            //Check prod price
            double productPrice = Double.parseDouble(formValues.get(3));
            if(productPrice < 0.00){
                AlertMsg.errorProduct(8);
                return false;
            }
            
            //Check number of prod parts
            if(partsAdded.size() < 1){
                AlertMsg.errorProduct(9);
                return false;
            }
            
            int productID = Integer.parseInt(formValues.get(0));
            String productName = formValues.get(1);
            
            //Create Product 
            try{
               
                Product newProd = new Product(partsAdded, productID, productName, 
                        productPrice, productQty, productMin, productMax);
                
//                System.out.println(partsAdded);
                
                //add to main products list
                mainView.productsInventory.addProduct(newProd);
       
                //refresh the list
                mainView.refreshList();
                
                wasCreated = true;
                
            }catch(Exception e){
                wasCreated = false;
                AlertMsg.errorProduct(10);
            }
        }catch(Exception e){
            return false;
        }finally{
            return wasCreated;
        }   
    }
    
    /**
     * @info   Add Product form - reset form values to default
     * @other  Helper method
     *         ->> complete
     */
    public void resetToDefault(){
        idTextfield.setText("");
        nameTextfield.setText("");
        qtyTextfield.setText("");
        priceTextfield.setText("");
        maxTextfield.setText("");
        minTextfield.setText("");
       
    }
    
    /**
     * @info    Add Product form - Calculate/Update estimated product cost 
     * @other   Helper method
     *          ->> complete
     */
    public void updateProdCost(){
        
        //Calculate cuml part cost
        estProdCost.setText("");
        if(partsAdded.size() > 0){

            double prodCost = 0.00;
            for(Part part : partsAdded){
                prodCost += part.getPrice();
            }
            estProdCost.setText(String.format("$%.2f", prodCost));
        }else{
            estProdCost.setText("$0.00");
        }
    }
    
    /**
     * @info    Search Product button clicked
     * @param   ActionEvent event
     *          ->> complete
     */
    @FXML public void clearProductSearchButton(ActionEvent event){
        searchProductTextfield.clear();
    }
    
    /**
     * @info    Add Part - save button event
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void saveProductButton(ActionEvent event) throws IOException{
        //Get Form Values
        ArrayList formValues = getFormValues();
        
        //Create new Part
        try{
            boolean wasAdded = saveNewProduct(formValues);
            
            if(wasAdded){
                //Close add part view
                Stage addProductStage = (Stage)saveProductButton.getScene().getWindow();
                addProductStage.close();
            }
        }catch(Exception e){
            AlertMsg.generalAlert("Error: Product was not created");
           
        }
    }
    
    
     /**
     * @info    Add Product form - Add part to product
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void addPartToProductButton(ActionEvent event)throws IOException {
     
        boolean isConfirmed = AlertMsg.addConfirm("part to this product?");
        
        if(isConfirmed){
            ObservableList<Part> moveParts = productListedTableView
                    .getSelectionModel()
                    .getSelectedItems();

            if(moveParts.size()>0){
                for(Part parts : moveParts ){
                    partsAdded.add(parts);
                }

                for(Part parts : moveParts){
                    partsListed.remove(parts);
                }

                productAddedPartTableView.refresh();

                updateProdCost();
            }
        } 
    }
    
    /**
     * @info    Add Product form - Delete associated part form product
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void deleteProductPartButton(ActionEvent event)throws IOException {
       
        boolean isConfirmed = AlertMsg.deleteConfirm("part from this product");
        
        if(isConfirmed){
            ObservableList<Part> moveParts = productAddedPartTableView
                        .getSelectionModel()
                        .getSelectedItems();

            if(moveParts.size()>0){

                for(Part parts : moveParts ){
                    partsListed.add(parts);
                }

                for(Part parts : moveParts){
                    partsAdded.remove(parts);
                }

                productAddedPartTableView.refresh();

                updateProdCost();

            }else{
                AlertMsg.partNotSelected();
            }
        }
    }
    
    /**
     * @info    Add Product form- Cancel button event
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void cancelProductButton(ActionEvent event)throws IOException {
        
        boolean isConfirmed = AlertMsg.cancelConfirm();
        
        if(isConfirmed){
            Stage addProductStage = (Stage)cancelProductButton.getScene().getWindow();
            addProductStage.close();
        }
    }
}
