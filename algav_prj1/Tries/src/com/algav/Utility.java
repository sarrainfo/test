package com.algav;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Utility {
	// renvoi la liste des mots du fichier filename 
	public static ArrayList<String> convert2list(String filename) 
			throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		ArrayList<String> words = new ArrayList<String>();
		while(scanner.hasNext()){
			words.add(scanner.next());
		}
		return words;
	}
	// tire nbWords au hasard a supprimer de words
	public static ArrayList<String> randomWords(ArrayList<String> words, int nbWords,
			int limit){
		int index;
		ArrayList<String> toDeleteWords = new ArrayList<String>();
		Random random = new Random();
		for(int i = 0; i < nbWords; i ++){
			index = random.nextInt(limit);
			toDeleteWords.add(words.get(index));
		}
		return  toDeleteWords;
	}
	
	//renvoi la liste des mots du fichier filename sans doublons
	public static ArrayList<String> convert2listWithoutDuplicate(String filename) 
			throws FileNotFoundException{
		
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		ArrayList<String> words = new ArrayList<String>();
		String word = null;
		while(scanner.hasNext()){
			word = scanner.next();
			if (!(words.contains(word))){
				words.add(word);
			}
		
		}
		return words;
	}
	

	

}
