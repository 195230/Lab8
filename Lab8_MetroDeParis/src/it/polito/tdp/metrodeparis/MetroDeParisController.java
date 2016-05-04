package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermataL;
import it.polito.tdp.metrodeparis.model.Linea;
import it.polito.tdp.metrodeparis.model.MetroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private MetroModel model= new MetroModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> cmbPartenza;

    @FXML
    private ComboBox<Fermata> cmbArrivo;

    @FXML
    private Button btnCalcolo;

    @FXML
    private TextArea txtArea;

    @FXML
    void calcolaDistanza(ActionEvent event) {
    	
    	model.createGraph1();

    	Fermata p= cmbPartenza.getValue();
    	Fermata a = cmbArrivo.getValue();
    	
    	Linea l = model.getLbyF(p).get(0);
    	Linea l2 = model.getLbyF(a).get(0);
    	
    	FermataL partenza= new FermataL( p.getId(), p.getNomeFermata(), p.getCordX(), p.getCordY(), l);
    	FermataL arrivo= new FermataL( a.getId(), a.getNomeFermata(), a.getCordX(), a.getCordY(), l2);
    	    	
    	txtArea.setText(model.percorso(model.cercaPercorsoLinee(partenza, arrivo)));
    	txtArea.appendText("\n"+model.calcolaTempoLinee(partenza, arrivo));
    	
    }

    @FXML
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcolo != null : "fx:id=\"btnCalcolo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    
        
        cmbPartenza.getItems().addAll(model.getFermate());
        cmbArrivo.getItems().addAll(model.getFermate());
    }
}
