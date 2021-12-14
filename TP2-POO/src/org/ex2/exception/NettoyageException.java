package org.ex2.exception;

/**
 * Classe qui permet de lever une exception d�s lors que
 * l'on souhaite changer de liquide dans la cuve mais qu'on
 * a pas nettoy� la cuve au pr�alable.
 * 
 * @author Idricealy MOURTADHOI
 *
 */
public class NettoyageException extends Exception {
	
	public NettoyageException() {
		
	}
	
	public NettoyageException(String message) {
		super(message);
	}
}
