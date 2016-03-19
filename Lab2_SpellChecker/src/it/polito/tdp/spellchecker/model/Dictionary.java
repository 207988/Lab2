package it.polito.tdp.spellchecker.model;
import java.util.*;

public abstract class Dictionary {
	
	public List<String>dizionario=new ArrayList<String>();
	private int auxDic;
	private int n;
	private boolean flag;
	
	public abstract void loadDictionary();
	
	public void aggiungiParola(String s){
		dizionario.add(s);
	}
	
	public List<RichWord> spellCheckText(List<String>inputTextList){
		List<RichWord> ris=new ArrayList<RichWord>();		
		
		//Aggiungo la parola da input a ris come rW errata
		//Dopo controllo se e' nel dizionario e nel caso metto
		//true il suo stato
		
				/*
		for(String s:inputTextList){			
			if(dizionario.contains(s))
				ris.add(new RichWord(s,true));
			else
				ris.add(new RichWord(s,false));
		}
		*/
		for(String s:inputTextList){
			auxDic=dizionario.size()/2;
			n=0;
			flag=false;
			if(this.ricercaDicotomica(s, -1))
				ris.add(new RichWord(s,true));
			else
				ris.add(new RichWord(s,false));
		}
		return ris;
	}
	
	public boolean ricercaDicotomica(String word,int pos){		
		//System.out.println(++n);
		
		
		/*if((pos<1||pos>dizionario.size())&&pos!=-1){
			System.err.println("OUT OF BOUND");
			return false;
		}*/
		
		auxDic=auxDic/2;
				
		
		if(pos==-1){
			//PRIMO AVVIO
			
			pos=dizionario.size()/2;
			int comp=word.compareTo(dizionario.get(pos-1));
			if(comp==0)
				return true;
			else if(comp<0)
				this.ricercaDicotomica(word, pos/2);
			else
				this.ricercaDicotomica(word, pos+pos/2);			
		}	
		
		int comp=word.compareTo(dizionario.get(pos-1));
		if(comp==0){
			//PAROLA TROVATA
			return true;
		}
		else if(auxDic==0||auxDic==1){
			//PAROLA NON TROVATA
			return flag;
		}
		else if(flag)
			return flag;
		else if(comp>0){
			//PAROLA ZONA SUCCESSIVA
			flag=this.ricercaDicotomica(word, pos+auxDic);
		}else if(comp<0){
			//PAROLA ZONA PRECEDENTE
			flag=this.ricercaDicotomica(word, pos-auxDic);
		}		
			
		
		return flag;
	}
	
}
