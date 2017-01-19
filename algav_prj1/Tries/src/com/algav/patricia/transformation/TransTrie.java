package com.algav.patricia.transformation;

import java.util.LinkedList;

public class TransTrie {
	private TransNode root;
	private TransTrie eq;
	private TransTrie inf;
	private TransTrie sup;
	
	public TransTrie(TransTrie inf, TransTrie eq, TransTrie sup,
			TransNode root){
		this.eq = eq;
		this.inf = inf;
		this.sup = sup;
		this.root = root;
	}
	
	public TransTrie(TransNode root){
		this.root = root;
		this.eq = null;
		this.inf = null;
		this.sup = null;

	}
	
	public TransNode getRoot() {
		return root;
	}
	public TransTrie getEq() {
		return eq;
	}
	public TransTrie getInf() {
		return inf;
	}
	public TransTrie getSup() {
		return sup;
	}

	public void setRoot(TransNode root) {
		this.root = root;
	}

	public void setEq(TransTrie eq) {
		this.eq = eq;
	}

	public void setInf(TransTrie inf) {
		this.inf = inf;
	}

	public void setSup(TransTrie sup) {
		this.sup = sup;
	}
	
	
	
	
}
