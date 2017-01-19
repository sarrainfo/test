package com.algav.HybridesTries;

public class ValueNonVide implements Value{
	public static int value =0;
	public int finMots ;
	
	public ValueNonVide(){
		finMots = value;
		value+=1;
		
	}
	public int getValeurValue(){
	return finMots;
	}
}
