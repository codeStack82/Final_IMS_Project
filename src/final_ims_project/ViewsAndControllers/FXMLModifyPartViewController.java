
package final_ims_project.ViewsAndControllers;

import final_ims_project.AlertMsg;
import final_ims_project.Models.InHouse;
import final_ims_project.Models.OutSourced;
import final_ims_project.Models.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import final_ims_project.ViewsAndControllers.FXMLMainViewController;

/**
 * FXML Modify Controller class
 * @author Ty
 */
public class FXMLModifyPartViewController implements Initializable {

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
    
    private InHouse inPart;
    private OutSourced outPart;
    private Part oldPart;
    
    private String sourceType;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        //Toggle Group for radio buttons
        this.partTypeToggleGroup =  new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partTypeToggleGroup);
        this.outSourcedRadioButton.setToggleGroup(partTypeToggleGroup);
        
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
     * @info:   Add Part Form - Receives data from main TabPane form
     * @param:  Part part from main form
     *          ->> complete
     */
    public void initData(Part part){
        
        oldPart = (Part)part;
        
        try{
            if(part instanceof InHouse){
                inPart = (InHouse)part;
                
                sourceType = "InHouse";
                inHouseRadioButton.setSelected(true);
                outSourcedRadioButton.setSelected(false);
                
                machineIDTextfield.setDisable(false);
                companyTextfield.setDisable(true);
                
                machineIDTextfield.setText(Integer.toString(inPart.getMachineID()));
                idTextfield.setText(Integer.toString(inPart.getPartID()));
                idTextfield.setDisable(true);
                nameTextfield.setText(inPart.getName());
                priceTextfield.setText(Double.toString(inPart.getPrice()));
                qtyTextfield.setText(Integer.toString(inPart.getInStock()));
                maxTextfield.setText(Integer.toString(inPart.getMax()));
                minTextfield.setText(Integer.toString(inPart.getMin()));
                
            }else if(part instanceof OutSourced){
                outPart = (OutSourced)part;
                
                inHouseRadioButton.setSelected(false);
                outSourcedRadioButton.setSelected(true);
                
                sourceType = "OutSourced";
                machineIDTextfield.setDisable(true);
                companyTextfield.setDisable(false);
                
                companyTextfield.setText(outPart.getCompanyName());
                idTextfield.setText(Integer.toString(outPart.getPartID()));
                idTextfield.setDisable(true);
                nameTextfield.setText(outPart.getName());
                priceTextfield.setText(Double.toString(outPart.getPrice()));
                qtyTextfield.setText(Integer.toString(outPart.getInStock()));
                maxTextfield.setText(Integer.toString(outPart.getMax()));
                minTextfield.setText(Integer.toString(outPart.getMin()));

            }
        }catch(Exception e){
            
        }
    }
    
    /**
     * @info:   Modify Part Form - Reset form values to default
     *          - >> complete
     */
    public void resetToDefault(){
        this.nameTextfield.setText("");
        this.qtyTextfield.setText("");
        this.priceTextfield.setText("");
        this.maxTextfield.setText("");
        this.minTextfield.setText("");
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
     * @info:   save Part Form - Gets the Add Part form values
     * @return: boolean isValid 
     *          ->> complete
     */
    public boolean partIsValid(ArrayList<String> formValues){
        
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
            
            //inhouse 
            if(formValues.get(0).equals("InHouse") && formValues.get(7).trim().isEmpty() ){
                AlertMsg.errorPart(7);
                return false;
            }
            
            //outsourced
            if(formValues.get(0).equals("OutSourced") && formValues.get(8).trim().isEmpty()){
                AlertMsg.errorPart(8);
                return false;
            }
            
            //Check min, max, and qty
            int min = Integer.parseInt(formValues.get(6));
            int max = Integer.parseInt(formValues.get(5));
            int qty = Integer.parseInt(formValues.get(3));
            
            if(min > max || max < min){
                AlertMsg.errorPart(9);
                return false;
            }
            
            if(qty < min || qty > max){
                AlertMsg.errorPart(10);
                return false;
            }
            
            //All conditions passed
            isValid = true;
            
        }catch(Exception e){
            return false;
        
        }finally{
            return isValid;
        } 
    
    }
    
    /**
     * @info:   Modified Part Form - modify part
     *          ->> complete
     */
    public Part modifiedPart(ArrayList<String> formValues){
        int counter = 1;
        
        boolean isValid = partIsValid(formValues);
        if(isValid){
            try{

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

                    int machineID = Integer.parseInt(formValues.get(7));

                    inPart = new InHouse(partID, machineID, partName, 
                            partPrice, partQty , partMin, partMax);

                    return (Part)inPart;

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

                    outPart = new OutSourced(partID, companyName, partName, 
                            partPrice, partQty, partMin, partMax);

                    return (Part)outPart;
                }

            }catch(NumberFormatException e){
                AlertMsg.errorPart(counter);
                return null;
            }
        }else{
            AlertMsg.errorPart(-1);
        }
        return null;
    }
    
    /**
     * @info:   Modify Part Form - Save Part button 
     * @param   ActionEventevent
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void savePartButton(ActionEvent event)throws IOException {
        //Get Form Values
        ArrayList formValues = getFormValues();
      
        //Create new Part
        try{
            boolean isValid = partIsValid(formValues);
            //System.out.println("isValid:"+ isValid);
            
            if(isValid){
                
                Part modPart = modifiedPart(formValues);
                
                boolean isConfirmed = AlertMsg.modifyPartConfirm();
                
                if(isConfirmed){
                    FXMLMainViewController.partsInventory.updatePart(oldPart, modPart);
                    FXMLMainViewController.refreshList();
                    
                    //Close and return
                    Stage modifyPartStage = (Stage)savePartButton.getScene().getWindow();
                    modifyPartStage.close();
                }

            }else{
                AlertMsg.modifyPartError();
            }

        }catch(Exception e){
            
        }
    }
    
     /**
     * @info:   Modify Part Form - Cancel button event
     * @param   ActionEvent event
     * @throws  IOException
     *          ->> complete
     */
    @FXML public void cancelPartButton(ActionEvent event) throws IOException{
        
        boolean isConfirmed = AlertMsg.cancelConfirm();
            
            if(isConfirmed){
                Stage addPartStage = (Stage)cancelPartButton.getScene().getWindow();
                addPartStage.close();
            }
    }
    
}
