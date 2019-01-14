/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author asus-pc
 */
@Entity
public class Propriete implements Serializable {

    @OneToMany(mappedBy = "propriete")
    private List<ProprieteAssets> proprieteAssetss;

    @OneToMany(mappedBy = "propriete")
    private List<Commentaire> commentaires;

    @OneToMany(mappedBy = "propriete")
    private List<Disponibilite> disponibilites;

    @OneToMany(mappedBy = "propriete")
    private List<Reservation> reservations;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom,apropos,telephone,adresse,email;
    private boolean fumee,visible,valide;
    private int maximumAdultes,maximumEnfants,nombreSallons,nombreChambreACoucher,nombreChambreDeBain;
    private double prix;
    @ManyToOne
    private Utilisateur proprietaire;
    @ManyToOne
    private Ville ville;
    @ManyToOne
    private Pays pays;
    private Double coordonneeX,coordonneeY;

    public Double getCoordonneeX() {
        return coordonneeX;
    }

    public void setCoordonneeX(Double coordonneeX) {
        this.coordonneeX = coordonneeX;
    }

    public Double getCoordonneeY() {
        return coordonneeY;
    }

    public void setCoordonneeY(Double coordonneeY) {
        this.coordonneeY = coordonneeY;
    }
    
    public Long getId() {
        return id;
    }

    public List<ProprieteAssets> getProprieteAssetss() {
        return proprieteAssetss;
    }

    public void setProprieteAssetss(List<ProprieteAssets> proprieteAssetss) {
        this.proprieteAssetss = proprieteAssetss;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Disponibilite> getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(List<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }
    
    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApropos() {
        return apropos;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    public void setApropos(String apropos) {
        this.apropos = apropos;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFumee() {
        return fumee;
    }

    public void setFumee(boolean fumee) {
        this.fumee = fumee;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public int getMaximumAdultes() {
        return maximumAdultes;
    }

    public void setMaximumAdultes(int maximumAdultes) {
        this.maximumAdultes = maximumAdultes;
    }

    public int getMaximumEnfants() {
        return maximumEnfants;
    }

    public void setMaximumEnfants(int maximumEnfants) {
        this.maximumEnfants = maximumEnfants;
    }

    public int getNombreSallons() {
        return nombreSallons;
    }

    public void setNombreSallons(int nombreSallons) {
        this.nombreSallons = nombreSallons;
    }

    public int getNombreChambreACoucher() {
        return nombreChambreACoucher;
    }

    public void setNombreChambreACoucher(int nombreChambreACoucher) {
        this.nombreChambreACoucher = nombreChambreACoucher;
    }

    public int getNombreChambreDeBain() {
        return nombreChambreDeBain;
    }

    public void setNombreChambreDeBain(int nombreChambreDeBain) {
        this.nombreChambreDeBain = nombreChambreDeBain;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propriete)) {
            return false;
        }
        Propriete other = (Propriete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNom()+" "+getApropos()+" "+getAdresse()+getCoordonneeX()+" "+getCoordonneeY()+" "+isFumee();
    }
    
}
