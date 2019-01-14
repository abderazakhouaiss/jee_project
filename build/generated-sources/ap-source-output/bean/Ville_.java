package bean;

import bean.Pays;
import bean.Propriete;
import bean.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Ville.class)
public class Ville_ { 

    public static volatile ListAttribute<Ville, Propriete> proprietes;
    public static volatile ListAttribute<Ville, Utilisateur> utilisateurs;
    public static volatile SingularAttribute<Ville, String> titre;
    public static volatile SingularAttribute<Ville, Long> id;
    public static volatile SingularAttribute<Ville, Pays> pays;

}