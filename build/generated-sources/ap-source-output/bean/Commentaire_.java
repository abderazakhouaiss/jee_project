package bean;

import bean.Propriete;
import bean.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Commentaire.class)
public class Commentaire_ { 

    public static volatile SingularAttribute<Commentaire, Date> dateCreation;
    public static volatile SingularAttribute<Commentaire, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Commentaire, String> titre;
    public static volatile SingularAttribute<Commentaire, Long> id;
    public static volatile SingularAttribute<Commentaire, String> message;
    public static volatile SingularAttribute<Commentaire, Propriete> propriete;
    public static volatile SingularAttribute<Commentaire, Boolean> etat;

}