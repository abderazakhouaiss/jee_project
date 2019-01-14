/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Propriete;
import bean.Reservation;
import bean.Utilisateur;
import controller.util.DateUtil;
import controller.util.MailUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus-pc
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    public List<Reservation> getReservationByUser(Utilisateur utilisateur, boolean test) {
        String query = "SELECT r FROM Reservation r WHERE r.utilisateur.id = " + utilisateur.getId() + " ORDER BY r.dateReservation DESC";
        if (test == true) {
            return (List<Reservation>) em.createQuery(query).setMaxResults(5).getResultList();
        }
        return (List<Reservation>) em.createQuery(query).getResultList();
    }

    public boolean getReservationByCriteria(Reservation r, Propriete p) {
        return (getResAfter(r, p) || getResBefore(r, p));
    }
    public boolean getResAfter(Reservation r, Propriete p){
        
        String query1 = "SELECT r FROM Reservation r WHERE function('date_format', r.dateFin, '%Y-%m-%d') < "+DateUtil.getSqlDate(r.getDateDebut())+
                " AND r.propriete.id="+p.getId();
        boolean test = em.createQuery(query1).getResultList().isEmpty();
        System.out.println("testAfter "+test);
        System.out.println("testAfter "+query1);
        return test;
    }
    
    public boolean getResBefore(Reservation r, Propriete p){
        String query1 = "SELECT r FROM Reservation r WHERE function('date_format', r.dateDebut, '%Y-%m-%d') > "+DateUtil.getSqlDate(r.getDateFin())+
                " AND r.propriete.id="+p.getId();
        boolean test = em.createQuery(query1).getResultList().isEmpty();
        System.out.println("testBefore "+test);
        System.out.println("testBefore "+query1);
        return test;
    }
    public int createReservation(Reservation reservation, Propriete propriete, Utilisateur utilisateur) throws MessagingException, IOException {
        if (reservation == null) {
            return -1;
        } else if (propriete == null) {
            return -2;
        } else if (utilisateur == null) {
            return -3;
        } else {
            reservation.setPropriete(propriete);
            reservation.setUtilisateur(utilisateur);
            reservation.setDateReservation(new Date());
            reservation.setEtat(0);
            create(reservation);
            String user = "hnatsu143@gmail.com";
            String pass = "abcd1234@";
            String dest[] = {reservation.getUtilisateur().getEmail()};
            String sub = "Réservation d'une villa";
            String body = "Bonjour, <br><br> Vous avez réserver une villa, voici les infos:<br>"
                    + "Prix total:"+reservation.getPrixTotal()
                    +"<br> Date debut:"+reservation.getDateDebut()
                    +"<br> Date fin:"+reservation.getDateFin()
                    +"<br><br> Cordialement";
            MailUtil.sendFromGMail(user, pass, dest, sub, body);
            return 1;
        }
    }

    public List<Reservation> getNoValidatedReservations() {
        String query = "SELECT r FROM Reservation r WHERE r.etatReservation = 0";
        return (List<Reservation>) em.createQuery(query).getResultList();
    }

    public int validerReservation(Reservation reservation) {
        if (reservation == null) {
            return -1;
        } else {
            reservation.setDateEtatReservation(new Date());
            reservation.setEtat(1);
            reservation.setEtatReservation(true);
            edit(reservation);
            return 1;
        }
    }
}
