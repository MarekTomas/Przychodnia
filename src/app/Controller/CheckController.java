package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckController {

    @FXML
    private TextArea ta_informacion;

    @FXML
    private Button bt_send;

    @FXML
    private Button bt_close;
    
    //public static  String message;

    @FXML
    void ButtonClose(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PacjView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void ButtonSend(MouseEvent event) throws IOException {
    	//message = ta_informacion.getText();
    	
    	Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PacjView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

    	
    	
    	
    	 
    }

}