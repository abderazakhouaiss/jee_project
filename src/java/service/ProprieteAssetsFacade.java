/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Assets;
import bean.Propriete;
import bean.ProprieteAssets;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus-pc
 */
@Stateless
public class ProprieteAssetsFacade extends AbstractFacade<ProprieteAssets> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProprieteAssetsFacade() {
        super(ProprieteAssets.class);
    }
    public int createPA(Propriete propriete, Assets assets){
        if(propriete == null)
            return -1;
        if(assets == null)
            return -2;
        else{
            ProprieteAssets proprieteAssets = new ProprieteAssets();
            proprieteAssets.setAsset(assets);
            proprieteAssets.setPropriete(propriete);
            create(proprieteAssets);
            return 1;
        }
    }
    
    public List<ProprieteAssets> getProprieteAssets(Propriete propriete){
        String query = "SELECT pa FROM ProprieteAssets pa WHERE pa.propriete.id = "+propriete.getId();
        return (List<ProprieteAssets>) em.createQuery(query).getResultList();
    }
}
