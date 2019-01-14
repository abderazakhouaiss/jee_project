/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus-pc
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }

    public List<Pays> getAllPays() {
        return findAll();
    }

    public Pays findByTitre(String name) {
        if (name != null) {
            return (Pays) em.createQuery("SELECT v FROM Pays v where v.nom='" + name + "'").getSingleResult();
        } else {
            return null;
        }
    }
}
