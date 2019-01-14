/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import bean.Propriete;
import bean.Utilisateur;
import bean.Ville;
import controller.util.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus-pc
 */
@Stateless
public class ProprieteFacade extends AbstractFacade<Propriete> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;
    @EJB
    private VilleFacade villeFacade;
    @EJB
    private PaysFacade paysFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProprieteFacade() {
        super(Propriete.class);
    }

    public Propriete createProperty(Propriete property, String pays, String ville) {
        Utilisateur utilisateur = (Utilisateur) SessionUtil.getConnectedUser();
        Ville v = villeFacade.findByTitre(ville);
        Pays p = paysFacade.findByTitre(pays);
        property.setProprietaire(utilisateur);
        property.setVille(v);
        property.setPays(p);
        property.setId(generateId());
        create(property);
        return property;
    }

    private Long generateId() {
        return generateId("Propriete", "id");
    }

    public List<Propriete> getProprieteByUser(Utilisateur utilisateur, boolean test) {
        String query = "SELECT p FROM Propriete p WHERE p.proprietaire.id = " + utilisateur.getId();
        if (test == true) {
            return (List<Propriete>) em.createQuery(query).setMaxResults(5).getResultList();
        }
        return (List<Propriete>) em.createQuery(query).getResultList();
    }

    public List<Propriete> getAllReservation() {
        String query = "SELECT p FROM Propriete p WHERE p.valide = 1 AND p.visible = 1";
        return (List<Propriete>) em.createQuery(query).getResultList();
    }

    public List<Propriete> getNoValidatedProperties() {
        String query = "SELECT p FROM Propriete p WHERE p.valide = 0 OR p.visible = 0";
        return (List<Propriete>) em.createQuery(query).getResultList();
    }

    public int validate(Propriete propriete) {
        if (propriete == null) {
            return -1;
        } else {
            propriete.setValide(true);
            propriete.setVisible(true);
            edit(propriete);
            return 1;
        }
    }
}
