package bean;

import bean.Commentaire;
import bean.Disponibilite;
import bean.Pays;
import bean.ProprieteAssets;
import bean.Reservation;
import bean.Utilisateur;
import bean.Ville;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Propriete.class)
public class Propriete_ { 

    public static volatile SingularAttribute<Propriete, Integer> nombreChambreDeBain;
    public static volatile ListAttribute<Propriete, ProprieteAssets> proprieteAssetss;
    public static volatile SingularAttribute<Propriete, Ville> ville;
    public static volatile SingularAttribute<Propriete, Boolean> visible;
    public static volatile SingularAttribute<Propriete, Double> prix;
    public static volatile SingularAttribute<Propriete, Integer> maximumEnfants;
    public static volatile SingularAttribute<Propriete, Boolean> fumee;
    public static volatile SingularAttribute<Propriete, Double> coordonneeY;
    public static volatile SingularAttribute<Propriete, String> telephone;
    public static volatile SingularAttribute<Propriete, Double> coordonneeX;
    public static volatile SingularAttribute<Propriete, Boolean> valide;
    public static volatile SingularAttribute<Propriete, Integer> nombreSallons;
    public static volatile SingularAttribute<Propriete, String> nom;
    public static volatile SingularAttribute<Propriete, Utilisateur> proprietaire;
    public static volatile ListAttribute<Propriete, Reservation> reservations;
    public static volatile SingularAttribute<Propriete, Integer> nombreChambreACoucher;
    public static volatile SingularAttribute<Propriete, String> adresse;
    public static volatile SingularAttribute<Propriete, Integer> maximumAdultes;
    public static volatile ListAttribute<Propriete, Commentaire> commentaires;
    public static volatile SingularAttribute<Propriete, Long> id;
    public static volatile SingularAttribute<Propriete, String> apropos;
    public static volatile SingularAttribute<Propriete, String> email;
    public static volatile ListAttribute<Propriete, Disponibilite> disponibilites;
    public static volatile SingularAttribute<Propriete, Pays> pays;

}