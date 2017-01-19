
package com.algav.patricia;


public interface IPATCase {

	String getWord();
	
	
	void setWord(String word);
	
	IPatriciaTrie getSon();
	
	void setSon(IPatriciaTrie son);
	
	 boolean hasSon();
}
