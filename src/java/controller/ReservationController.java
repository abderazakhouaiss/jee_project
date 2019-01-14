/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Propriete;
import bean.Reservation;
import bean.Utilisateur;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import service.ProprieteAssetsFacade;
import service.ProprieteFacade;
import service.ReservationFacade;

/**
 *
 * @author asus-pc
 */
@Named(value = "reservationController")
@SessionScoped
public class ReservationController implements Serializable {

    /**
     * Creates a new instance of ReservationController
     */
    public ReservationController() {
    }
    private Reservation selected;
    private Propriete propriete;
    private List<Propriete> items = null;
    private String error = null;
    private boolean test;
    private Long rib;
    private Date dateValidite;
    private Date today = new Date();
    @EJB
    private ReservationFacade ejbFacade;
    @EJB
    private ProprieteFacade proprieteFacade;
    @EJB
    private ProprieteAssetsFacade proprieteAssetsFacade;

    public Reservation getSelected() {
        if (selected == null) {
            selected = new Reservation();
        }
        return selected;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public void setSelected(Reservation selected) {
        this.selected = selected;
    }

    public Long getRib() {
        return rib;
    }

    public void setRib(Long rib) {
        this.rib = rib;
    }

    public List<Propriete> getItems() {
        items = proprieteFacade.getAllReservation();
        for (Propriete item : items) {
            item.setProprieteAssetss(proprieteAssetsFacade.getProprieteAssets(item));
        }
        return items;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public void setItems(List<Propriete> items) {
        this.items = items;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAssetUrl(String link) {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return origRequest.getContextPath() + link;
    }

    public void demanderReservation(Propriete propriete) throws IOException {
        this.propriete = propriete;
        FacesContext.getCurrentInstance().getExternalContext().redirect("../reservation/ViewPropriete.xhtml");
    }

    public boolean testerCriteres() {
        error = null;
        if (selected.getDateFin() == null || selected.getDateDebut() == null) {
            error = "Tous les champs sont requierts";
            selected = null;
            test = false;
            return false;
        } else if (getDifferenceDays(selected.getDateDebut(), selected.getDateFin()) <= 0) {
            error = "Saisir des dates correctes";
            selected = null;
            test = false;
            return false;
        } else if (selected.getNombreAdultes() > propriete.getMaximumAdultes()) {
            error = "le nombre d'adultes est très grand";
            selected = null;
            test = false;
            return false;
        } else if (selected.getNombreEnfants() > propriete.getMaximumEnfants()) {
            error = "le nombre d'enfants est très grand";
            selected = null;
            test = false;
            return false;
        } else if (!ejbFacade.getReservationByCriteria(selected, propriete)) {
            error = "la villa n'est pas disponible dans les jours spécifés";
            selected = null;
            test = false;
            return false;
        }
        selected.setPrixTotal(propriete.getPrix() * getDifferenceDays(selected.getDateDebut(), selected.getDateFin()));
        test = true;
        error = null;
        return true;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void paiement() throws IOException, MessagingException {
        Utilisateur utilisateur = (Utilisateur) SessionUtil.getConnectedUser();
        int res = ejbFacade.createReservation(selected, propriete, utilisateur);
        selected = null;
        test = false;
        error = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("../profile/Profile.xhtml");
    }

    public List<Reservation> getNVR() {
        return ejbFacade.getNoValidatedReservations();
    }

    public String formatDate(Date date) {
        SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
        return sm.format(date);
    }

    public void valider(Reservation reservation) throws IOException {
        int res = ejbFacade.validerReservation(reservation);
        if (res == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administration/ValidateReservation.xhtml");
        }
    }
}
