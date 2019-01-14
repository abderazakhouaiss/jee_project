package bean;

import bean.Propriete;
import bean.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-04T12:23:14")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Integer> nombreAdultes;
    public static volatile SingularAttribute<Reservation, Date> dateDebut;
    public static volatile SingularAttribute<Reservation, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Reservation, Boolean> etatReservation;
    public static volatile SingularAttribute<Reservation, Long> id;
    public static volatile SingularAttribute<Reservation, Date> dateFin;
    public static volatile SingularAttribute<Reservation, Date> dateEtatReservation;
    public static volatile SingularAttribute<Reservation, Propriete> propriete;
    public static volatile SingularAttribute<Reservation, Date> dateReservation;
    public static volatile SingularAttribute<Reservation, Integer> etat;
    public static volatile SingularAttribute<Reservation, Double> prixTotal;
    public static volatile SingularAttribute<Reservation, Integer> nombreEnfants;

}