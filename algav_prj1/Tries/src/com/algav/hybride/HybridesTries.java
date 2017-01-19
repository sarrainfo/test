package com.algav.hybride;

public class HybridesTries {
	private Node racine ;
	private HybridesTries inf ;
	private HybridesTries eq ;
	private HybridesTries sup;
	
	//constructeur pour HybrideTries non vide
	public HybridesTries( HybridesTries inf, HybridesTries eq, 
			HybridesTries sup,Node racine){
				this.inf=inf;
				this.eq=eq;
				this.sup=sup;
				this.racine = racine;
			}
	
	// constructeur pour Hybride Tries vide
	public HybridesTries(){
		this.inf=null;
		this.eq= null;
		this.sup=null;
		racine = new Node('#',new ValueVide());
		
	}
	public boolean troisFilsVide(){
		if(inf.isVide() && eq.isVide() && sup.isVide())
			return true;
		return false;
	}
	
	// teste si l'arbre est vide 
	public boolean isVide(){
		return racine.nodeVide();
	}
	
	public HybridesTries getEq(){
		return eq;
	}
	
	public HybridesTries getSup(){
		return sup;
	}
	
	public Node getRacine(){
		return racine;
	}
	
	public static String AfficheHybride(HybridesTries h ){
		String s = "";
		if (!h.isVide()){
			s= s +" racine: "+ h.getRacine().getKey();
			if( h.getRacine().getValue() instanceof ValueNonVide )
				s+=" "+h.getRacine().getValue().getValeurValue()+"\n";

			if( !h.getInf().isVide()){
		
				s = s + " inf :[" +AfficheHybride(h.getInf())+"] \n";
			}
			else 
				s = s + " inf: vide ";
			if(! h.getEq().isVide()){
				s = s+" eq :[" + AfficheHybride(h.getEq())+"] \n";
			}
			else 
				s+=" eq: vide ";
			if( !h.getSup().isVide()){
				s = s +" sup :[ "+ AfficheHybride(h.getSup())+"] \n";
			}
			else
				s+=" sup: vide ";
		}
		else 
			s = s + " arbre vide ";
		return s;
		
		
		
	}
	
	public HybridesTries getInf(){
		return inf;
	}
	
	public void setEq(HybridesTries eq){
		this.eq = eq;
	}
	

}
