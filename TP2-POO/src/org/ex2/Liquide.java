package org.ex2;

/**
 * Classe d'énumeration pour les liquides que les cuves peuvent recevoir
 * @author Idricealy MOURTADHOI
 *
 */
public enum Liquide {
	EAU(10),
	VIN(15),
	HUILE(9);
	/**
	 * Temperature de conservation idéale du liquide
	 */
	private final double temperature;
	/**
	 * Constructeur qui intialise la température de conservation idéale du liquide
	 * @param temp la temperature de la conservation du liquide
	 */
	
	Liquide(int temp) {
		this.temperature = temp;
	}
	
	/**
	 * Getter de la temperature
	 * @return la temperature idéale du liquide
	 */
	public double getTemperature() {
		return temperature;
	}
	
	
}
