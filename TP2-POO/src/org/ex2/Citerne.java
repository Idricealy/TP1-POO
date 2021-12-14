package org.ex2;

import java.time.LocalDate;
import java.util.Objects;

import org.ex2.exception.NettoyageException;

/**
 * Classe qui repr�sente la mod�lisation de cuve contenant divers liquides. 
 * @author Idricealy MOURTADHOI
 *
 */
public class Citerne implements EstComparable, Cloneable{
	
	/**
	 * Nombres de cuves cr�e
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
	 * Liquide re�u par ka cuve
	 */
	private Liquide liquide;
	
	/**
	 * Boolean repr�setant l'op�ration de nettoyage complet de la cuve
	 */
	private boolean nettoyage;
	
	/**
	 * Date de la mise en service de la cuve.
	 */
	private LocalDate dateMiseEnService;
	
	/**
	 * Quantit� de liquide pr�sente dans la cuve 
	 */
	private int volumeOccupe;
	
	/**
	 * Constructeur de la citerne
	 * @param capacite capacit� maximal de la citerne
	 * @param l liquide contenu dans la citerne
	 */
	public Citerne(int capacite, Liquide l){
		idCiterne++;
		this.myIdCiterne = idCiterne;
		if(capacite > 0 && capacite <= 20000){
			this.capacite = capacite;
		}else {
			throw new IllegalArgumentException("La capacit� de la citerne doit �tre comprise entre 1 et 20 000 m3.");
		}
		
		this.liquide = l;
		this.nettoyage = false;
		this.dateMiseEnService = LocalDate.now();

	}
	
	/**
	 * Getter du num�ro d'identification
	 * @return id de la cuve
	 */
	public static int getIdCiterne() {
		return idCiterne;
	}
	
	public String getCiterneId() {
		return "Citerne n�" + myIdCiterne;
	}
	
	/**
	 * Getter du liquide
	 * @return
	 */
	public Liquide getLiquide() {
		return liquide;
	}
	
	/**
	 * Setter du liquide contenu dans la cuve, qui peut �tre chang� si 
	 * il ya eu au prealable une operation de nettoyage compl�te dans la cuve.
	 * @param liquide
	 * @throws NettoyageException 
	 */
	public void setLiquide(Liquide liquide) throws NettoyageException {
		if(this.nettoyage == false) {
			throw new NettoyageException("Un nettoyage de la cuve doit �tre fait avant de changer de liquide");
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
	 * Getter de la date de la capacit�
	 * @return la capacit� de la cuve
	 */
	public int getCapacite() {
		return capacite;
	}
	
	/**
	 * Getter du volume occup� de la cuve
	 * @return le volume occup� de la cuve
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
		/*On retourne la premi�re date pass� en param�tre si celle ci est
		 * plus ancienne ou �gale � la seconde pass� en param�tre.
		 */
		if(dateC1.isBefore(c2.getDateMiseEnService()) || dateC1.isEqual(c2.getDateMiseEnService()) ) {
			return c1.getCiterneId();
		}else{
			return c2.getCiterneId();
		}
	}
	
	/**
	 * M�thode pour ajouter du liquide � la cuve.
	 * Si la cuve est trop pleine pour recevoir le quiquide on la rempli au maximum
	 * et on l�ve une exception signalant la quantit� de luquide en d�passement.
	 * Si la cuve vient d'�tre nettoy� ou que le nouveau liquide assign� (cuve neuve) 
	 * n'est pas encore sp�cifi�.
	 * On jete une IllegalArgumentException car on essaye de d�passer la valeur du volume maximal.
	 * @param qt quantit� � ajouter
	 * @throws NettoyageException 
	 */
	public void ajouterLiquide(int qt) throws NettoyageException {
		int ajout = volumeOccupe+qt;
		int depassement = ajout-capacite;
		
		if(isNettoyage() == true || liquide.equals(null)){
			throw new NettoyageException("La cuve � �t� nettoy� ou est neuve, veuillez assigner un liquide � celle-ci !!");
		}else if(ajout > capacite){
			volumeOccupe = capacite;
			throw new IllegalArgumentException("La cuve "+this.getCiterneId()+" est pleine il y a" +depassement+"m3 de liquide en trop !!");
		}else {
			volumeOccupe += qt;
		}
	}
	
	/**
	 * M�thode pour enlever du luquide de la cuve.
	 * On vide la cuve selon la quantit� choisi, si la cuve ne contient pas assez de liquide
	 * par rapport � la quantit� demand�e au niveau du retrait, on l�ve une exception signalant
	 * la quantit� de liquide manquante pour satisfaire � la demande.
	 * @param qt la quantit =� de liquide � enlever
	 */
	public void enleverLiquide(int qt) {
		int retrait = volumeOccupe-qt;
		
		volumeOccupe -= qt;
		
		if(retrait < 0) {
			volumeOccupe = 0;
			throw new IllegalArgumentException("La cuve � �t� vid� mais il manquait "+Math.abs(retrait)+"m3 de quantit� de liquide pour satisfaire � votre demande.");
		}
	}
	/**
	 * Le test d'�galit� se fait sur la capacit�, le volume, la date de mise en service et sur 
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
				+", capacit�: "+this.getCapacite()
				+"m3, mise en service : "+this.getDateMiseEnService()
				+", volume occup� : "+this.getVolumeOccupe()+"m3.";
	}
	
	/**
	 * M�thode de l'interface EstCompare, qui permet de comparer deux citernes
	 * Une Citerne c1 sera consid�r�e inf�rieure � la citerne c2 si son volume 
	 * effectif contenu est inf�rieur au volume contenu dans c2, et pour un volume 
	 * �gale c1 sera inf�rieure � c2 si sa capacit� l�est.
	 */
	@Override
	public String compareCiterne(Object o) {
		// on recupere le nom de l'instance de notre objet courant
		String nameInstance = this.getClass().getName();

		if(o == null) {
			throw new NullPointerException("L'objet pass� en param�tre est null !");
		}
		//on jete une exception si l'objet pass� en param�tre n'est pas comparable avec notre objet courant
		else if(!o.getClass().getName().equals(nameInstance)) {
			throw new ClassCastException("L'instance de l'objet doit �tre de type " +nameInstance);
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
