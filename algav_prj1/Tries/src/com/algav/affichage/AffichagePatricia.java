package com.algav.affichage;

import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.algav.patricia.PatriciaTrie;
import com.algav.patricia.TestPatricia;
import static com.algav.patricia.string.StringManipulation.*;

public class AffichagePatricia {

	public static void afficherPat(PatriciaTrie p, String filename){
		
	}
	
	public static String fileHeader(String graphName){
		return ("graph " + graphName + " {" + "node [shape=record];" );
	}
	
	public static String fileFooter(){
		return "}";
	}
	
	public static String startLine(int i){
		String output = new String();
		output = output + "patricia"+i+ " [label=\"" ;
		return output;
	}
	
	public static String endLine(){
		return "\"];";
	}
	
	public static String printCase(String content, int i){
		return "<f"+i+"> " + content + "|" ;
	}
	public static String printLastCase(String content, int i){
		return "<f"+i+"> " + content + "\"];";
	}
	/*
	public static String declareNodes(PatriciaTrie n, int i){
		String output = new String();
		int c = 0;
		output = output + printNode( n, 0);
		for (int k = 0; k < n.getSize()-1; ++k){
			if (n.getSon(k)!=null){
				String word = n.getWord(k);
				if (containsEpsilon(word))
					word=truncEpsilon(word);
					output = output + printCase(word,k);
			}
		}
	}
	*/
	public static String printNode(PatriciaTrie n, int i){
		String output = new String();
		output = output + startLine(i);
		for (int k = 0; k < n.getSize()-1; ++k){
			if (n.getCase(k)!=null){
				String word = n.getWord(k);
				if (containsEpsilon(word))
					word=truncEpsilon(word);
				output = output + printCase(word,k);
			}
		}
		output = output + printLastCase(n.getWord(n.getSize()-1),n.getSize()-1);
		return output;
	}
	public static void main(String[] str){
		TestPatricia t = new TestPatricia("./shakespeare/test.txt");
	    String content = fileHeader("g") +
	    		printNode((PatriciaTrie)t.getPatriciaTrie(), 0) 
	    		+fileFooter();
try{
	    File file = new  File("./test.gv");

        // if file doesnt exist, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        System.out.println("Done");

    } catch (IOException e) {
        e.printStackTrace();
    }
		//System.out.println(fileHeader("test"));
		//System.out.println(printNode((PatriciaTrie)t.getPatriciaTrie(), 0));
		/*System.out.println(startLine(0) + 
				printCase("a",  0) + printCase( "b", 1) + 
				printLastCase( "c",  2) + endLine());*/
		//System.out.println(fileFooter());
	}}

