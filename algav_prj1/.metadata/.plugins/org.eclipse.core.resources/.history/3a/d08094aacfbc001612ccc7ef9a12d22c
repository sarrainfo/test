package com.algav.patricia;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import com.algav.patricia.exceptions.OutOfCharacterSetException;
import com.algav.patricia.exceptions.PATStringException;
import com.algav.patricia.exceptions.PatriciaException;

import static com.algav.patricia.string.StringManipulation.*;

public class PatriciaTrie implements IPatriciaTrie{

	protected IPATCase[] patTrie;
	protected static final int SIZE = 128;
	protected int nbCases = 0;
	//creer un noeud vide et y ajouter un seul mot
	public PatriciaTrie(String mot){
		this.patTrie = new IPATCase[SIZE];
		if (mot.length() == 0 || mot == null){
			//return;
			throw new PatriciaException("patricia trie must contain "
					+ "at least one word");
		}
		if (containsEpsilon(mot)){
			this.patTrie[asciiFirst(mot)]
					= new PATCase (mot);
			this.nbCases++;
		}else{
			stringValid(mot);
			this.patTrie[asciiFirst(mot)]
					= new PATCase (concatEpsilon(mot));
			this.nbCases++;
		}
	}
	
	//can only be used internally
	public PatriciaTrie(){
		this.patTrie = new IPATCase[SIZE];
	}
	
	public int getSize(){
		return SIZE;
	}
	public String getWord (int i){
		if (this.patTrie[i] == null)
			return null;
		return this.patTrie[i].getWord();
	}
	
	public void setWord(int i, String word){
		if (this.patTrie[i] == null){
			this.patTrie[i] = new PATCase(word);
			this.nbCases++;
		}else{
			this.patTrie[i].setWord(word);
		}
	}
	
	public IPatriciaTrie getSon (int i){
		//if son == null create case
		if (this.patTrie[i] == null)
			return null;
		
		return this.patTrie[i].getSon();
	}
	
	public void setSon (int i, IPatriciaTrie node){
		if (this.patTrie[i] == null){
			throw new PatriciaException("Set son to non existing index");
		}
		this.patTrie[i].setSon(node);
	}
	/*
	//set ith son in this node with ith son in node
	//if ith son in node is null do not set
	public void setSonGetSonI(int i, IPatriciaTrie node){
		if (this.patTrie[i] == null){

	}
	*/
	public IPATCase getCase (int i){
		return this.patTrie[i];
	}
	
	public void setCase (int i, String s){
		this.patTrie[i] = new PATCase(s);
		this.nbCases++;
	}
	
	public void deleteCase (int i){
		this.nbCases--;
		this.patTrie[i] = null;
	}
	
	public int getNbCases(){
		return this.nbCases;
	}
	
	public boolean isLeaf(){
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i) != null && this.getSon(i) != null)
				return false;
		}
		return true;
	}
	/*
	public boolean isEmpty(){
		for (int i=0; i < SIZE; ++i){
			if (this.getCase(i)!=null)
				return false;
		}
		return true;
	}
	*/
	public boolean isEmpty(){
		return this.nbCases==0;
	}
	
	/////***************************patricia methods*********************************///
	//ajout d'un mot dans un patricia trie
	//note: le patricia trie ne peut pas etre vide
	public void ajout(String word){
		if (word == null || word.length() == 0){
			throw new PatriciaException("specify word to insert");
		}
		sysAjout(concatEpsilon(stringValid(word)));
	}
	
	public void sysAjout (String word){ 
		
		//cas1
		/*
		if (word == null || word.length() == 0 ){
			System.out.println("cas1");
			return;
		}
		*/
		//cas2
		if (this.getCase(asciiFirst(word)) == null){
			//System.out.println("cas2");
			this.setCase(asciiFirst(word), word);
			return;
		}
		
		//cas3
		//mot deja existant
		if (word.equals((String) this.getWord(asciiFirst(word)))){
			//System.out.println("cas3");
			return;
		}
		//cas4
		//mot dans case prefixe de mot ajouter
		if (word.startsWith(this.getWord(asciiFirst(word)))){
			//System.out.println("cas4");
			String rest = rest(word, 
						pref(word,this.getWord(asciiFirst(word))));
			
			//ajouter reste du mot dans fils
			(this.getSon(asciiFirst(word))).sysAjout(rest);
		}
		
		//cas5
		else{
			//System.out.println("cas5");
			String prefixe = pref(word, this.getWord(asciiFirst(word)));
			String restWordInput = rest(word, prefixe);
			String restWordInDic = rest(this.getWord(asciiFirst(word)),prefixe);
		
			IPatriciaTrie newNode = new PatriciaTrie();
			newNode.setWord(asciiFirst(restWordInDic), restWordInDic);
			//breaking up struct to accomodate new word
			this.setWord(asciiFirst(prefixe), prefixe);
			//newNode.sysAjout(restWordInDic);
			newNode.setWord(asciiFirst(restWordInput), restWordInput);
			newNode.setSon(asciiFirst(restWordInDic), this.getSon(asciiFirst(prefixe)));
			this.setSon(asciiFirst(prefixe), newNode);
		
			
		}
			
	}

	public LinkedList<String> listeMots(){
		LinkedList<String> liste = new LinkedList<String>();
		return sysListeMots(liste, "");
	}
	
	public LinkedList<String> sysListeMots(LinkedList<String> liste, String prefixe){
		for (int i = 0; i < SIZE; ++i){
			String newPrefixe = "";
			newPrefixe = newPrefixe.concat(prefixe);
			if (this.getCase(i) != null){
				if (containsEpsilon(this.getWord(i))){
					String truncEps = truncLast(this.getWord(i));
					newPrefixe = newPrefixe.concat(truncEps);
					liste.add(newPrefixe);
				}else{
					newPrefixe = newPrefixe.concat(this.getWord(i));
				}
				if (this.getSon(i) != null)
					this.getSon(i).sysListeMots(liste, newPrefixe);
			}
		}
		return liste;	
	}
	
	public int comptageMots(){
		int nb = 0;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i) != null){
			if (containsEpsilon(this.getWord(i)))
				nb++;
			
			if (this.getSon(i) != null)
				nb = nb + this.getSon(i).comptageMots();
			}
		}
		return nb;
	}
	
	public int comptageNil(){
		int nb = 0;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i) == null)
				++nb;
			else{
				if (this.getSon(i) != null)
					nb = nb + this.getSon(i).comptageNil();
			}	
		}
		return nb;
	}
	
	public boolean recherche(String word){
		if (word == null || word.length() == 0){
			throw new PatriciaException("enter word to search for");
		}
		return (sysRecherche(concatEpsilon(word)));
	}
	
	public boolean sysRecherche(String word){
		
		int i = asciiFirst(word);
		
		//cas1
		if (this.getCase(i) == null){
			//System.out.println("cas1");
			return false;
		}
	
		//cas2
		if (this.getWord(i).equals(word)){
			//System.out.println("cas2");
			return true;
		}
		
		//cas3
		//contenu de la case i est pas un prefixe du mot
		if (!(pref(this.getWord(i),word).equals(this.getWord(i)))){
			//System.out.println("cas3");
			return false;
		}
			
		//cas4
		if (this.getSon(i) == null){
			//System.out.println("cas4");
			return false;
		}
		//cas5
		else{
			//System.out.println("cas5");
			return this.getSon(i).sysRecherche(rest(word, this.getWord(i)));
		}
		
	}
	
	public int hauteur(){
		int maxh = 0;
		int h;
		for (int i = 0; i < SIZE; ++i){
			h = 0;
			if (this.getCase(i) != null && this.getSon(i)!=null)
				h = 1 + this.getSon(i).hauteur();
			
			maxh = Math.max(maxh, h);
		}
		return maxh;
	}
	
	public int prefixe(String strPrefixe){
		//cas1
		if (strPrefixe.length() == 0){
			//System.out.println("cas1");
			return this.comptageMots();
		}
		int i = asciiFirst(strPrefixe);
		//cas2
		//can we get empty node but allocated one i.e node!=null???
		if (this.getCase(i) == null){
			//System.out.println("cas2");
			return 0;
		}
		//cas3
		if (pref(strPrefixe,this.getWord(i)).equals(this.getWord(i))){
			//System.out.println("cas3");
			return this.getSon(i).prefixe(rest(strPrefixe,this.getWord(i)));
		}
		//cas4
		if (pref(strPrefixe, this.getWord(i)).equals(strPrefixe)){
			//System.out.println("cas4");
			if (this.getSon(i) != null)
				return this.getSon(i).comptageMots();
			else {
				if (!containsEpsilon(this.getWord(i)))
					throw new PatriciaException("word in trie must have ended in epsilon");
				return 1;
			}
		}
		//cas5
		//System.out.println("cas5");
		return 0;
	}
	
	public double profondeurMoyenne(){
		return (profondeurTotal()/nbFeuilles());
	}
	
	public int profondeurTotal(){
		int ttl = 0;
		if (this.isLeaf())
			return 0;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i)!= null && this.getSon(i)!=null){
				ttl = ttl + 1 + this.getSon(i).profondeurTotal();
			}
		}
		return ttl;
	}
	
	public int nbFeuilles(){
		int ttl = 0;
		if (this.isLeaf())
			return 1;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i)!= null && this.getSon(i)!=null){
				ttl += this.getSon(i).nbFeuilles();
			}
		}
		return ttl;
	}
	
/*	public Integer profondeurMoyenne(){
		ArrayList<Integer> l = this.sysProfondeurTotal(0);
		//l[0]: profondeur total des feuilles
		//l[1]: nb feuilles
		return l.get(0)/l.get(1);
	}
	
	public ArrayList<Integer> sysProfondeurTotal(Integer prof){
		Integer profondeurTotal = 0;
		Integer nbFeuilles = 0;
		
		if (this.isLeaf()){
			++nbFeuilles;
			profondeurTotal = prof;
		}
		
		else{
			for (int i = 0; i <SIZE ; ++i){
				if (this.getCase(i)!= null && this.getSon(i)!=null){
					ArrayList<Integer> result= this.getSon(i).sysProfondeurTotal(++prof);
					profondeurTotal = Integer.sum (profondeurTotal, result.get(0));
					nbFeuilles = Integer.sum(nbFeuilles, result.get(1));
				}
			}
		}
		
		ArrayList<Integer> l = new ArrayList<Integer>(2);
		l.add(profondeurTotal);
		l.add(nbFeuilles);
		return l;
	}*/
	public void suppression(String mot){
		/*
		if (this.comptageMots() == 1 && this.listeMots().get(0).equals(mot)){
			System.err.println("WARNING: cannot delete last word in dictionary");
			return;
		}
		*/
		 sysSuppression(concatEpsilon(mot));
	}
	public void sysSuppression(String mot){
		int i = asciiFirst(mot);
		//cas1 - mot pas dans dic
		if (this.getCase(i) == null)
			return;
		//cas2 - mot a supprimer == mot dans case
		else if (this.getWord(i).equals(mot)){
			if (this.getSon(i)!=null)
				throw new PatriciaException("suppression:epsilon cannot be in the middle of word");
			this.deleteCase(i);
			return;
		//cas3 - mot dans case est un prefixe de mot a supprimer
		}else if(isPref(this.getWord(i),mot)){
			if (this.getSon(i) == null)
				throw new PatriciaException("suppression:word has to end in epsilon");
			this.getSon(i).sysSuppression(rest(mot,this.getWord(i)));
			if (this.getSon(i).getNbCases() == 1){
				int k = 0;
				while(this.getSon(i).getCase(k) == null)
					++k;
				this.setWord(i, this.getWord(i).concat(this.getSon(i).getWord(k)));
				this.setSon(i, this.getSon(i).getSon(k));
				return;
			}
				
		}
	}
	/*public void sysSuppression(String mot){
		int i = asciiFirst(mot);
		//cas1
		if (this.getCase(i) == null)
			return;
		
		//cas2
		if (this.getWord(i).equals(mot)){
			if (this.getSon(i)!=null)
				throw new PatriciaException("suppression:epsilon cannot be in the middle of word");
			this.deleteCase(i);
		}
		
		//cas3
		else if (isPref(this.getWord(asciiFirst(mot)),mot)){
			if (this.getSon(i) == null)
				throw new PatriciaException("suppression:word has to end in epsilon");
			this.getSon(i).sysSuppression(rest(mot,this.getWord(i)));
		}
		
		//cas4
		else if (!isPref(this.getWord(asciiFirst(mot)),mot)){
			return;
		}
		
		//suite cas2 (action a faire en retour de fonction cas2)
		if (this.getCase(i)!=null && this.getSon(i).isEmpty()){
			this.setSon(i, null);
		}
	}*/
	
	
	public IPatriciaTrie fusion(IPatriciaTrie p){
		/*
		if (this == null && p == null)
			return null;
		if (this==null && p!=null)
			return p;
		if (this!=null && p==null)
			return this;
			*/
		IPatriciaTrie result = new PatriciaTrie();
		for (int i = 0; i < SIZE; ++i){
			//cas0
			if (p.getCase(i) == null && this.getCase(i) == null){
				//System.out.println("cas0");
				result.deleteCase(i);
			}
			//cas1 - une des cases vide
			else if (p.getCase(i) == null && this.getCase(i) != null){
				//System.out.println("cas1.1");
				result.setWord(i, this.getWord(i));
				result.setSon(i, this.getSon(i));
			}else if(p.getCase(i)!=null && this.getCase(i)==null){
				//System.out.println("cas1.2");
				result.setWord(i, p.getWord(i));
				result.setSon(i, p.getSon(i));
			}
			
			//cas2 - les deux cases contiennent le meme mot
			else if(p.getWord(i).equals(this.getWord(i))){
				//System.out.println ("cas2");
				result.setWord(i, this.getWord(i));
				if (this.getSon(i) == null)
					result.setSon(i,p.getSon(i));
				else if (p.getSon(i) == null)
					result.setSon(i, this.getSon(i));
				else
					result.setSon(i, this.getSon(i).fusion(p.getSon(i)));
				
			}

			//cas3- l'un contient le prefixe de l'autre
			else if (isPref(this.getWord(i), p.getWord(i))){
				//System.out.println("cas3.1");
				if (this.getSon(i) == null){
					throw new PatriciaException("word must end in eps");
				}
				IPatriciaTrie temp = new PatriciaTrie();
				int k = asciiFirst(rest(p.getWord(i),this.getWord(i)));
				temp.setWord(k, rest(p.getWord(i),this.getWord(i)));
				temp.setSon(k, p.getSon(i));
				
				result.setWord(i, this.getWord(i));
				result.setSon(i, this.getSon(i).fusion(temp));

				
			}else if (isPref(p.getWord(i), this.getWord(i))){
				//System.out.println("cas3.2");
				if (p.getSon(i) == null){
					throw new PatriciaException("word must end in eps");
				}
				
				IPatriciaTrie temp = new PatriciaTrie();
				int j = asciiFirst(rest(this.getWord(i),p.getWord(i)));
				temp.setWord(j, rest(this.getWord(i),p.getWord(i)));
				temp.setSon(j, this.getSon(i));

				result.setWord(i, p.getWord(i));
				result.setSon(i, p.getSon(i).fusion(temp));

				//result.setSon(i, temp.fusion(p.getSon(i)));

			}
			//cas4- les deux mots sont plus long que le prefixe
			else if (p.getWord(i).length() > pref(p.getWord(i),this.getWord(i)).length() &&
					this.getWord(i).length() > pref(p.getWord(i),this.getWord(i)).length()){
				//System.out.println("cas4");
				
				String prefixe = pref(this.getWord(i),p.getWord(i));

				int j = asciiFirst(rest(this.getWord(i),prefixe));
				int k = asciiFirst(rest(p.getWord(i),prefixe));
				
				result.setWord(i, prefixe);
				
				IPatriciaTrie son = new PatriciaTrie();
				
				son.setWord(j,rest(this.getWord(i),prefixe));
				son.setSon(j, this.getSon(i));
				
				son.setWord(k, rest(p.getWord(i),prefixe));
				son.setSon(k, p.getSon(i));

				result.setSon(i, son);

			}
			else{
				throw new PatriciaException("non existant case");			}
		}
		return result;
	}
	
	public IPatriciaTrie clone(){
		IPatriciaTrie result = new PatriciaTrie();
		for (int i = 0 ; i < SIZE; ++i){
			if (this.getCase(i) != null){
			result.setWord(i, this.getWord(i));
				if (this.getSon(i)!=null)
					result.setSon(i, this.getSon(i));
			}
		}
		return result;
	}
	public static void main(String[] s){
		//WARNING empty dictionary not supported
		IPatriciaTrie dic = new PatriciaTrie("atb");
		
		dic.ajout("tgag");
		dic.ajout("tgac");
		dic.ajout("cgga");
		dic.ajout("tac");
		dic.ajout("tacg");
		dic.ajout("tacgh");
		dic.ajout("tacgi");
		dic.ajout("tacgil");
		dic.ajout("tacb");
		dic.ajout("cggb");
		
		System.out.println("profondeur moyenne: " + dic.profondeurMoyenne());
		LinkedList<String> l = dic.listeMots();
		IPatriciaTrie dicClone = dic.clone();
		System.out.println(dic.listeMots());
		System.out.println(dicClone.listeMots());
		//IPatriciaTrie dic2 = new PatriciaTrie("he2");
		//String input = 
		//"A quel genial professeur de dactylographie";
		//"sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
		/*System.out.println("nb mots: " + dic.comptageMots());
		System.out.println("nb nil: " + dic.comptageNil());
		System.out.println(l.toString());
		System.out.println("starting search.................");
		System.out.println("ended");
		System.out.println("hauteur: " + dic.hauteur());
		System.out.println("starting prefixe.................");
		System.out.println("prefixe de tac: " + dic.prefixe("tac"));
		System.out.println("profondeur moyenne: " + dic.getSon((int)'t').getSon((int)'g').profondeurMoyenne());
		//System.out.println((dic.getSon((int)'t')).isLeaf());
		System.out.println("starting deletion.......................");
		LinkedList<String> l2 = dic.listeMots();
		System.out.println(l2.toString());*/

/*
		IPatriciaTrie dic3 = new PatriciaTrie("at");
		dic3.ajout("tac");
		dic3.ajout("tacgil");
		dic3.ajout("yyy");
		dic3.ajout("tacmlk");
		dic3.ajout("atc");
		dic3.ajout("yyy");

		LinkedList<String> l3 = dic3.listeMots();

		System.out.println("dic1: " + l.toString());

		System.out.println("dic3: " + l3.toString());
		System.out.println("\n\n\nstarting fusion....................");

		IPatriciaTrie dic4 = dic.fusion(dic3);
		LinkedList<String> l4 = dic4.listeMots();
		System.out.println("dic4: " + l4.toString());*/


	}
	
	
	
}
