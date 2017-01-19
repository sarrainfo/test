package com.algav.patricia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import com.algav.Calculation4;
import com.algav.Comparaison4;
import com.algav.Utility4;

public class TestComparaisonConstruction {

	public PatriciaTrie consFusion(LinkedList<String> input ){
		PatriciaTrie result = new PatriciaTrie();

		for (int i = 0; i < input.size(); ++i){
			PatriciaTrie p1 = new PatriciaTrie(input.get(i));
			result.fusion(p1);
		}
		return result;
	}
	
public static ArrayList<PatriciaTrie> allFilleIndirectory(String patname) throws Exception{
		File directory = new File(patname);
		ArrayList<String> r ;
		if( !directory.exists())
			throw new Exception("repertoire inconnue");
		if(!directory.isDirectory())
			throw new Exception(directory.getName()+" n'est pas un repertoire");
		//ArrayList<String> wordsInFiles =  new ArrayList<String>();
		ArrayList<PatriciaTrie> p = new ArrayList<PatriciaTrie>();
		//HashSet<String> s = new HashSet<String>();
		File[] list = directory.listFiles();
		for(int i =0 ; i < list.length;i++){
			//r = convert2listWithoutDuplicate(patname+"/"+list[i].getName());
			//s.addAll(r);
			//PatriciaTrie f = new PatriciaTrie();
			TestPatricia t = new TestPatricia(list[i].getAbsolutePath());
			p.add((PatriciaTrie)t.getPatriciaTrie());
		}
		return p;
	}

public static PatriciaTrie allFileFusion(ArrayList<PatriciaTrie> l) throws Exception{
	PatriciaTrie p =l.get(0);
	for(int i = 1; i < l.size();i++){
		 p.fusion(l.get(i));
	}
	return p;
}

	public static void main (String[] s) throws FileNotFoundException {
		String patname ="./shakespeare";
		TestPatricia t = new TestPatricia("./shakespeare/cleopatra.txt");
		ArrayList<String> wordsinFiles = null ;
		try {
			wordsinFiles = Utility4.allFileIndirectory(patname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("test temps fusion: " + Calculation4.constructionPatriciaFusion(patname));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test temps ajout successif: " + Calculation4.constructionPatricia(wordsinFiles));

	}
}
