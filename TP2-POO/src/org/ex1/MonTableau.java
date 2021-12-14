package org.ex1;

/**
 * Classe qui se comporte comme un tableau d'entier
 * @author idric
 *
 */
public class MonTableau implements EstComparable {
	
	/**
	 * Attribut repr�sentant un tableau d'entier
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
	 * M�thode de l'interface EstCompare, qui permet de comparer le tableau d'entier 
	 * avec un objet de type MonTableau. La m�thode retourne -1 si la somme de notre 
	 * tableau d'entier courant est inf�rieur � celui pass� en param�tre, 0 si ils 
	 * sont �gaux et 1 sinon.   
	 */
	@Override
	public int compareA(Object o) {

		String nameInstance = this.getClass().getName();
		
		if(o == null) {
			throw new NullPointerException("L'objet pass� en param�tre est null !");
		}
		else if(!o.getClass().getName().equals(nameInstance)) {
			throw new ClassCastException("L'instance de l'objet doit �tre de type " +nameInstance);
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
