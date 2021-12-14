package org.ex2;

import java.util.Objects;

import org.ex2.exception.NettoyageException;

/**
 * Classe représentant des citernes sécurisé, elle hérite la classe citerne, la différence
 * est qu'elle contient une cuve de Trop Plein, dès lors que la citerne déborde, le surplus 
 * de liquide ira dans la cuve de Trop Plein.
 * @author Idricealy MOURTADHOI
 *
 */
public class CiterneSecurise extends Citerne implements Cloneable{
	
	/**
	 * Cuve de trop plein, si la cuve courante se met à déborder
	 * le jus ira dans cette cuve.
	 */
	private Citerne TropPlein;
	
	/**
	 * Constructeur de la citerne sécurisé avec la construction 
	 * de la cuve de trop plein avec une capacité de 10% par rapport
	 * à la capacité de la cuve principale
	 * @param capacite
	 * @param l le type de liquide de la cuve
	 */
	public CiterneSecurise(int capacite, Liquide l) {
		super(capacite, l);
		TropPlein = new Citerne((int) (capacite*0.1), l);	
	}
	
	/**
	 * Getter de la cuve de trop plein
	 * @return
	 */
	public Citerne getTropPlein() {
		return TropPlein;
	}
	
	/**
	 * Setter de la cuve trop plein
	 * @param tropPlein
	 */
	public void setTropPlein(Citerne tropPlein) {
		TropPlein = tropPlein;
	}


	@Override
	public void ajouterLiquide(int qt) throws NettoyageException {
		int volumeOcc = this.getVolumeOccupe();
		int capacite = this.getCapacite();
		String nomCiterne = this.getCiterneId();
		
		//ajout du liquide en prenant compte le luquide déja présent dans la citerne
		int ajout = volumeOcc +qt;
		// calcul du surplus de liquide si il y a
		int depassement = ajout-capacite;
		
		//on jete une exception si la cuve à été nettoyé et qu'un liquide n'a pas été réaffecté
		if(isNettoyage() == true || this.getLiquide().equals(null)){
			throw new NettoyageException("La cuve à été nettoyé ou est neuve, veuillez assigner un liquide à celle-ci !!");
		}else if(ajout > this.getCapacite()){
			this.setVolumeOccupe(capacite);
			TropPlein.setVolumeOccupe(depassement);
			System.err.println("La cuve "+nomCiterne+ " déborde !!!!");
		}else {
			this.setVolumeOccupe(volumeOcc+qt);
		}
		
		if(TropPlein.getVolumeOccupe() > TropPlein.getCapacite()/2) {
			System.err.print("La cuve de trop plein "+nomCiterne+" est à moitié rempli !!!!!!");
		}
	}
	/*
	 * Méthode identique à sa classe mère sauf que la comparaison sur deux citernes
	 * séurisés se fera sur la comparaison combinée de la citerne principale et de son
	 * trop plein en cumulant les capacités et volumes
	 */ 
	@Override
	public String compareCiterne(Object o) {
		// on recupere le nom de l'instance de notre objet courant
		String nameInstance = this.getClass().getName();
		
		if(o == null) {
			throw new NullPointerException("L'objet passé en paramètre est null !");
		}
		//on jete une exception si l'objet passé en paramètre n'est pas comparable avec notre objet courant
		else if(!o.getClass().getName().equals(nameInstance)) {
			throw new ClassCastException("L'instance de l'objet doit être de type " +nameInstance);
		} 
		else {
			CiterneSecurise c2 = (CiterneSecurise) o;
			// La comparaison est effectué en en cumulant la capacité et le volume de la citerne principal et de son trop plein
			int cumulCapaciteCiterne1 = this.getCapacite() + this.getTropPlein().getCapacite();
			int cumulCapaciteCiterne2 = c2.getCapacite() + c2.getTropPlein().getCapacite();
			
			int cumulVolumeCiterne1 = this.getVolumeOccupe() + this.getTropPlein().getVolumeOccupe();
			int cumulVolumeCiterne2 = c2.getVolumeOccupe() + c2.getTropPlein().getVolumeOccupe();
			
			if(cumulVolumeCiterne1 < cumulVolumeCiterne2 || (cumulVolumeCiterne1 == cumulVolumeCiterne2 && cumulCapaciteCiterne1 < cumulCapaciteCiterne2)) {
				return c2.getCiterneId();
			}else {
				return this.getCiterneId();
			}
		}
	}

	/**
	 * Le test d'égalité se fait sur le cumules les capacités et volume de la citerne 
	 * et de sa citerne de trop plein, sur la date de mise en service et sur 
	 * le liquide de la cuve
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CiterneSecurise other = (CiterneSecurise) obj;
		int cumulCapaciteCiterne1 = this.getCapacite() + this.getTropPlein().getCapacite();
		int cumulCapaciteCiterne2 = other.getCapacite() + other.getTropPlein().getCapacite();
		
		int cumulVolumeCiterne1 = this.getVolumeOccupe() + this.getTropPlein().getVolumeOccupe();
		int cumulVolumeCiterne2 = other.getVolumeOccupe() + other.getTropPlein().getVolumeOccupe();
		
		return cumulCapaciteCiterne1 == cumulCapaciteCiterne2 && Objects.equals(this.getDateMiseEnService(), other.getDateMiseEnService())
				&& this.getLiquide() == other.getLiquide() && cumulVolumeCiterne1 == cumulVolumeCiterne2;
	}
	
	
	@Override
	public String toString() {
		return super.toString()+" et sa cuve de trop plein : "+TropPlein.toString();
	}

	/**
	 * Deep copy des citernes sécurisés
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		CiterneSecurise csClone = (CiterneSecurise) super.clone();
		csClone.TropPlein = (Citerne) TropPlein.clone();
		return csClone;
	}
	
	
}
