package org.tp2.ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TestPaires {

	public static void main(String[] args) {
		
		//Création de la liste pouvant accuellir des objets de types hétérogènes.
		Collection<Paires<?,?>> c1 = new ArrayList<Paires<?,?>>();
		
		Integer i1 = 1;
		Integer i2 = 2;
		Date d1 = new Date();
		Personne personne1 = new Personne("idrice");
		
		//Création de mes paires
		Paires<Integer,Date> p1 = new Paires<Integer,Date>(i1, d1);
		Paires<Integer, Date> p2 = new Paires<Integer, Date>(i2, d1);
		Paires<Date, Personne> p3 = new Paires<Date,Personne>(d1, personne1);
		
		
		//Remplissage de la liste
		c1.add(p1);
		c1.add(p2);
		c1.add(p3);
		
		for(Paires<?, ?> elt : c1) {
			System.out.println(elt.getS1().toString()+" - "+elt.getU1().toString());
		}
		
		
	}

}
