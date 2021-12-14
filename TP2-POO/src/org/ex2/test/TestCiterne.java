package org.ex2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.ex2.Citerne;
import org.ex2.CiterneSecurise;
import org.ex2.Liquide;
import org.ex2.exception.NettoyageException;
import org.junit.jupiter.api.Test;

class TestCiterne {

	@Test
	void testPlusAncienne() {
		Citerne c1 = new Citerne(10000, Liquide.VIN);
		Citerne c2 = new Citerne(20000, Liquide.EAU);
		assertEquals(c1.getCiterneId(), Citerne.plusAncienne(c1, c2));
	}

	@Test
	void testAjouterLiquide() throws NettoyageException {
		Citerne c3 = new Citerne(10000, Liquide.VIN);
		c3.ajouterLiquide(7500);
		c3.ajouterLiquide(500);
		assertEquals(8000, c3.getVolumeOccupe());
	}
	
	@Test
	void testEnleverLiquide() throws NettoyageException {
		Citerne c4 = new Citerne(5000, Liquide.VIN);
		c4.ajouterLiquide(5000);
		c4.enleverLiquide(2500);
		assertEquals(2500, c4.getVolumeOccupe());
	}
	
	@Test
	void testEquals() throws NettoyageException {
		Citerne c5 = new Citerne(5000, Liquide.HUILE);
		Citerne c6 = new Citerne(5000, Liquide.HUILE);
		
		c5.ajouterLiquide(2500);
		c6.ajouterLiquide(2500);
		assertEquals(true, c5.equals(c6));
	}
	
	@Test
	void testAjoutLiquideApresNettoyage() throws NettoyageException {
		Citerne c7 = new Citerne(20000, Liquide.VIN);
		c7.nettoyerCiterne();
		c7.setLiquide(Liquide.EAU);
		c7.ajouterLiquide(1000);	
		assertEquals(1000, c7.getVolumeOccupe());
	}
	
	@Test
	void testCompareCiterne() throws NettoyageException {
		Citerne c8 = new Citerne(5000, Liquide.VIN);
		Citerne c9 = new Citerne(5000, Liquide.EAU);
		
		c8.ajouterLiquide(1000);
		c9.ajouterLiquide(1001);
		
		assertEquals(c9.getCiterneId(), c8.compareCiterne(c9));
	}
	
	@Test
	void testAjouterLiquideCiterneSecurise() throws NettoyageException{
		CiterneSecurise c10 = new CiterneSecurise (5000, Liquide.VIN);
		c10.ajouterLiquide(5251);
		assertEquals(251, c10.getTropPlein().getVolumeOccupe());
		assertEquals(500, c10.getTropPlein().getCapacite());
	}
	
	@Test
	void testCompareCiterneSecurise() throws NettoyageException {
		CiterneSecurise c11 = new CiterneSecurise(5000, Liquide.EAU);
		CiterneSecurise c12 = new CiterneSecurise(5001, Liquide.EAU);
		
		c11.ajouterLiquide(1000);
		c12.ajouterLiquide(1000);
		
		assertEquals(c12.getCiterneId(), c11.compareCiterne(c12));
	}
	
	@Test
	void testEqualsCiterne() throws NettoyageException {
		CiterneSecurise c13 = new CiterneSecurise(5000, Liquide.HUILE);
		CiterneSecurise c14 = new CiterneSecurise(5000, Liquide.HUILE);
		
		c13.ajouterLiquide(2500);
		c14.ajouterLiquide(2500);
		assertEquals(true, c13.equals(c14));
	}
	
	@Test
	void testCloneCiterneSecurise() throws CloneNotSupportedException, NettoyageException {
		CiterneSecurise c15 = new CiterneSecurise(5000, Liquide.HUILE);
		CiterneSecurise c16 = (CiterneSecurise) c15.clone();
		
		c15.getTropPlein().ajouterLiquide(50);
		assertNotEquals(c16.toString(), c15.toString());
	}
	
	@Test
	/**
	 * Test à lancer seul, sinon le compilateur prend en compte les instanciations des 
	 * autres méthode de test, et donc ce test échoue.
	 */
	void testIdCiterne() {
		CiterneSecurise c18 = new CiterneSecurise(10, Liquide.HUILE);
		Citerne c19 = new Citerne(10, Liquide.VIN);
		
		assertEquals("Citerne n°1", c18.getCiterneId());
		assertEquals("Citerne n°3", c19.getCiterneId());
		assertEquals(3, Citerne.getIdCiterne());
	}
	
	@Test
	void testNettoyage() throws NettoyageException {
		Citerne c20 = new Citerne(2500, Liquide.EAU);
		
		c20.nettoyerCiterne();
		assertEquals(null, c20.getLiquide());
		
		c20.setLiquide(Liquide.VIN);
		c20.ajouterLiquide(200);
		assertEquals("VIN", c20.getLiquide().name());
	}
}
