package com.algav;

import java.util.ArrayList;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;

public class Comparaison3 {
	
	public static void construction(ArrayList<String> words){
		System.out.print("dans l'hybride ...");
		double hybride = Calculation3.constructionHybride(words);
		System.out.println("Calcule terminer ");
		System.out.print("dans le patricia ...");
		double patricia = Calculation3.constructionPatricia(words);
		System.out.println("calcule termine");
		//double hybrideEquilibre = Calculation3.constructionHybrideEquilibre(words);
		System.out.println("Le temps de calcule pour la construction des structures"
				+ " pour les hybrides :"+hybride+" milliseconde"
						+ "\n pour les patricia :"+patricia+" milliseconde"
								+ "\n et pour les hybrides equilibre"/*+hybrideEquilibre*/+" milliseconde");
	}
	// he est un arbre equilibre
	public static void addWord(HybridesTries h, PatriciaTrie p,HybridesTries he,
			String word){
		System.out.println("calcule du temps d'ajout dans l'hybride en cours ...");
		double hybride = Calculation3.addNewWordHybride(word, h);
		System.out.println("fin du calcule dans l'hybride");
		System.out.println("calcule du temps d'ajout dans le patricia en cours ...");
		double patricia = Calculation3.addNewWordPatricia(word, p);
		System.out.println("fin du calcule dans le patricia");
		double hybrideEquilibre = Calculation3.addNewWordHybride(word, he);
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
		double hybride = Calculation3.deleteWordsHybride(h, words);
		System.out.println("fin du calcule de l'hybride ");
		System.out.println("calcule du temps de supressions dans un patricia en cours ...");
		double patricia = Calculation3.deleteWordsPatricia(p, words);
		System.out.println("fin du calcule du patricia");
		double hybrideEquilibre = Calculation3.deleteWordsHybride(he, words);
		System.out.println("Le temps de calcule pour la suppressions des mots:\n"
				+ " dans un hybride :"+hybride+" milliseconde"
						+ "\n dans un patricia :"+patricia+" milliseconde\n"
								+ "et enfin dans un hybride equilibre: "+hybrideEquilibre);
	}
	
	//he un arbre equilibre
	public static void profondeur(HybridesTries h, PatriciaTrie p, HybridesTries he){
		double hybride = Ajout.profondeurMoyenne(h);
		double patricia = p.profondeurMoyenne();
		double hybrideEquilibre = Ajout.profondeurMoyenne(he);
		System.out.println("La profondeur pour l'hybride :"+hybride+"\n"
				+ "pour le patricia :"+patricia
				+"\n et enfin pour l'hybride equilibre: "+hybrideEquilibre);
	}
	
	public static void searchWord(HybridesTries h, PatriciaTrie p, HybridesTries heq
			,String word){
		double hybride = Calculation3.searchWord(h, word);
		double patricia = Calculation3.searchWord(p, word);
		double hybrideEquilibre = Calculation3.searchWord(heq, word);
		System.out.println("Le temps de calcule pour la recherche du mot: "
				+word+"\n"
				+ " dans un hybride :"+hybride+" milliseconde"
						+ "\n dans un patricia :"+patricia+" milliseconde\n"
								+ "et enfin dans un hybride equilibre: "+hybrideEquilibre);
	}
	public static void main(String args[]){
		HybridesTries h = new HybridesTries();
		h = Ajout.ajoutString("bohneur", h);
		PatriciaTrie p = new PatriciaTrie("bohneur");
		//Comparaison.addWord(h, p, "ciel");
		//Comparaison.deleteWords(h, p, "bohneur");
	}

}
