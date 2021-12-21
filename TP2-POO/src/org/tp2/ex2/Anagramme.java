package org.tp2.ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Anagramme {

	public static void main(String[] args){
		File f = new File("");
		try{
			Scanner scan = new Scanner(f);
			
			while(scan.hasNext())System.out.print(scan.next()+" ");
			scan.close();
		}catch(FileNotFoundException e){
			System.err.println("Le fichier n'a pas ete trouve...");
		}
	}

}
