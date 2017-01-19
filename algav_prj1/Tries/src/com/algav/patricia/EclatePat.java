package com.algav.patricia;

import java.util.LinkedList;

import com.algav.HybridesTries.HybridesTries;

public class EclatePat extends PatriciaTrie {
	
	private IPATCase[] eclatPatTrie;
	private int eclatPatSize;
	public EclatePat(int i){
		this.eclatPatTrie = new IPATCase[i];
		this.eclatPatSize = i;
	}
	
	public EclatePat compresserPatricia (PatriciaTrie p){
		LinkedList<IPATCase>compressedNode = new LinkedList<IPATCase>();
		for (int i = 0; i < p.SIZE; ++i){
			if (p.getCase(i) != null){
				compressedNode.add(p.getCase(i));
				compresserPatricia((PatriciaTrie)compressedNode.get(i).getSon());
			}
		}
		return null;
	}
	public EclatePat(PatriciaTrie p){
		this.eclatPatTrie = p.patTrie;
		this.eclatPatSize = p.patTrie.length;
	}
	
	public static HybridesTries patToHyb(PatriciaTrie p){
		EclatePat ep = new EclatePat(p);
		return null;
	}
	
	public static EclatePat patToHybPhase1(EclatePat ep){
		return null;
	}
	
	public static HybridesTries eclatPatToHyb(EclatePat ep){
		return null;
	}
}
