/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import bean.Ville;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus-pc
 */
@Stateless
public class VilleFacade extends AbstractFacade<Ville> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VilleFacade() {
        super(Ville.class);
    }

    public List<Ville> getAllVilles() {
        return findAll();
    }

    public Ville findByTitre(String name) {
        if (name != null) {
            return (Ville) em.createQuery("SELECT v FROM Ville v where v.titre='" + name + "'").getSingleResult();
        } else {
            return null;
        }
    }

    public List<Ville> findByCountry(String pays) {
        return em.createQuery("SELECT v FROM Ville v where v.pays.nom='" + pays + "'").getResultList();
    }
}
