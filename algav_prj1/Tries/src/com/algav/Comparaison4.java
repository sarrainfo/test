package com.algav;

import java.util.ArrayList;

import com.algav.HybridesTries.FonctionHybrideTries;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;

public class Comparaison4 {
	
	public static void construction(ArrayList<String> words){
		System.out.print("dans l'hybride...");
		double hybride = Calculation4.constructionHybride(words);
		System.out.println("calcule terminer dans l'hybride.");
		System.out.print("Dans le patricia ...");
		double patricia = Calculation4.constructionPatricia(words);
		System.out.println("calcule termine dans le patricia");
		System.out.println("Le temps de calcule pour la construction des structures"
				+ " pour les hybrides :"+hybride+" milliseconde"
						+ "\n pour les patricia :"+patricia+" milliseconde");
	}
	// he est un arbre equilibre
	public static void addWord(HybridesTries h, PatriciaTrie p,HybridesTries he,
			String word){
		System.out.println("calcule du temps d'ajout dans l'hybride en cours ...");
		double hybride = Calculation4.addNewWordHybride(word, h);
		System.out.println("fin du calcule dans l'hybride");
		System.out.println("calcule du temps d'ajout dans le patricia en cours ...");
		double patricia = Calculation4.addNewWordPatricia(word, p);
		System.out.println("fin du calcule dans le patricia");
		double hybrideEquilibre = Calculation4.addNewWordHybride(word, he);
		System.out.println("Le temps de calcule pour l'ajout  du mot: "+word+"\n"
				+ " dans un hybride :"+hybride+" milliseconde"
						+ "\n dans un patricia :"+patricia+" milliseconde \n"
								+ "et enfin dans un hybride equilibre: "+hybrideEquilibre);
		
	}
	//he un arbre equilibre
	public static void deleteWords(HybridesTries h, PatriciaTrie p,
			HybridesTries he,ArrayList<String> words) 
					throws CloneNotSupportedException{
		System.out.println("calcule du temps de supression dans un hybride en cours ...");
		double hybride = Calculation4.deleteWordsHybride(h, words);
		System.out.println("fin du calcule de l'hybride ");
		System.out.println("calcule du temps de supressions dans un patricia en cours ...");
		double patricia = Calculation4.deleteWordsPatricia(p, words);
		System.out.println("fin du calcule du patricia");
		double hybrideEquilibre = Calculation4.deleteWordsHybride(he, words);
		System.out.println("Le temps de calcule pour la suppressions des mots:\n"
				+ " dans un hybride :"+hybride+" milliseconde"
						+ "\n dans un patricia :"+patricia+" milliseconde\n"
								+ "et enfin dans un hybride equilibre: "+hybrideEquilibre);
	}
	
	//he un arbre equilibre
	public static void profondeur(HybridesTries h, PatriciaTrie p, HybridesTries he){
		FonctionHybrideTries f = new FonctionHybrideTries();
		double hybride = f.profondeurMoyenne(h);
		double patricia = p.profondeurMoyenne();
		double hybrideEquilibre = f.profondeurMoyenne(he);
		System.out.println("La profondeur pour l'hybride :"+hybride+"\n"
				+ "pour le patricia :"+patricia
				+"\n et enfin pour l'hybride equilibre: "+hybrideEquilibre);
	}
	
	public static void searchWord(HybridesTries h, PatriciaTrie p, HybridesTries heq
			,String word){
		double hybride = Calculation4.searchWord(h, word);
		double patricia = Calculation4.searchWord(p, word);
		double hybrideEquilibre = Calculation4.searchWord(heq, word);
		System.out.println("Le temps de calcule pour la recherche du mot: "
				+word+"\n"
				+ " dans un hybride :"+hybride+" milliseconde"
						+ "\n dans un patricia :"+patricia+" milliseconde\n"
								+ "et enfin dans un hybride equilibre: "+hybrideEquilibre);
	}
	public static void main(String args[]){
		HybridesTries h = new HybridesTries();
		//h = FonctionHybrideTries.ajoutString("bohneur", h);
		PatriciaTrie p = new PatriciaTrie("bohneur");
		//Comparaison.addWord(h, p, "ciel");
		//Comparaison.deleteWords(h, p, "bohneur");
	}

}
