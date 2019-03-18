/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_ims_project.ViewsAndControllers;

import final_ims_project.AlertMsg;
import final_ims_project.Models.InHouse;
import final_ims_project.Models.Inventory;
import final_ims_project.Models.OutSourced;
import final_ims_project.Models.Part;
import final_ims_project.Models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.stage.Modality;

/**
 * FXML Main Controller class
 * @author Ty
 */
public class FXMLMainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Parts View
    @FXML private TextField searchPartsTextfield;
    @FXML private Button    searchPartSystemButton; 
    
    //Parts Table View
    @FXML public TableView<Part> partsTableView;
        @FXML private TableColumn<Part, String> partIDColumn;
        @FXML private TableColumn<Part, String> partNameColumn;
        @FXML private TableColumn<Part, String> partQtyColumn;
        @FXML private TableColumn<Part, String> partPriceColumn;

    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button deletePartButton;
    
    //Products View
    @FXML private TextField searchProductTextfield;
    @FXML private Button    searchProductSystemButton; 
    
    @FXML public TableView<Product> productTableView;
        @FXML private TableColumn<Product, String> productIDColumn;
        @FXML private TableColumn<Product, String> productNameColumn;
        @FXML private TableColumn<Product, String> productQtyColumn;
        @FXML private TableColumn<Product, String> productPriceColumn;
    
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deleteProductButton;

    public static final Inventory partsInventory = new Inventory();
    public static final Inventory productsInventory = new Inventory();

    private static FXMLMainViewController thisController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        // Add parts to the partsInventory
        InHouse smallbolt = new InHouse(1,23, "Small Bolt", 0.55, 45, 10, 500);
        InHouse smallerbolt = new InHouse(2,25, "Smaller Bolt", 0.52, 48, 10, 500);
        InHouse smallwasher = new InHouse(3,27, "Small washer", 0.55, 49, 10, 500);
        InHouse smallerwasher = new InHouse(4,29, "Smaller washer", 0.52, 78, 10, 500);
        
        OutSourced largeBolt = new OutSourced(5,"Ty\'s Tools Shop", "Medium Bolt", 0.65, 46, 10, 500);
        OutSourced largerBolt = new OutSourced(6,"Ty\'s Tools Shop", "Large Bolt", 0.69, 67, 10, 500);
        OutSourced largewasher = new OutSourced(7,"Brad\'s Tools Shop", "Medium washer", 0.65, 30, 10, 500);
        OutSourced largerwasher = new OutSourced(8,"Brad\'s Tools Shop", "Large washer", 0.69, 450, 10, 500);
    

        // Add to partsInventory
        partsInventory.addPart(smallbolt);
        partsInventory.addPart(smallerbolt);
        partsInventory.addPart(smallwasher);
        partsInventory.addPart(smallerwasher);
        
        partsInventory.addPart(largeBolt);
        partsInventory.addPart(largerBolt);
        partsInventory.addPart(largewasher);
        partsInventory.addPart(largerwasher);
        
        //Create list for holding parts for new product
        ObservableList<Part> smallBoltList = FXCollections.observableArrayList();
            smallBoltList.add(smallbolt);
            smallBoltList.add(smallerbolt);
            smallBoltList.add(smallwasher);
            smallBoltList.add(smallerwasher);
        
        ObservableList<Part> largeBoltList = FXCollections.observableArrayList();
            largeBoltList.add(largeBolt);
            largeBoltList.add(largerBolt);
            largeBoltList.add(largewasher);
            largeBoltList.add(largerwasher);
           
        //Create new products
        Product smallBoltProduct = new Product(smallBoltList,1, "Small bolk Kit",
                3.99,60,10,500);
        
        Product largeBoltProduct = new Product(largeBoltList,2, "Large bolk Kit",
                5.99,99,10,1000);

        //Add products to the inventory
        productsInventory.addProduct(smallBoltProduct);
        productsInventory.addProduct(largeBoltProduct);
        
        // Initialize TableView(s)
        configurePartsTable();
        configureProductsTable();
        
        // Set this controller
        thisController = this;
       
    }   
    
     /**
     * @info:    Main View Form - Refresh configure table views
     *           ->>complete
     */
    public static void refreshList(){
        thisController.configurePartsTable();
        thisController.configureProductsTable();
    }
    
    /**
     * @info:    Main View Form - Setup Parts Table View
     * @other:   Configuration Method - has event listener for table parts view
     *           ->>complete
     */
    public void configurePartsTable(){ 
        //Setup column in table
        partIDColumn.setCellValueFactory(   new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        partQtyColumn.setCellValueFactory(  new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Build fitler list
        FilteredList<Part> partsFilter = new FilteredList<>(partsInventory.getParts(),p -> true);
        
        //listener for text property change
        searchPartsTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
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
        
        sortedData.comparatorProperty().bind(partsTableView.comparatorProperty());
        
        // Set data in table
        partsTableView.setItems(sortedData);
    }
    
     /**
     * @info:    Main View Form - Setup Products Table View
     * @other:   Configuration Method - has event listener for table products view
     *           ->>complete
     */
    public void configureProductsTable(){ 
        //Setup column in table
        productIDColumn.setCellValueFactory( new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));

        productQtyColumn.setCellValueFactory(  new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Build fitler list
        FilteredList<Product> productFilter = new FilteredList<>(productsInventory.getProducts(),p -> true);
        
        //listener for text property change
        searchProductTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            productFilter.setPredicate(product -> {
                
                 // If filter text is empty, display all products
                 if (newValue == null || newValue.isEmpty()) {
                     return true;
                 }

                 // Compare product name
                 String nameFilter = newValue.toLowerCase();
                 
                 //filter for product name
                 if (product.getName().toLowerCase().contains(nameFilter)) {
                        return true; // Filter matches part
                 }else if(Integer.toString(product.getProductID()).indexOf(newValue) != -1){
                    return true;
                 } 
                 
                 return false; // Does not match.
             });
         });
        
        SortedList<Product> sortedData = new SortedList<>(productFilter);
        
        sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
        
        productTableView.setItems(sortedData);
    }
     
    /**
     * @info:   Main View Form - Search Part button clicked
     *          ->> complete
     */
    @FXML public void searchPartButtonClicked(ActionEvent event){
        searchPartsTextfield.clear();
    }
    
    /**
     * @info:   Main View Form - Search Product button clicked
     * @param:  ActionEvent event
     *          ->> complete
     */
    @FXML public void searchProductButtonClicked(ActionEvent event){
        searchProductTextfield.clear();
    }
    
    /**
     * @info:   Main View Form - Switches the scene to the Add Part view
     * @param:  ActionEvent event
     * @throws: IOException
     *          ->>complete
     */
    @FXML public void addPartButton(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddPartView.fxml"));
            Parent root = (Parent)loader.load();

            //Get add part view
            FXMLAddPartViewController addPartView = loader.getController();
            Stage addPartStage = new Stage();
            addPartStage.setTitle("Add Part Menu");
            addPartStage.setResizable(false);
            addPartStage.setScene(new Scene(root));
            
            addPartStage.initModality(Modality.APPLICATION_MODAL);
            addPartStage.showAndWait();
            
        }catch(IOException e){
            AlertMsg.generalAlert("Unknown Error. Please try again!\n"+ e.getLocalizedMessage());
        }
    }
    
     /**
     * @info:   Main View Form -Switches the scene to the Add Part view
     * @param:  ActionEvent event
     * @throws: IOException
     *          ->>complete
     */
    @FXML public void modifyPartButton(ActionEvent event) throws IOException{

        try{
            final int selectedIndex = partsTableView.getSelectionModel().getSelectedIndex();
            
            if(selectedIndex!=-1){
               
                Part thePart = (Part)partsTableView.getSelectionModel().getSelectedItem();
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLModifyPartView.fxml"));
                Parent root = (Parent)loader.load();
                
                //Set scene and stage
                Stage modifyPartStage = new Stage();
                modifyPartStage.setTitle("Modify Part Menu");
                modifyPartStage.setResizable(false);
                modifyPartStage.setScene(new Scene(root));

                //Send part to controller
                FXMLModifyPartViewController modifyPart = loader.getController();
                modifyPart.initData(thePart);

                modifyPartStage.initModality(Modality.APPLICATION_MODAL);
                modifyPartStage.showAndWait();
     
            }else{
                AlertMsg.partNotSelected();
            }
        }catch(Exception e){
            AlertMsg.generalAlert("Unknown Error. Please try again!\n"+ e.getLocalizedMessage());
        }finally{
            partsTableView.refresh();
        }
    }
    
    /**
     * @info:   Main View Form - Delete Part from Table View
     * @param:  ActionEvent event
     * @throws: IOException
     *          ->>complete
     */
    @FXML public void deletePartButton(ActionEvent event) throws IOException{
        try{
            final int selectedIndex = partsTableView.getSelectionModel().getSelectedIndex();
            
            if(selectedIndex!=-1){
                
                Part part = (Part)partsTableView.getSelectionModel().getSelectedItem();
                
                boolean isConfirmed = AlertMsg.deleteConfirm("part");
                if(isConfirmed){
                    this.partsInventory.deletePart(part);
                    refreshList();
                }
            }else{
               AlertMsg.partNotSelected();
            }
        }catch(Exception e){
            AlertMsg.generalAlert("Unknown Issue. Please try again!\n"+e.getLocalizedMessage());
        }
    }
    
    /**
     * @info:   Main View Form - Switches the scene to the Add Product view
     * @param:  ActionEvent event
     * @throws: IOException
     *          ->>complete
     */
    @FXML public void addProductButton(ActionEvent event) throws IOException{ 
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddProductView.fxml"));
            Parent root = (Parent)loader.load();

            //Get add part view
            FXMLAddProductViewController addProdView = loader.getController();
            Stage addProdStage = new Stage();
            addProdStage.setTitle("Add Product Menu");
            addProdStage.setResizable(false);
            addProdStage.setScene(new Scene(root));
            
            addProdStage.initModality(Modality.APPLICATION_MODAL);
            addProdStage.showAndWait();
        }catch(IOException e){
           AlertMsg.generalAlert("Unknown Error. Please try again!\n"+ e.getLocalizedMessage());
        }
        
    }
    
     /**
     * @info:   Main View Form - Switches the scene to the Modify Product view
     * @param:  ActionEvent event
     * @throws: IOException
     *          ->> complete
     */
    @FXML public void modifyProductButton(ActionEvent event) throws IOException{
        try{
            
            final int selectedIndex = productTableView.getSelectionModel().getSelectedIndex();
         
            if(selectedIndex!=-1){
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLModifyProductView.fxml"));
                Parent root = (Parent)loader.load();
                
                //Get part if selected
                Product theProduct = (Product)productTableView.getSelectionModel().getSelectedItem();

                //Get add part view
                FXMLModifyProductViewController addProdView = loader.getController();
                Stage addProdStage = new Stage();
                addProdStage.setTitle("Modify Product Menu");
                addProdStage.setResizable(false);
                addProdStage.setScene(new Scene(root));
                
                //Send product to mod controller
                FXMLModifyProductViewController modifyPart = loader.getController();
                modifyPart.initData(theProduct);

                addProdStage.initModality(Modality.APPLICATION_MODAL);
                addProdStage.showAndWait();
            }else{
               AlertMsg.productNotSelected();
            }
        }catch(IOException e){
             AlertMsg.generalAlert("Unknown Error. Please try again!\n"+ e.getLocalizedMessage());
        }
    }
   
    /**
     * @info:   Main View Form - Delete Product button clicked
     *          ->> complete
     */
    @FXML public void deleteProductButton(ActionEvent event){
      
        try{
            final int selectedIndex = productTableView.getSelectionModel().getSelectedIndex();
            
            if(selectedIndex!=-1){
            
                Product prod = (Product)productTableView.getSelectionModel().getSelectedItem();
                boolean isConfirmed = AlertMsg.deleteConfirm("product");
                
                if(isConfirmed){
                    productsInventory.removeProduct(prod.getProductID());    
                    refreshList();
                }
            }else{
                AlertMsg.productNotSelected();
            }
        }catch(Exception e){
              AlertMsg.generalAlert("Unknown Issue. Please try again!\n" + e.getLocalizedMessage());
        }
    }
    
    /**
     * @info:   Main View Form - Menu Bar - Exit Application
     *          ->> complete
     */
    @FXML public void close_main_window(){
        Platform.exit();
        System.exit(0);
    }
}
