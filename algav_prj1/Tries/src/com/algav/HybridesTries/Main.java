package com.algav.HybridesTries;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	
	public static void main(String[] args){
		
		HybridesTries hVide = new HybridesTries();
		HybridesTries h ;
		//h = Ajout.ajoutString("A", hVide, new Value(1));
		//h = Ajout.ajoutString("quel", h, new Value(2));
		/*String text = "A quel genial professeur de dactylographie sommes nous "
				+ "redevable de la superbe phrase ci dessou , un modele du genre"
				+ " , que toute dactylo connait par coeur puisque elle fait"
				+ " appel a chacune des touches du clavier de la machine"
				+ " a ecrire ?" ;*/
		//String text = "A quel quelle quotient quadra";
		
		//String text= "apprend quel appeine";
		String text = "apprend appeine quel $";
		h = Ajout.ajoutText(text, hVide);
		
		
		//h=Ajout.ajoutText("SARRA", h);
		//System.out.println(HybridesTries.AfficheHybride(h));
		//System.out.println(h.comptageMots());
		//boolean b = Ajout.recherche(h, "SARA");
				//System.out.println(b);
		//System.out.println(Ajout.comptageMots(h));
		//ArrayList<String> l = Ajout.liste(h);
		//System.out.println(l);
		/*System.out.println("nb de mot: "+Ajout.comptageMots(h)+"\n"+
		"Hauteur :"+Ajout.hauteur(h)+"\n"+"Nbr de Nill :"+Ajout.nbTrieVide(h)
		+"\n Taille de l'arbre :"+Ajout.nbNoeud(h)+"\n Nombre de feuillle"
				+ ": "+Ajout.nbFeuille(h)+"\n profondeur moyenne:"
				+Ajout.profondeurMoyenne(h));*/
		System.out.println(Ajout.liste(h).toString());
		HybridesTries g = Ajout.supression(h, "apprend");
		System.out.println(Ajout.liste(g).toString());
		System.out.println((int) '$');
		
		
	}
}
