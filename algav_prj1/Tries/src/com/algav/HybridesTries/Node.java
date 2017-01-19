package com.algav.HybridesTries;

public class Node {
	// key correspond au chararctere a inserer, 
	private Value value;
	private char key;
	
	public Node(char key,Value value){
		this.value = value;
		this.key = key;
	}
	
	public boolean nodeVide(){
		if (key == '#')
			return true;
		return false;
	}
	
	public char getKey(){
		return key;
	}
	public Value getValue(){
		return value;
	}
	public void setValue(Value n){
		this.value = n;
	}

}
