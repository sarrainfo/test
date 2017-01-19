package com.algav;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Clock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.IPatriciaTrie;
import com.algav.patricia.PatriciaTrie;

public class ExtractFiles {
	
	public static IPatriciaTrie constructPATtrie(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");

		IPatriciaTrie patricia = new PatriciaTrie();
		ArrayList<String> test = new ArrayList<String>();
		//String inputWord;
		while(scanner.hasNext()){
			patricia.ajout(scanner.next());
			test.add(scanner.next());
		}
		test.sort(null);
		//System.out.println((test).toString());
		//System.out.println("TESTING: " + test.equals(patricia.listeMots()));
	
		return patricia;
	}
	
	public static HybridesTries constructHYBtrie(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");

		HybridesTries hybride = new HybridesTries();
		int nb = 0;
		while(scanner.hasNext()){
			hybride = Ajout.ajoutString(scanner.next(),hybride);
			//Ajout.ajoutString("haha",hybride);
			++nb;
		}
		System.out.println("nb mots dico: " + nb);
		return hybride;
	}
	
	public static void testConstruction(String filename) throws FileNotFoundException{
		//test create, listMots, remove
		//create patricia and hybride, add words, use listeMots() compare lists with expetedResult
	
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		ArrayList<String> expectedResult = new ArrayList<String>();
		while(scanner.hasNext()){
			expectedResult.add(scanner.next());
		}
		expectedResult.sort(null);
		//System.out.println("expected list: " + (expectedResult).toString());
		System.out.println("expected size: " + expectedResult.size());
		//expectedResult.remove("about");
		//System.out.println("expected size: " + expectedResult.size());


	}
	
	public static void main (String[] arg){
		try{
		
			IPatriciaTrie p = constructPATtrie("./shakespeare/1henryiv.txt");
			HybridesTries h = constructHYBtrie("./shakespeare/1henryiv.txt");
			System.out.println("patricia: " + p.listeMots().toString());
			System.out.println("hybride:" + Ajout.liste(h).toString());
			
			LinkedList<String> lp = p.listeMots();
			System.out.println("length patricia: " + lp.size());
			System.out.println("length hybride:" + Ajout.liste(h).size());
			
			//expected result
			testConstruction("./shakespeare/1henryiv.txt");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
	}
}
