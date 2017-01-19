package com.algav.patricia;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
public class TestPatricia {
	
	public TestPatricia(String filename){
		this.filename = filename;
		InputStream oeuvre = null;
		try{
			oeuvre = new FileInputStream(filename);
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}	
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		String input = null;
		while(scanner.hasNext()){
			input = scanner.next();
			rawfileList.add(input);
			rawfileList2.add(input);

			if (!(expectedResult.contains(input))){
				expectedResult.add(input);
				noDoubles.add(input);
			}
		}
		expectedResult.sort(null);
		
		this.inputSize = rawfileList.size();
		for (int i = 0; i < getInputSize(); ++i){
			this.patriciaTrie.ajout(this.getRawFileList().get(i));

		}
	}
	public int getInputSize(){
		return inputSize;
	}
	public LinkedList<String> getRawFileList() {
		return rawfileList;
	}
	public ArrayList<String> getRawFileList2() {
		return rawfileList2;
	}


	private String filename;
	private ArrayList<String> rawfileList2 = new ArrayList<String>();

	private LinkedList<String> rawfileList = new LinkedList<String>();
	private LinkedList<String> noDoubles = new LinkedList<String>();//no doubles
	private LinkedList<String> expectedResult = new LinkedList<String>(); //sorted and no doubles
	private int inputSize;
	private IPatriciaTrie patriciaTrie= new PatriciaTrie();
	
	public LinkedList<String> getExpectedResult() {
		return expectedResult;
	}
	public LinkedList<String> getNoDoubles() {
		return noDoubles;
	}
	public IPatriciaTrie getPatriciaTrie(){
		return patriciaTrie;
	}
	
	public boolean testConstruction(){
		IPatriciaTrie p = new PatriciaTrie();
		for (int i = 0; i < getInputSize(); ++i){
			p.ajout(this.getRawFileList().get(i));

		}
		return p.listeMots().equals(this.getExpectedResult());
	}
	
	public boolean testSuppression(){
		IPatriciaTrie p = new PatriciaTrie();
		
		LinkedList<String> noDbl = (LinkedList<String>)this.getNoDoubles().clone();
		LinkedList<String> sorted = null;

		for (int i = 0; i < this.getRawFileList().size(); ++i){
			p.ajout(this.getRawFileList().get(i));

		}
		
		String nonExistant = noDbl.get(0);
		
		for (int i = 0; i < this.getNoDoubles().size() - 1; ++i){
			//System.out.println();
			String beingDeleted = noDbl.get(0);
			LinkedList<String> initList = (LinkedList<String>)noDbl.clone();
			initList.sort(null);
			p.suppression(noDbl.get(0));
			noDbl.remove(0);
			sorted = (LinkedList<String>)noDbl.clone();
			sorted.sort(null);
			if (!(p.listeMots().equals(sorted))){
				System.out.println("initial list:" + initList);
				System.out.println("being deleted:" + beingDeleted);
				System.out.println("expected list: " + (sorted).toString());
				System.out.println("patricia list: " + (p.listeMots()).toString());
				return false;
			}
		}
		//System.out.println("expected list: " + (sorted).toString());
		//System.out.println("patricia list: " + (p.listeMots()).toString());
		return true;
	}

	public boolean testFusion() {
		IPatriciaTrie p1 = new PatriciaTrie();
		IPatriciaTrie p2 = new PatriciaTrie();
		IPatriciaTrie control = new PatriciaTrie();
		
		int index;
		if(this.inputSize % 2 == 0)
			index = this.inputSize/2;
		else
			index = (this.inputSize - 1)/2;
		
		for (int i = 0; i < index; ++i){
			p1.ajout(this.getRawFileList().get(i));
			control.ajout(this.getRawFileList().get(i));
		}
		for (int i = index; i < this.inputSize; ++i){
			p2.ajout(this.getRawFileList().get(i));
			control.ajout(this.getRawFileList().get(i));
		}
		
		if (!this.expectedResult.equals(control.listeMots()))
			System.out.println("WARNING: CONTROL NOT CORRESPONDING");
		
		IPatriciaTrie p = p2.fusion(p1);
		
		if (p.listeMots().equals(this.expectedResult)){
			return true;
		}else{
			System.out.println("fusion   list: " + (p.listeMots()).toString());
			System.out.println("expected list: " + (this.expectedResult).toString());
			return false;
		}	
		
		
	}
	
	
	public static void main (String[] args){
		TestPatricia testeur = new TestPatricia("./shakespeare/cleopatra.txt");
		System.out.println("verdict testConstruction: "+ testeur.testConstruction());
		System.out.println("verdict testSuppression: "+testeur.testSuppression());
		System.out.println("verdict testFusion: " +testeur.testFusion());
		/*
		PatriciaTrie p = new PatriciaTrie();
		for(int j = 0; j < 100000; ++j){
		for (int i = 0; i < 20; ++i){
			testeur.getPatriciaTrie().suppression(testeur.getExpectedResult().get(i));
		}
		}
		System.out.println(p.comptageMots());
		*/
	}
	
		
}
