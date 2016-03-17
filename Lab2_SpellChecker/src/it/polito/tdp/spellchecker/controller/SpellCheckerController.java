package it.polito.tdp.spellchecker.controller;

import java.awt.Font;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {
	
	ItalianDictionary iD;	
	EnglishDictionary eD;
	Text t;
	
	public void setModel(ItalianDictionary ita,EnglishDictionary eng){
		iD=ita;
		eD=eng;	
		
		cmbLang.getItems().add(iD);
		cmbLang.getItems().add(eD);
       
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Dictionary> cmbLang;

    @FXML
    private TextArea txtIn;

    @FXML
    private Button btnSpell;

    @FXML
    private TextFlow txtOut;

    @FXML
    private Label lblErr;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {
    	lblErr.setText("");
    	lblTime.setText("");
    	txtIn.clear();
    	txtOut.getChildren().clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	txtOut.getChildren().clear();
    	//exit no dizionario
    	if(cmbLang.getValue()==null){
    		txtOut.getChildren().add(new Text("Seleziona un dizionario"));
    		//txtOut.setText("Selezione un dizionario");
    		return;
    	}
    	//exit no testo input
    	if(txtIn.getText().compareTo("")==0){
    		txtOut.getChildren().add(new Text("Inserisci del testo da controllare"));
    		//txtOut.setText("Inserisci del testo da controllare");
    		return;
    	}
    	
    	lblErr.setText("");
    	txtOut.getChildren().clear();   	
    	List<String>elencoParole=new ArrayList<String>();
    	List<RichWord>paroleCorrette=new ArrayList<RichWord>();
    	//rimuove tutta la punteggiatura
    	String s=txtIn.getText().replaceAll("\\p{Punct}", "");
    	//separazione parole
    	String sArray[]=s.split(" ");
    	
    	//carico il vettore in una lista
    	if(sArray.length==0){
    		lblErr.setText("Inserisci del testo da controllare");
    		return;
    	}else{
    		for(int i=0;i<sArray.length;i++)
    			elencoParole.add(sArray[i].toLowerCase());
    	}
    	
    	
    	
    	//controllo ortografico
    	if(cmbLang.getValue()==iD){
    		//DIZIONARIO ITALIANO
    		iD.loadDictionary();
    		paroleCorrette.addAll(iD.spellCheckText(elencoParole));
    	}
    	else{
    		//DIZIONARIO INGLESE 
    		eD.loadDictionary();
    		paroleCorrette.addAll(eD.spellCheckText(elencoParole));
    	}
    	
    	s="";
    	//assemblo stringa risultato e la stampo in txtOut
    	//MANCA COLORE ROSSO PER PAROLE ERRATE
    	/*for(RichWord r:paroleCorrette){
    		if(r.isCorretto()==true)
    			s+=(r.getParola()+" ");
    		else{
    			s+=(r.getParola().toUpperCase()+" ");
    			lblErr.setText("Your text contains errors");
    		}
    	}*/
    	
    	for(RichWord r:paroleCorrette){
		if(r.isCorretto()==true){
			txtOut.getChildren().add(new Text((r.getParola()+" ")));
			//s+=(r.getParola()+" ");
		}
		else{
			Text t1 =new Text(r.getParola()+" ");
			t1.setFill(Color.BLUE);
			//t1.setText(r.getParola().toUpperCase()+" ");	
			
			//s+=(r.getParola().toUpperCase()+" ");
			txtOut.getChildren().addAll(t1);
			lblErr.setText("Your text contains errors");
		}
	}
    	//txtOut.getChildren().clear();

    }

    @FXML
    void initialize() {
        assert cmbLang != null : "fx:id=\"cmbLang\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtIn != null : "fx:id=\"txtIn\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOut != null : "fx:id=\"txtOut\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErr != null : "fx:id=\"lblErr\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";           
        
        t=new Text("ciao");
        t.setFill(Color.RED);        
       
       
               
        lblErr.setText("");
        
    }
}
