package com.algav.hybride;

public class HybrideNotWordException extends HybrideException{
	
	String msg ;
	public HybrideNotWordException(String mot){
		super("l'arbre ne contient pas de mot dont le prefixe est :"+mot);
		this.msg = "l'arbre ne contient pas de mot dont le prefixe est :"+mot;		
	}
	
	public String getMessage(){
		return msg;
	}
}
