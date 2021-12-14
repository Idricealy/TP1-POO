package org.ex1;

/**
 * Classe qui se comporte comme un tableau d'entier
 * @author idric
 *
 */
public class MonTableau implements EstComparable {
	
	/**
	 * Attribut représentant un tableau d'entier
	 */
	private int[] array; 
	
	/**
	 * Constructeur qui va initialiser notre tabbleau courant
	 * @param a
	 */
	public MonTableau(int[] a) {
		this.array = a;
	}
	
	public int[] getArray() {
		return array;
	}
	
	/**
	 * Méthode de l'interface EstCompare, qui permet de comparer le tableau d'entier 
	 * avec un objet de type MonTableau. La méthode retourne -1 si la somme de notre 
	 * tableau d'entier courant est inférieur à celui passé en paramètre, 0 si ils 
	 * sont égaux et 1 sinon.   
	 */
	@Override
	public int compareA(Object o) {

		String nameInstance = this.getClass().getName();
		
		if(o == null) {
			throw new NullPointerException("L'objet passé en paramètre est null !");
		}
		else if(!o.getClass().getName().equals(nameInstance)) {
			throw new ClassCastException("L'instance de l'objet doit être de type " +nameInstance);
		} 
		else {
			MonTableau oTableau = (MonTableau) o;
			
			int thisSommeArray = 0;
			int sommeArray = 0;
			
			for (int number : this.array) {
				thisSommeArray += number;
			}
			
			for (int number : oTableau.getArray()) {
				sommeArray += number;
			}
			
			if(thisSommeArray < sommeArray) {
				return -1;
			} else if(thisSommeArray == sommeArray) {
				return 0;
			} else {
				return 1;
			}
		}
		
	}

}
