package bean;

import bean.Propriete;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Disponibilite.class)
public class Disponibilite_ { 

    public static volatile SingularAttribute<Disponibilite, Double> prixDisponiblite;
    public static volatile SingularAttribute<Disponibilite, Date> dateCreation;
    public static volatile SingularAttribute<Disponibilite, Date> dateDebut;
    public static volatile SingularAttribute<Disponibilite, String> titre;
    public static volatile SingularAttribute<Disponibilite, Long> id;
    public static volatile SingularAttribute<Disponibilite, Date> dateFin;
    public static volatile SingularAttribute<Disponibilite, Propriete> propriete;
    public static volatile SingularAttribute<Disponibilite, Boolean> disponible;

}