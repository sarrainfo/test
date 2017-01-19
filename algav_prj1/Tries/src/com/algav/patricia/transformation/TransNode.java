package com.algav.patricia.transformation;

import java.util.LinkedList;

public class TransNode {
	public TransNode(String word, LinkedList<TransNode> son){
		this.son = son;
		this.word = word;
	}
	private String word;
	private LinkedList<TransNode> son;
	
	public String getWord() {
		return word;
	}
	public LinkedList<TransNode> getSon() {
		return son;
	}
	
	public void setSon(LinkedList<TransNode> l){
		this.son = l;
	}
	
	public void setWord(String word){
		this.word = word;
	}
	
	public void deleteSon(){
		this.son = null;
	}
	
}
