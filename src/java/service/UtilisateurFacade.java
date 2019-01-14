/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Assets;
import bean.Pays;
import bean.Utilisateur;
import bean.Ville;
import controller.util.HashageUtil;
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
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

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

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    public int createUtilisateur(Utilisateur selected){
        String email = selected.getEmail();
        String motDePasse = selected.getMotDePasse();
        String nom = selected.getNom();
        String prenom = selected.getPrenom();
        String nationalite = selected.getNationalite();
        String nomUtilisateur = selected.getNomUtilisateur();
        if (!email.isEmpty() && !motDePasse.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !nationalite.isEmpty() && !nomUtilisateur.isEmpty()) {
            String motDePasseHashe = HashageUtil.sha256(motDePasse);
            selected.setMotDePasse(motDePasseHashe);
            selected.setCompteActive(true);
            selected.setRole(2);
            SessionUtil.registerUser(selected);
            create(selected);
            return 1;
        } else {
            return -1;
        }
    }
    
    public Utilisateur getUtilisateurInscre(int id){
        return find(id);
    }
    
   public void editerUtilisateur(Utilisateur utilisateur,Assets assets,String pays,String ville){
       
       Ville v = villeFacade.findByTitre(ville);
       Pays p = paysFacade.findByTitre(pays);
       utilisateur.setPays(p);
       utilisateur.setVille(v);
       utilisateur.setAsset(assets);
       edit(utilisateur);
   }
   
   public int seConnnecter(Utilisateur selected) {
        if (selected.getEmail().isEmpty() || selected.getMotDePasse().isEmpty()) {
            return -5;
        } else {
            Utilisateur loadedUtilisateur = findByLogin(selected.getEmail());
            if (loadedUtilisateur == null) {
                return -4;
            } else if (!loadedUtilisateur.getMotDePasse().equals(HashageUtil.sha256(selected.getMotDePasse()))) {
                return -3;
            } else if (loadedUtilisateur.isCompteActive() == false) {
                return -2;
            } else {
                System.out.println("2"+loadedUtilisateur.getEmail());
                SessionUtil.registerUser(loadedUtilisateur);
                return 1;
            }
        }
    }

    private Utilisateur findByLogin(String login) {
        List<Utilisateur> res = em.createQuery("SELECT item FROM Utilisateur item WHERE item.email='" + login + "'").getResultList();
        if (res == null || res.isEmpty() || res.get(0) == null) {
            return null;
        }
        System.out.println(res.get(0).getEmail());
        return res.get(0);
    }
}
