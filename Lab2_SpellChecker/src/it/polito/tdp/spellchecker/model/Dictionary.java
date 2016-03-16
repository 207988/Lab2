package it.polito.tdp.spellchecker.model;
import java.util.*;

public abstract class Dictionary {
	
	public List<RichWord>dizionario=new ArrayList<RichWord>();

	
	public abstract String loadDictionary();
	
	public List<RichWord> spellCheckText(List<String>inputTextList){
		List<RichWord> ris=new ArrayList<RichWord>();
		RichWord r;
		//Aggiungo la parola da input a ris come rW errata
		//Dopo controllo se e' nel dizionario e nel caso metto
		//true il suo stato
		for(String s:inputTextList){
			ris.add(r=new RichWord(s,false));
			if(dizionario.contains(r))
				r.setCorretto(true);			
		}
		return ris;
	}
	
}
