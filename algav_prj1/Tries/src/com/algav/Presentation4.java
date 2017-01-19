package com.algav;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.algav.HybridesTries.FonctionHybrideTries;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Presentation4{
	public static void main (String args[]) throws CloneNotSupportedException{
		FonctionHybrideTries f = new FonctionHybrideTries();
		String directory = "./shakespeare";
		//ArrayList<String> wordsInFile = null;
		HashSet<String> wordsInFiles = null;
		//ArrayList<String> wordsInFiles = null;

		ArrayList<String> wordsInFileWithoutDuplicate =null;
		HybridesTries h = new HybridesTries();
		HybridesTries heq = new HybridesTries();
		PatriciaTrie p = new PatriciaTrie();
		String word2add ="savoir";
		ArrayList<String> deletedWords = null;
		String searchWord = "the";
		int nbWords2Del = 5;
		
		// Recupere tous les mots 
		try {
			wordsInFiles = Utility4.allFilleIndirectory(directory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("taille: " + wordsInFiles.size());
		System.out.println("Nous allons creez 3 arbres: HybrideTrie, PatriciaTrie et"
				+ " HybrideTrie Equilibré");
		System.out.print("Construction de "
				+ "l'hybrideTrie en cours d'execution ...");
		java.util.Iterator<String> it = wordsInFiles.iterator();
		
		String next;
		while(it.hasNext()){
			next = it.next();
			h = f.ajoutString(next, h);
			heq = f.ajoutString(next, heq);
		}
		System.out.println("Fin de construction de l'hybride");
		
		System.out.print("Construction du "
				+ " patriciatrie en cours d'execution ...");
		java.util.Iterator<String> pt = wordsInFiles.iterator();
		while(pt.hasNext()){
			p.ajout(pt.next());
		}
		
		System.out.println("Fin de construction du patricia");
		
		System.out.println("Calcule du temps de construction pour les 3 arbres ...");
		ArrayList<String> a = new ArrayList<String>();
		a.addAll(wordsInFiles);
		Comparaison4.construction(a);
		System.out.println("equilibrage de notre hybride en cours...");
		heq = f.equilibrage(heq);
		System.out.println("le temps d'equilibrage est :"+Calculation4.equilibrage(h));
		
		
		//liste de mots a supprimer pri au hasard
		ArrayList<String> w = new ArrayList<String>() ;
		w.addAll(wordsInFiles);
		deletedWords = Utility4.randomWords(w,
				nbWords2Del, wordsInFiles.size());
		System.out.println("Supression des mots :"+deletedWords);
		Comparaison4.deleteWords(h, p, heq, deletedWords);
		System.out.println("On ajoute a nos structure le mot :"+word2add);
		Comparaison4.addWord(h, p, heq, word2add);
		System.out.println("cherchons le mot :"+searchWord+" dans nos structures");
		Comparaison4.searchWord(h, p, heq, searchWord);
		System.out.println("calcule de la profondeur de nos structure");
		Comparaison.profondeur(h, p, heq);
		
	}
	
	

}
