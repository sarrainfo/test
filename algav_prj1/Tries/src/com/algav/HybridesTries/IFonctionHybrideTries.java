package com.algav.HybridesTries;

import java.util.ArrayList;

public interface IFonctionHybrideTries {
	public  HybridesTries ajoutString(String key,HybridesTries a);
	public boolean recherche (HybridesTries h, String mot);
	public int comptageMots(HybridesTries h);
	public ArrayList<String> liste(HybridesTries h);
	public int nbTrieVide(HybridesTries h);
	public int hauteur(HybridesTries h);
	public double profondeurMoyenne(HybridesTries h);
	public int prefixe(HybridesTries h , String mot);
	public HybridesTries supression(HybridesTries h, String mot);
	public HybridesTries equilibrage(HybridesTries h);
	
}
