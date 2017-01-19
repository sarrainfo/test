package com.algav.patricia;

import com.algav.patricia.exceptions.PATStringException;


public class PATCase implements IPATCase{
	
	private String word;
	private IPatriciaTrie son;
	
	public PATCase(String mot, IPatriciaTrie fils){
		this.word = mot;
		this.son = fils;
	}
	
	public PATCase(String mot){
		this.word = mot;
		this.son = null;
	}
	
	public String getWord() {
		return word;
	}
	
	//Make sure this can only be used if original string has gone 
		//through validString() method before
	//note: replaces current word!
	public void setWord(String word) {
		this.word = word;
	}
	public IPatriciaTrie getSon() {
		return son;
	}
	public void setSon(IPatriciaTrie son) {
		this.son = son;
	}
	
	public boolean hasSon(){
		if (this.getSon() == null)
			return true;
		return false;
	}
	
	public static String stringValid(String s){
		for (int i = 0; i < s.length(); ++i){
			char c = s.charAt(i);
			int ascii = (int)c;
			//on utilise les caracters ASCII d'indice 1 a 127
			if (ascii < 1 || ascii > 127)
				throw new PATStringException("String out of character set\n");
		}
		return s;
	}

}
