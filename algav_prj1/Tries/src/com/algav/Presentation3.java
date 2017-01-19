package com.algav;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;

public class Presentation3 {
	public static void main (String args[]) throws CloneNotSupportedException{
		//String filename = "Tries/shakespeare/macbeth.txt";
		String filename = "./shakespeare/macbeth.txt";
		ArrayList<String> wordsInFile = null;
		ArrayList<String> wordsInFileWithoutDuplicate =null;
		HybridesTries h = new HybridesTries();
		HybridesTries heq = new HybridesTries();
		PatriciaTrie p = new PatriciaTrie();
		String word2add = "maison";
		ArrayList<String> deletedWords = null;
		String searchWord = "pa";
		int nbWords2Del = 20;
		try {
			 wordsInFile = Utility.convert2list(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Nous allons creez 3 arbres: Hb, Pat et HbEq");
		System.out.print("Construction de "
				+ "l'hybrideTrie en cours d'execution ...");
		
		for(int i = 0 ; i < wordsInFile.size();i++){
			  h = Ajout.ajoutString(wordsInFile.get(i), h);
			  heq = Ajout.ajoutString(wordsInFile.get(i),heq);
			  
		 }
		System.out.println("Fin de construction de l'hybride");
		
		System.out.print("Construction du "
				+ " patriciatrie en cours d'execution ...");
		
		for(int i = 0 ; i < wordsInFile.size();i++){
			  p.ajout(wordsInFile.get(i));
			 // heq = Ajout.ajoutStringEquilibrage(wordsInFile.get(i), heq);
			  
		 }
		System.out.println("Fin de construction du le patricia");
		
		/*System.out.println("Calcule du temps de construction pour les 3 arbres ..."
				+ "Ceci peut prendre quelque minutes");
		Comparaison3.construction(wordsInFile);*/
		System.out.println("equilibrage de notre hybride en cours...");
		heq = Ajout.equilibrage(heq);
		//System.out.println("le temps d'equilibrage est :"+Calculation3.equilibrage(h));
		
		try {
			wordsInFileWithoutDuplicate = Utility.convert2listWithoutDuplicate(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		deletedWords = Utility.randomWords(wordsInFileWithoutDuplicate,
				nbWords2Del, wordsInFileWithoutDuplicate.size());
		
	/*	deletedWords = new ArrayList<String>();
		deletedWords.add("now");*/
	System.out.println("Supression des mots :"+deletedWords);
		Comparaison3.deleteWords(h, p, heq, deletedWords);
		System.out.println("On ajoute a nos structure le mot :"+word2add);
		Comparaison3.addWord(h, p, heq, word2add);
		System.out.println("calcule de la profondeur de nos structure");
		Comparaison3.profondeur(h, p, heq);
		System.out.println("cherchons le mot :"+searchWord+" dans nos structures");
		Comparaison3.searchWord(h, p, heq, searchWord);
		System.out.println("hauteur du hybride :"+ Ajout.hauteur(h));
		System.out.println("hauteur de  patricia "+p.hauteur());
	//	Comparaison3.searchWord(h, p, heq, searchWord);
		
		
	}
	
	

}
