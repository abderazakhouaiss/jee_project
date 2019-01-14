package bean;

import bean.Propriete;
import bean.Utilisateur;
import bean.Ville;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Pays.class)
public class Pays_ { 

    public static volatile ListAttribute<Pays, Propriete> proprietes;
    public static volatile ListAttribute<Pays, Utilisateur> utilisateurs;
    public static volatile ListAttribute<Pays, Ville> villes;
    public static volatile SingularAttribute<Pays, Long> id;
    public static volatile SingularAttribute<Pays, String> nom;

}