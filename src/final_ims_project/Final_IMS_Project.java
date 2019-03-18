
package final_ims_project;

import final_ims_project.Models.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ty
 */
public class Final_IMS_Project extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ViewsAndControllers/FXMLMainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
    
}
