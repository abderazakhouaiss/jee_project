package bean;

import bean.Assets;
import bean.Commentaire;
import bean.Pays;
import bean.Propriete;
import bean.Reservation;
import bean.Ville;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile ListAttribute<Utilisateur, Propriete> proprietes;
    public static volatile SingularAttribute<Utilisateur, String> nationalite;
    public static volatile SingularAttribute<Utilisateur, Boolean> compteActive;
    public static volatile SingularAttribute<Utilisateur, Ville> ville;
    public static volatile SingularAttribute<Utilisateur, Integer> role;
    public static volatile SingularAttribute<Utilisateur, Date> dateNaissance;
    public static volatile SingularAttribute<Utilisateur, String> nomUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> motDePasse;
    public static volatile ListAttribute<Utilisateur, Reservation> reservations;
    public static volatile SingularAttribute<Utilisateur, String> phone;
    public static volatile SingularAttribute<Utilisateur, String> adresse;
    public static volatile SingularAttribute<Utilisateur, Integer> genre;
    public static volatile ListAttribute<Utilisateur, Commentaire> commentaires;
    public static volatile SingularAttribute<Utilisateur, Long> id;
    public static volatile SingularAttribute<Utilisateur, Assets> asset;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, String> apropos;
    public static volatile SingularAttribute<Utilisateur, String> email;
    public static volatile SingularAttribute<Utilisateur, Pays> pays;

}