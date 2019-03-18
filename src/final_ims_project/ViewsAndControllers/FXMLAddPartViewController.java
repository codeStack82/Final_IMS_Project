
package final_ims_project.ViewsAndControllers;

import final_ims_project.AlertMsg;
import final_ims_project.Models.InHouse;
import final_ims_project.Models.OutSourced;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Add Part Controller class
 * @author Ty
 */
public class FXMLAddPartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outSourcedRadioButton;  
    @FXML private ToggleGroup partTypeToggleGroup;
    
    
    @FXML private TextField idTextfield;
    @FXML private TextField nameTextfield;
    @FXML private TextField qtyTextfield;
    @FXML private TextField priceTextfield;
    @FXML private TextField maxTextfield;
    @FXML private TextField minTextfield;
    @FXML private TextField companyTextfield;
    @FXML private TextField machineIDTextfield;
    
    @FXML private Button savePartButton;
    @FXML private Button cancelPartButton;

    private String sourceType;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        //Toggle Group for radio buttons
        this.partTypeToggleGroup =  new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partTypeToggleGroup);
        this.outSourcedRadioButton.setToggleGroup(partTypeToggleGroup);
        
        //Default values
        sourceType ="InHouse";
        this.machineIDTextfield.setDisable(false);
        this.companyTextfield.setDisable(true);
        
       // Radio Button Event Listeners
       // OutSourced Radio Button - Lambda Event Listener 
       inHouseRadioButton.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) -> {
           if (isNowSelected) {
               companyTextfield.setDisable(true);
               machineIDTextfield.setDisable(false);
               companyTextfield.setText("");
               sourceType = "InHouse";
           }
        });
       
       //OutSourced Radio Button - Lambda Event Listener 
       outSourcedRadioButton.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) -> {
           if (isNowSelected) {
               companyTextfield.setDisable(false);
               machineIDTextfield.setDisable(true);
               machineIDTextfield.setText("");
               sourceType = "OutSourced";
           }
        });
    }
    
    /**
     * @info:   Add Part Form - Gets the Add Part form values
     * @return: ArrayList String
     *          ->> complete
     */
    public ArrayList<String> getFormValues(){
        
        ArrayList<String> formValues = new ArrayList<>();
        
        String partSourceType   = sourceType;
        String partID           = idTextfield.getText();
        String partName         = nameTextfield.getText();
        String partQty          = qtyTextfield.getText();
        String partPrice        = priceTextfield.getText();
        String partMax          = maxTextfield.getText();
        String partMin          = minTextfield.getText();
        String companyName      = companyTextfield.getText();
        String machineID        = machineIDTextfield.getText(); 
        
        formValues.add(partSourceType);     //0
        formValues.add(partID);             //1
        formValues.add(partName);           //2
        formValues.add(partQty);            //3
        formValues.add(partPrice);          //4
        formValues.add(partMax);            //5
        formValues.add(partMin);            //6
        formValues.add(machineID);          //7
        formValues.add(companyName);        //8
        
        return formValues;
    }
    
    /**
     * @info:   Add Part Form - Gets the Add Part form values
     * @return: boolean isValid
     *          ->> complete
     */
    public boolean saveNewPart(ArrayList<String> formValues){
        
        boolean wasCreated = false;
        boolean isValid = false;
        
        try{
 
            if(formValues.get(1).trim().isEmpty()){
                AlertMsg.errorPart(1);
                return false;
            }
            if(formValues.get(2).trim().isEmpty()){
                AlertMsg.errorPart(2);
                return false;
            }
            if(formValues.get(3).trim().isEmpty()){
                AlertMsg.errorPart(3);
                return false;
            }
            if(formValues.get(4).trim().isEmpty()){
                AlertMsg.errorPart(4);
                return false;
            }
            if(formValues.get(5).trim().isEmpty()){
                AlertMsg.errorPart(5);
                return false;
            }
            if(formValues.get(6).trim().isEmpty()){
                AlertMsg.errorPart(6);
                return false;
            }
            
            if(inHouseRadioButton.isSelected() && formValues.get(7).trim().isEmpty()){
                AlertMsg.errorPart(7);
                return false;
            }
            
            if(outSourcedRadioButton.isSelected() && formValues.get(8).trim().isEmpty()){
                AlertMsg.errorPart(8);
                return false;
            }
            
            //Check min, max, and qty
            int min = Integer.parseInt(formValues.get(6));
            int max = Integer.parseInt(formValues.get(5));
            int qty = Integer.parseInt(formValues.get(3));
            double price = Double.parseDouble(formValues.get(4));
             
            if(min > max || max < min || min < 0 || max < 0){
                AlertMsg.errorPart(9);
                return false;
            }
            if(qty < min || qty > max || qty < 1){
                AlertMsg.errorPart(10);
                return false;
            }
            if(price < 0.00){
                AlertMsg.errorProduct(11);
                return false;
            }
            
            //parse type
            int counter = 1;
            isValid = true;
            
            if(isValid){
                try{
                    //TODO: check for numbers < 0
                    //InHouse
                    if(formValues.get(0).equals("InHouse")){

                        int partID = Integer.parseInt(formValues.get(1));
                        counter +=1;

                        String partName = formValues.get(2);
                        counter +=1;

                        int partQty = Integer.parseInt(formValues.get(3));
                        counter +=1;

                        double partPrice = Double.parseDouble(formValues.get(4));
                        counter +=1;

                        int partMax = Integer.parseInt(formValues.get(5));
                        counter +=1;

                        int partMin = Integer.parseInt(formValues.get(6));
                        counter +=1;

                        //TODO: validate min max qty for part and product

                        int machineID = Integer.parseInt(formValues.get(7));

                        InHouse part = new InHouse(partID, machineID, partName, 
                                partPrice, partQty , partMin, partMax);

                        FXMLMainViewController.partsInventory.addPart(part);

                        // refresh the list
                        FXMLMainViewController.refreshList();

                        wasCreated = true;

                    }else if(formValues.get(0).equals("OutSourced")){

                        int partID = Integer.parseInt(formValues.get(1));
                        counter +=1;

                        String partName = formValues.get(2);
                        counter +=1;

                        int partQty = Integer.parseInt(formValues.get(3));
                        counter +=1;

                        double partPrice = Double.parseDouble(formValues.get(4));
                        counter +=1;

                        int partMax = Integer.parseInt(formValues.get(5));
                        counter +=1;

                        int partMin = Integer.parseInt(formValues.get(6));
                        counter +=1;

                        String companyName = formValues.get(8);

                        OutSourced part = new OutSourced(partID, companyName, partName, 
                                partPrice, partQty, partMin, partMax);

                        FXMLMainViewController.partsInventory.addPart(part);

                        FXMLMainViewController.refreshList();

                        wasCreated = true;

                    }
                    
                }catch(NumberFormatException e){
                    AlertMsg.errorPart(counter);
                    return false;

                }finally{
                    return wasCreated;
                }   
            }
            
        }catch(Exception e){
            return false;
        
        }finally{
            return wasCreated;
        }
    }
    
    /**
     * @info:   Add Part Form - reset form values to default
     *          ->> complete
     */
    @FXML public void resetToDefaultButtonClicked(ActionEvent event){
       reset();
    }
    
    /**
     * @info:  Add Part Form - Reset text fields
     *          ->> complete
     */
    public void reset(){
        this.idTextfield.setText("");
        this.nameTextfield.setText("");
        this.qtyTextfield.setText("");
        this.priceTextfield.setText("");
        this.maxTextfield.setText("");
        this.minTextfield.setText("");
        this.companyTextfield.setText("");
        this.inHouseRadioButton.setSelected(true);
        this.outSourcedRadioButton.setSelected(false);
        this.companyTextfield.setDisable(true);
        this.companyTextfield.setText("");
        this.machineIDTextfield.setText("");
        this.machineIDTextfield.setDisable(false); 
    }
    
    /**
     * @info:   Add Part Form - save button event
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void savePartButtonClicked(ActionEvent event) throws IOException{
        ArrayList formValues = getFormValues();

        try{
            boolean isValid = saveNewPart(formValues);
            
            if(isValid){
                Stage addPartStage = (Stage)savePartButton.getScene().getWindow();
                addPartStage.close();
            }
        }catch(Exception e){
            AlertMsg.generalAlert("Part was not created, Please try again!");
        }
    }
    
    /**
     * @info: Add Part - Cancel button event
     * @param event
     * @throws IOException
     *  ->> complete
     */
    @FXML public void cancelPartButtonClicked(ActionEvent event)throws IOException {

            boolean isConfirmed = AlertMsg.cancelConfirm();
            
            if(isConfirmed){
                Stage addPartStage = (Stage)cancelPartButton.getScene().getWindow();
                addPartStage.close();
            }        
    }
}
