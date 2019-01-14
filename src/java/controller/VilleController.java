/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Ville;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import service.VilleFacade;

/**
 *
 * @author asus-pc
 */
@Named("villeController")
@SessionScoped
public class VilleController implements Serializable{
    @EJB
    private VilleFacade ejbFacade;
    
    private List<Ville> items = null;
    
    private Ville selected;

    public VilleController(){
    }
    
    public List<Ville> getItems() {
        if(items == null)
            items = ejbFacade.getAllVilles();
        return items;
    }

    public void setItems(List<Ville> items) {
        this.items = items;
    }

    public Ville getSelected() {
        if(selected == null)
            selected = new Ville();
        return selected;
    }

    public void setSelected(Ville selected) {
        this.selected = selected;
    }
    
    
    public void onCountryChanged(String payss){
        setItems(ejbFacade.findByCountry(payss));
    }
}
