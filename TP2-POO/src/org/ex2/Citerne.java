package org.ex2;

import java.time.LocalDate;
import java.util.Objects;

import org.ex2.exception.NettoyageException;

/**
 * Classe qui représente la modélisation de cuve contenant divers liquides. 
 * @author Idricealy MOURTADHOI
 *
 */
public class Citerne implements EstComparable, Cloneable{
	
	/**
	 * Nombres de cuves crée
	 */
	private static int idCiterne = 0;
	
	/**
	 * Id unique de la citerne
	 */
	private int myIdCiterne;
	/**
	 * Capacite en m2 de la cuve
	 */
	private int capacite;
	
	/**
	 * Liquide reçu par ka cuve
	 */
	private Liquide liquide;
	
	/**
	 * Boolean représetant l'opération de nettoyage complet de la cuve
	 */
	private boolean nettoyage;
	
	/**
	 * Date de la mise en service de la cuve.
	 */
	private LocalDate dateMiseEnService;
	
	/**
	 * Quantité de liquide présente dans la cuve 
	 */
	private int volumeOccupe;
	
	/**
	 * Constructeur de la citerne
	 * @param capacite capacité maximal de la citerne
	 * @param l liquide contenu dans la citerne
	 */
	public Citerne(int capacite, Liquide l){
		idCiterne++;
		this.myIdCiterne = idCiterne;
		if(capacite > 0 && capacite <= 20000){
			this.capacite = capacite;
		}else {
			throw new IllegalArgumentException("La capacité de la citerne doit être comprise entre 1 et 20 000 m3.");
		}
		
		this.liquide = l;
		this.nettoyage = false;
		this.dateMiseEnService = LocalDate.now();

	}
	
	/**
	 * Getter du numéro d'identification
	 * @return id de la cuve
	 */
	public static int getIdCiterne() {
		return idCiterne;
	}
	
	public String getCiterneId() {
		return "Citerne n°" + myIdCiterne;
	}
	
	/**
	 * Getter du liquide
	 * @return
	 */
	public Liquide getLiquide() {
		return liquide;
	}
	
	/**
	 * Setter du liquide contenu dans la cuve, qui peut être changé si 
	 * il ya eu au prealable une operation de nettoyage complète dans la cuve.
	 * @param liquide
	 * @throws NettoyageException 
	 */
	public void setLiquide(Liquide liquide) throws NettoyageException {
		if(this.nettoyage == false) {
			throw new NettoyageException("Un nettoyage de la cuve doit être fait avant de changer de liquide");
		} else {
			this.liquide = liquide;
			this.nettoyage = false;
		}
	}
	
	/**
	 * Getter du nettoyage
	 * @return
	 */
	public boolean isNettoyage() {
		return nettoyage;
	}
	
	public void nettoyerCiterne() {
		nettoyage = true;
		liquide = null;
	}
	
	/**
	 * Getter de la date de la mise en service de la cuve 
	 * @return
	 */
	public LocalDate getDateMiseEnService() {
		return dateMiseEnService;
	}
	
	/**
	 * Getter de la date de la capacité
	 * @return la capacité de la cuve
	 */
	public int getCapacite() {
		return capacite;
	}
	
	/**
	 * Getter du volume occupé de la cuve
	 * @return le volume occupé de la cuve
	 */
	public int getVolumeOccupe() {
		return volumeOccupe;
	}
	
	
	public void setVolumeOccupe(int volumeOccupe) {
		this.volumeOccupe = volumeOccupe;
	}
	/**
	 * Retourne la cuve qui a la date de mise en service la plus ancienne
	 * @param c1 citerne 
	 * @param c2 citerne
	 * @return la citerne la plus ancienne
	 */
	public static String plusAncienne(Citerne c1, Citerne c2) {
		LocalDate dateC1 = c1.getDateMiseEnService();
		/*On retourne la première date passé en paramètre si celle ci est
		 * plus ancienne ou égale à la seconde passé en paramètre.
		 */
		if(dateC1.isBefore(c2.getDateMiseEnService()) || dateC1.isEqual(c2.getDateMiseEnService()) ) {
			return c1.getCiterneId();
		}else{
			return c2.getCiterneId();
		}
	}
	
	/**
	 * Méthode pour ajouter du liquide à la cuve.
	 * Si la cuve est trop pleine pour recevoir le quiquide on la rempli au maximum
	 * et on lève une exception signalant la quantité de luquide en dépassement.
	 * Si la cuve vient d'être nettoyé ou que le nouveau liquide assigné (cuve neuve) 
	 * n'est pas encore spécifié.
	 * On jete une IllegalArgumentException car on essaye de dépasser la valeur du volume maximal.
	 * @param qt quantité à ajouter
	 * @throws NettoyageException 
	 */
	public void ajouterLiquide(int qt) throws NettoyageException {
		int ajout = volumeOccupe+qt;
		int depassement = ajout-capacite;
		
		if(isNettoyage() == true || liquide.equals(null)){
			throw new NettoyageException("La cuve à été nettoyé ou est neuve, veuillez assigner un liquide à celle-ci !!");
		}else if(ajout > capacite){
			volumeOccupe = capacite;
			throw new IllegalArgumentException("La cuve "+this.getCiterneId()+" est pleine il y a" +depassement+"m3 de liquide en trop !!");
		}else {
			volumeOccupe += qt;
		}
	}
	
	/**
	 * Méthode pour enlever du luquide de la cuve.
	 * On vide la cuve selon la quantité choisi, si la cuve ne contient pas assez de liquide
	 * par rapport à la quantité demandée au niveau du retrait, on lève une exception signalant
	 * la quantité de liquide manquante pour satisfaire à la demande.
	 * @param qt la quantit =é de liquide à enlever
	 */
	public void enleverLiquide(int qt) {
		int retrait = volumeOccupe-qt;
		
		volumeOccupe -= qt;
		
		if(retrait < 0) {
			volumeOccupe = 0;
			throw new IllegalArgumentException("La cuve à été vidé mais il manquait "+Math.abs(retrait)+"m3 de quantité de liquide pour satisfaire à votre demande.");
		}
	}
	/**
	 * Le test d'égalité se fait sur la capacité, le volume, la date de mise en service et sur 
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
		Citerne other = (Citerne) obj;
		return capacite == other.capacite && Objects.equals(dateMiseEnService, other.dateMiseEnService)
				&& liquide == other.liquide && volumeOccupe == other.volumeOccupe;
	}
	
	@Override
	public String toString() {
		return getCiterneId()
				+", "+this.liquide.name()
				+", capacité: "+this.getCapacite()
				+"m3, mise en service : "+this.getDateMiseEnService()
				+", volume occupé : "+this.getVolumeOccupe()+"m3.";
	}
	
	/**
	 * Méthode de l'interface EstCompare, qui permet de comparer deux citernes
	 * Une Citerne c1 sera considérée inférieure à la citerne c2 si son volume 
	 * effectif contenu est inférieur au volume contenu dans c2, et pour un volume 
	 * égale c1 sera inférieure à c2 si sa capacité l’est.
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
			Citerne c2 = (Citerne) o;
			int volumeCourant = this.getVolumeOccupe();
			int volumeC2 = c2.getVolumeOccupe();
			
			if(volumeCourant < volumeC2 || (volumeCourant == volumeC2 && this.getCapacite() < c2.getCapacite())) {
				return c2.getCiterneId();
			}else {
				return this.getCiterneId();
			}
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
