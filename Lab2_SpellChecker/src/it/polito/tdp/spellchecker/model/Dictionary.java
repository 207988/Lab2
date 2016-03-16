package it.polito.tdp.spellchecker.model;
import java.util.*;

public abstract class Dictionary {
	
	public List<String>dizionario=new ArrayList<String>();

	public abstract void loadDictionary();
	
	public void aggiungiParola(String s){
		dizionario.add(s);
	}
	
	public List<RichWord> spellCheckText(List<String>inputTextList){
		List<RichWord> ris=new ArrayList<RichWord>();
		
		//Aggiungo la parola da input a ris come rW errata
		//Dopo controllo se e' nel dizionario e nel caso metto
		//true il suo stato
		
		for(String s:inputTextList){			
			if(dizionario.contains(s))
				ris.add(new RichWord(s,true));
			else
				ris.add(new RichWord(s,false));
		}
		return ris;
	}
	
}
