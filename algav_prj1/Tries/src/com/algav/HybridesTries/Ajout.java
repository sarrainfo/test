package com.algav.HybridesTries;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import com.algav.HybridesTries.InvalidCharactereException;

public class Ajout {
	
	
	public static HybridesTries ajoutString(String key,HybridesTries a){
		if( a.isVide()){
			HybridesTries infVide = new HybridesTries();
			HybridesTries supVide = new HybridesTries();
			HybridesTries eqVide = new HybridesTries();
			if( key.length() == 1){
				
				Node n = new Node(key.charAt(0),new ValueNonVide());
				HybridesTries h = new HybridesTries(infVide, eqVide, supVide,n);
				return h;
			}
			else{
				Node n = new Node(key.charAt(0),new ValueVide());
				a.setEq(eqVide);
				return new HybridesTries( infVide,
						ajoutString(key.substring(1),a.getEq()), supVide,n);
				
			}		
			
		}
		
		else {
			char p = key.charAt(0);
			if(p < a.getRacine().getKey()) {
				return new HybridesTries(ajoutString(key, a.getInf()),
						a.getEq(), a.getSup(),a.getRacine());
			}
			
			if(p>a.getRacine().getKey()){
				return new HybridesTries(a.getInf(), a.getEq(),
						ajoutString(key,a.getSup()), a.getRacine());
			}
			if( key.length() > 1){
				return new HybridesTries(a.getInf(),ajoutString(key.substring(1),
					a.getEq()),a.getSup(),a.getRacine());
			}
			if(a.getRacine().getValue() instanceof ValueVide){
				a.getRacine().setValue(new ValueNonVide());
			}
			return a;
			
		}
	}
	
	
	//apres l'ajout d'un mot, reequilibrage
	public static HybridesTries ajoutStringEquilibrage(String mot,HybridesTries h){
		HybridesTries a =ajoutString(mot, h);
		return equilibrage(a);
	}
	public static HybridesTries ajoutText(String s, HybridesTries h)  {
		HybridesTries newH = h;
		try
		{
			String[] splitArray = s.split(" ");
			for (int i = 0 ; i < splitArray.length; i++){
				if(verifie(splitArray[i])){
				newH = ajoutString(splitArray[i],newH);
				}
			}
		}catch (StringIndexOutOfBoundsException e){
			System.out.println("retirer les espaces en trop");
			
		} catch (InvalidCharactereException e) {
			System.out.println("les characteres entrer ne sont pas conforme");
			e.printStackTrace();
		}
		
		return newH;
	}
	
	//apres chaque ajout d'un mot dans l'arbre on equilibre
	public static HybridesTries ajoutTextEquilibrage(String s, HybridesTries h)  {
		HybridesTries newH = h;
		try
		{
			String[] splitArray = s.split(" ");
			for (int i = 0 ; i < splitArray.length; i++){
				if(verifie(splitArray[i])){
				newH = ajoutStringEquilibrage(splitArray[i],newH);
				
				}
			}
		}catch (StringIndexOutOfBoundsException e){
			System.out.println("retirer les espaces en trop");
			
		} catch (InvalidCharactereException e) {
			System.out.println("les characteres entrer ne sont pas conforme");
			e.printStackTrace();
		}
		
		return newH;
	}
	
	
	public static boolean verifie(String mot) throws InvalidCharactereException{
		for(int i = 0; i < mot.length();i++){
			if(mot.charAt(i)<0 || mot.charAt(i)> 128)
				throw new InvalidCharactereException();
			}
		return true;
		
	}
	
	
	
	public static boolean recherche (HybridesTries h, String mot){
		boolean existe = true;
		if( !h.isVide() ){
			char c = h.getRacine().getKey();
			if(c == mot.charAt(0)){
				if( mot.length() > 1) {
					if(h.getEq().isVide())
						return false;
					else
						existe &= true  && recherche(h.getEq(),mot.substring(1));
				}
				if(mot.length() == 1){
					if( h.getRacine().getValue() instanceof ValueVide){
						return false;
					}
					else
						return true;
				}
			}
					
			if(c > mot.charAt(0))
					existe &= recherche(h.getInf(),mot);
			if( c < mot.charAt(0))
					existe &= recherche(h.getSup(),mot);
				
		}
		else
			return false;
	return existe;
	}
	
	public static int comptageMots(HybridesTries h){
		int nbMots = 0;
		if(!h.isVide()){
			if( h.getRacine().getValue() instanceof ValueNonVide){
				nbMots+=1;
			}
			nbMots += comptageMots(h.getInf());
			nbMots += comptageMots(h.getEq());
			nbMots += comptageMots(h.getSup());
			
		}
		return nbMots;
	}
	//liste les mots d enotre arbre par ordre alphabetique 
	public static ArrayList<String> liste(HybridesTries h){
		ArrayList<String> lesmots = new ArrayList<String>();
		listeMots(h,lesmots,"");
		return lesmots;
		
	}
	// utiliser comme fonction intermediaire
	public static void listeMots(HybridesTries h,ArrayList<String> lesMots,
			String mot){
		String chars = mot;
		String chars2;
		if(!h.isVide()){
			
				listeMots(h.getInf(),lesMots,chars);
				chars2 = chars + h.getRacine().getKey();
				if(h.getRacine().getValue() instanceof ValueNonVide){
					lesMots.add(chars2);
				}
				listeMots(h.getEq(),lesMots,chars2);
				//chars+=h.getRacine().getKey();
				/*if(h.getRacine().getValue() instanceof ValueNonVide){
					lesMots.add(chars2);
					}*/
						
				listeMots(h.getSup(), lesMots,chars);
						
			}		
		}
	//nb de pointer vers nill
	public static int nbTrieVide(HybridesTries h){
		int nb = 0;
		if(! h.isVide()){
			nb += nbTrieVide(h.getInf());
			nb += nbTrieVide(h.getEq());
			nb += nbTrieVide(h.getSup());
		}
		else{
		
			nb++;
		}
		return nb;
		
	}
	//compte la hauteur de la racine a la feuille
	public static int hauteur(HybridesTries h){
		int nb = 0 ;
		if(!h.isVide()){
			nb +=1+ Math.max( Math.max( hauteur(h.getInf()), hauteur(h.getEq()) ),
					hauteur(h.getSup()) );
		}
		if( h.isVide())
			return -1;
		
		return nb;
	}
	
	public static double profondeurMoyenne(HybridesTries h){
		double profM = ((double) nbNoeud(h))/nbFeuille(h);
		return profM;
	}
	public static int nbNoeud(HybridesTries h){
		int nb = 0;
		if(!h.isVide()){
			nb += nbNoeud(h.getInf());
			nb +=nbNoeud(h.getEq());
			nb += nbNoeud(h.getSup());
			nb +=1;
		}
			
		if(h.isVide()){
			return 0;
		}
	return nb;
		
	}	
	
	
	public static int nbFeuille(HybridesTries h){
		int nb = 0;
		if(h.isVide())
			return 0;
		if(h.getInf().isVide() && h.getEq().isVide() && h.getSup().isVide())
			return 1;
		
		nb += nbFeuille(h.getInf());
		nb += nbFeuille(h.getEq());
		nb += nbFeuille(h.getSup());
		return nb;
	}
	
	public static int prefixe(HybridesTries h , String mot){
		int nb = 0;
		char p = mot.charAt(0);
		if(! h.isVide()){
			if(h.getRacine().getKey() == p){
				if(mot.length() == 1){
					nb = comptageMots(h.getEq());
					// teste si mot est un mot dans h
					if(h.getRacine().getValue() instanceof ValueNonVide){
						 nb+=1;
					}
					return nb;
				}
				else{
					nb += prefixe(h.getEq(),mot.substring(1));
				}
			}
			if(p < h.getRacine().getKey()){
				nb +=prefixe(h.getInf(),mot);
			}
			if(p>h.getRacine().getKey()){
				nb +=prefixe(h.getSup(),mot);
			}
		}
		return nb;		
	}
	//insere l'arbre h dans l'arbre h au bon endroit
	//utilise uniquement pour la fonction supression
	public static HybridesTries inserer(HybridesTries h, HybridesTries g){
		char c = h.getRacine().getKey();
		
		if( g.isVide()){
			return new HybridesTries(h.getInf(),h.getEq(),h.getSup(),h.getRacine());
			}
		
		if( c < g.getRacine().getKey()){
			return new HybridesTries(inserer(h,g.getInf()),g.getEq(),
					g.getSup(), g.getRacine());
		}
		if( c > g.getRacine().getKey()){
			return new HybridesTries(g.getInf(),g.getEq(),
					inserer(h,g.getSup()), g.getRacine());
		}
		return new HybridesTries(g.getInf(), inserer(h.getEq(),g.getEq()),
				g.getSup(),g.getRacine() );
	}
	
	public static HybridesTries suprimer(HybridesTries h, String mot,int taille){
		char p = mot.charAt(0);
		if(! h.isVide()){
			if(h.getRacine().getKey() < p){
				HybridesTries g = new HybridesTries(h.getInf(),h.getEq(),
						suprimer(h.getSup(),mot,taille),h.getRacine());
				
					return g;
				}
				
			
			if(h.getRacine().getKey() > p){
				return new HybridesTries(suprimer(h.getInf(),mot,taille), h.getEq(), 
						h.getSup(), h.getRacine());
			}
			
			if(h.getRacine().getKey() == p){
				if(mot.length() == 1){
					if(h.troisFilsVide()){
						return new HybridesTries();
						
					}
					if(!h.getEq().isVide()){
						Value vide = new ValueVide();
						//h.getRacine().setValue(vide);
						Node racine = new Node(h.getRacine().getKey(), vide);
						return new HybridesTries(h.getInf(),h.getEq(),
								h.getSup(),racine);
					}
					if(h.getSup().isVide()){
						/*return new HybridesTries(
								h.getInf().getInf(),h.getInf().getEq(),
								h.getInf().getSup(),h.getInf().getRacine());*/
						return h.getInf();
					}
					HybridesTries nv = inserer(h.getInf(),h.getSup().getInf());
					return new HybridesTries(nv,
							h.getSup().getEq() ,
							h.getSup().getSup(),
							h.getSup().getRacine());
				}
			
				HybridesTries g = new HybridesTries(h.getInf(),
						suprimer(h.getEq(),mot.substring(1),taille),
						h.getSup(),h.getRacine());
				if(g.getRacine().getValue() instanceof ValueNonVide){
					return g;
				}
				if(g.troisFilsVide() || g.isVide()){
					return new HybridesTries();
				}
				if(g.getEq().isVide()){
					if(g.getSup().isVide()){
						return g.getInf();
					}
					HybridesTries nv = inserer(g.getInf(),g.getSup().getInf());
					return new HybridesTries(nv,
							g.getSup().getEq(),
							g.getSup().getSup(),
							g.getSup().getRacine());
				}
				return new HybridesTries(g.getInf(),g.getEq(),g.getSup(),
						g.getRacine());
				
			}
		}
		return new HybridesTries();
		
	}
	
	public static HybridesTries supression(HybridesTries h, String mot) {
		if(recherche(h, mot) ==  false){
			System.out.println("le mot "+mot+" n'existe pas dans le dico");
			//return new HybridesTries();
			return h;
			}
		return suprimer(h,mot, mot.length());
	}
	//supprime un mot puis reequilibre l'arbre
	public static HybridesTries supressionEqulibrage(HybridesTries h, String mot){
		HybridesTries a = supression(h,mot);
		return equilibrage(a);
	}
	
	public static HybridesTries rotationDroite(HybridesTries a){
		HybridesTries filsDroit = new HybridesTries(a.getInf().getSup(),
				a.getEq(),a.getSup(), a.getRacine());
		HybridesTries filsGauche = a.getInf().getInf();
		HybridesTries filsEq = a.getInf().getEq();
		HybridesTries newArbre = new HybridesTries(filsGauche,
				filsEq,filsDroit,a.getInf().getRacine());
		return newArbre;
	}
	
	public static HybridesTries rotationGauche(HybridesTries a){
		HybridesTries filsGauche = new HybridesTries(a.getInf()
				, a.getEq(), a.getSup().getInf(), a.getRacine());
		HybridesTries filsEq = a.getSup().getEq();
		HybridesTries filsDroit = a.getSup().getSup();
		HybridesTries newArbre =
				new HybridesTries(filsGauche, filsEq, filsDroit, a.getSup().getRacine());
		return newArbre;
		
	}
	//calcule la pseudo hauteur de l'arbre 
	public static int hauteurEqu(HybridesTries  h, boolean b ){
		int nb = 0;
		if(h.isVide())
			return 0;
		if( b == false){
			
			nb = 1 + Math.max(Math.max(hauteurEqu(h.getInf(),false),
					hauteurEqu(h.getSup(),false)), 
					hauteurEqu(h.getEq(),true));
			return nb;
		}
		else {
			nb = Math.max(Math.max(hauteurEqu(h.getInf(),false),
					hauteurEqu(h.getSup(),false)), 
					hauteurEqu(h.getEq(),true));
			return nb;
			
		}
		
	}
	public static int hauteurEquilibrage(HybridesTries h){
		return hauteurEqu(h,false);
	}
	
	//calcule la difrerence de hauteur entre le fils gauche et le fils droit
	public static int  delta(HybridesTries a){
		return hauteurEquilibrage(a.getSup())-hauteurEquilibrage(a.getInf());
		
	}
	
	public static HybridesTries equilibrage(HybridesTries h){
		if( !h.isVide()){
			HybridesTries inf = equilibrage(h.getInf());
			HybridesTries eq = equilibrage(h.getEq());
			HybridesTries sup = equilibrage(h.getSup());
			// si arbre est deseqilibre
			if(delta(h) !=  0){
				// si l'arbre gauche est plus lourd que le droit
				if( delta(h) < -1 ){
					if( !inf.isVide() && delta(inf)< 0  ){
						HybridesTries rd = rotationDroite(h);
						return rd;
					}
					else {
						HybridesTries rgd = rotationGauche(inf);
						HybridesTries newArbre = new HybridesTries(rgd, h.getEq()
								, h.getSup(), h.getRacine());
						 rgd = rotationDroite(newArbre);
						 return rgd;
					}
				}
				else {
					if(delta(h) > 1){
						if(!sup.isVide() && delta(sup) > 0){
							HybridesTries rg = rotationGauche(h);
							return rg;
						}
						else {
							HybridesTries rdg = rotationDroite(sup);
							HybridesTries newArbre = new HybridesTries(h.getInf(),
									h.getEq(),rdg , h.getRacine());
							rdg = rotationGauche(newArbre);
							return rdg;
						}
					}
				}
					
			}
			return h;
		}
		return h ;
		
	}
}
//https://algo.infoprepa.epita.fr//index.php/Epita:Algo:Cours:Info-Sup:Arbres_de_recherche
	
