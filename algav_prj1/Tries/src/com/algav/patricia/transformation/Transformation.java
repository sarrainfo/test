package com.algav.patricia.transformation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.HybridesTries.Node;
import com.algav.HybridesTries.ValueNonVide;
import com.algav.HybridesTries.ValueVide;
import com.algav.patricia.IPatriciaTrie;
import com.algav.patricia.PatriciaTrie;
import com.algav.patricia.TestPatricia;
import com.algav.patricia.exceptions.PatriciaException;

import static com.algav.patricia.string.StringManipulation.*;

public class Transformation {

	public static LinkedList<TransNode> compress (IPatriciaTrie p){
		//remove all empty nodes from trie
		if (p == null)
			return null;
		
		LinkedList<TransNode> result = new LinkedList<TransNode>();
		
		for(int i = 0; i < p.getSize(); ++i){
			if (p.getCase(i) != null){
				result.add(new TransNode(p.getWord(i), compress(p.getSon(i))));
			}
		}
		return result;
	}
	
	public static LinkedList<TransNode> removeEpsNodes(LinkedList<TransNode> cp){
		if (cp == null)
			return null;
		for (int i = 0; i < cp.size(); ++i){
			if (cp.get(i).getSon() != null && cp.get(i).getSon().get(0).getWord().equals(String.valueOf((char)(0)))){
				cp.get(i).setWord(concatEpsilon(cp.get(i).getWord()));
				cp.get(i).getSon().remove(0);
			}
			cp.get(i).setSon(removeEpsNodes(cp.get(i).getSon()));
		}
		return cp;
	}
	
	public static int middle(int len){
		return (int)Math.floor((len - 1)/2);
	}
	
	public static TransTrie eclater(List<TransNode> cp){
		if (cp == null)
			return null;
		
		TransTrie result = null;

		if (cp.size() == 1){
			result = new TransTrie(cp.get(0));
			result.setEq(eclater(cp.get(0).getSon()));
			
		}else if (cp.size() == 2){	
			result = new TransTrie(cp.get(0));
			result.setEq(eclater(cp.get(0).getSon()));
			result.setSup(new TransTrie(cp.get(1)));
			result.getSup().setEq(eclater(cp.get(1).getSon()));
		}else{
			result = new TransTrie(cp.get(middle(cp.size())));
			result.setEq(eclater(cp.get(middle(cp.size())).getSon()));
			result.setInf(eclater(cp.subList(0, middle(cp.size()))));
			result.setSup(eclater(cp.subList(middle(cp.size()) + 1, cp.size())));
		}
		return result;
	}
	/*
	public static HybridesTries[] expand (String s){
		//le premier char de peut pas etre epsilon
		if (s.charAt(0)==((char)0))
			throw new PatriciaException("first character cannot be epsilon");
		
		//HybridesTries first = null;
		//HybridesTries iter = null;
		
		HybridesTries first = new HybridesTries(new HybridesTries(),new HybridesTries(),new HybridesTries(),new Node(s.charAt(0),new ValueVide()));
		HybridesTries iter = first;

		for (int i = 1; i < s.length(); ++i){
			if (i<s.length() - 1 && s.charAt(i+1)==((char)0)){
				//ValueNonVide: epsilon represente 1 chiffre dans le hybride trie
				iter.setEq(new HybridesTries(new HybridesTries(),new HybridesTries(),new HybridesTries(),new Node(s.charAt(i),new ValueNonVide())));
			}else{
				//ValueVide: il y a pas de valeur (chiffre) dans le noeud du hybride trie
				iter.setEq(new HybridesTries(new HybridesTries(),new HybridesTries(),new HybridesTries(),new Node(s.charAt(i),new ValueVide())));
			}
			
			iter = iter.getEq();	
		}
		HybridesTries result[] = new HybridesTries[2];
		result[0] = first;
		result[1] = iter;
		//System.out.println("String: " + s + "list: " + Ajout.liste(first));
		return result;
	}
	*/
	
	
	public static HybridesTries expand(String s){
		return ajoutString2(s, new HybridesTries());
	}
	
	public static HybridesTries ajoutString2(String key,HybridesTries a){
		
			HybridesTries infVide = new HybridesTries();
			HybridesTries supVide = new HybridesTries();
			HybridesTries eqVide = new HybridesTries();
			if( key.length() == 2 && key.charAt(1)==(char)0){
				Node n = new Node(key.charAt(0),new ValueNonVide());
				HybridesTries h = new HybridesTries(infVide, eqVide, supVide,n);
				return h;
			}
			if( key.length() == 1){
				Node n = new Node(key.charAt(0),new ValueVide());
				HybridesTries h = new HybridesTries(infVide, eqVide, supVide,n);
				return h;
			}
			else{
				Node n = new Node(key.charAt(0),new ValueVide());
				a.setEq(eqVide);
				return new HybridesTries( infVide,
						ajoutString2(key.substring(1),a.getEq()), supVide,n);
				
			}		
			
		}
	
	
	/*
	public static HybridesTries[] transFinal(TransTrie tt){
		if (tt == null)
			return new HybridesTries();
		
		HybridesTries expanded[] = expand(tt.getRoot().getWord()); 
		
		//type de parcours ne compte pas (inf, pre, suf)
		expanded[0].setInf(transFinal(tt.getInf()));
		//expanded[1].setEq(transFinal(tt.getEq()));
		expanded[0].setSup(transFinal(tt.getSup()));
		
		HybridesTries iter = expanded[0];
		while (!iter.getEq().isVide()){
			iter = iter.getEq();
		}
		iter.setEq(transFinal(tt.getEq()));

		return expanded[0];

	}
	*/
	public static HybridesTries transFinal(TransTrie tt){
		if (tt == null)
			return new HybridesTries();
		HybridesTries expanded = expand(tt.getRoot().getWord()); 
		//type de parcours ne compte pas (inf, pre, suf)
			expanded.setInf(transFinal(tt.getInf()));
			expanded.setSup(transFinal(tt.getSup()));
				
				HybridesTries iter = expanded;
				while (!iter.getEq().isVide()){
					iter = iter.getEq();
				}
				iter.setEq(transFinal(tt.getEq()));

				return expanded;
	}
	
	public static HybridesTries transformationPatToHyb(PatriciaTrie p){
		LinkedList<TransNode> ct = compress(p);
		ct = removeEpsNodes(ct);
		return transFinal(eclater(ct));
	}
	public static void main(String[] args){
		
		TestPatricia t = new TestPatricia("./shakespeare/1henryiv.txt");
		//System.out.println("raw file: " + t.getRawFileList());
	
		//LinkedList<TransNode> ct = compress(t.getPatriciaTrie());
		
		HybridesTries tst = new HybridesTries();
		for (int i = 0; i < t.getInputSize(); ++i)
			tst = Ajout.ajoutString(t.getRawFileList().get(i),tst);
		//ct = removeEpsNodes(ct);
		//mal construit h
		//HybridesTries h = transFinal(eclater(ct));
		HybridesTries h = transformationPatToHyb((PatriciaTrie)t.getPatriciaTrie());
		System.out.println("patricia: " + t.getPatriciaTrie().listeMots());
		System.out.println("hybride: " + Ajout.liste(tst));
		System.out.println("hybride: " + Ajout.liste(h));
		System.out.println("patricia: " + t.getExpectedResult().size());
		System.out.println("hybride: " + Ajout.comptageMots(tst));
		System.out.println("generated hybride: " + Ajout.comptageMots(h));
		ArrayList<String> a = new ArrayList<String>(t.getExpectedResult().size());
		for (int i = 0; i < t.getExpectedResult().size(); ++i){
			a.add(i, t.getExpectedResult().get(i));
		}
		System.out.println("verdict trans: " + a.equals(Ajout.liste(tst)));
		//System.out.println();
		 
		/*
		String s = "abcd";
		s.concat(String.valueOf((char)0));
		HybridesTries h = expand("abcd");
		System.out.println("hybride 0");
		System.out.println(HybridesTries.AfficheHybride(h));
		System.out.println("hybride 1");
		System.out.println(HybridesTries.AfficheHybride(h));

		System.out.println(Ajout.liste(h));
		System.out.println(Ajout.liste(h));
		*/
	}
}
