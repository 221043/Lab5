package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ruzzle.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class RuzzleController {

	private Model model = new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenera;

    @FXML
    private ListView<String> txtParole;
    
    @FXML
    private GridPane griglia;

    public void setModel(Model model){
    	this.model = model;
    }
    
    @FXML
    void doGenera(ActionEvent event) {
    	btnGenera.setDisable(true);
    	for(int i=0; i<model.getDimensione(); i++){
    		for(int j=0; j<model.getDimensione(); j++){
    			Label l = new Label();
    			l.setMaxSize(40, 40);
    			l.setAlignment(Pos.CENTER);
    			l.setText(model.generaLettera(j, i).toUpperCase());
    			griglia.add(l, i, j);
    		}
    	}
    	for(Node n:griglia.getChildren()){
			((Region) n).setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	}
    	ObservableList<String> parole = FXCollections.observableArrayList(model.solution());
    	txtParole.setItems(parole);
    	btnGenera.setDisable(false);
    }


    @FXML
    void doSeleziona(MouseEvent event) {
    	for(Node n:griglia.getChildren()){
				((Region) n).setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		}
    	String parola = txtParole.getSelectionModel().getSelectedItem();	
    	List<Posizione> lista = model.getPosizioniDaColorare(parola);
    	for(Posizione p:lista){
    		int r = p.getRiga();
    		int c = p.getCol();
    		for(Node n:griglia.getChildren()){
    			if(GridPane.getColumnIndex(n)==c && GridPane.getRowIndex(n)==r)
    				((Region) n).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    		}
    	}
    }
    
    @FXML
    void initialize() {
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        assert griglia != null : "fx:id=\"griglia\" was not injected: check your FXML file 'Ruzzle.fxml'.";
        
    }
    
}
