package org.ex2.exception;

/**
 * Classe qui permet de lever une exception dès lors que
 * l'on souhaite changer de liquide dans la cuve mais qu'on
 * a pas nettoyé la cuve au préalable.
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
