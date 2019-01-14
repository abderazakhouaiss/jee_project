/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Pays;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.PaysFacade;

/**
 *
 * @author asus-pc
 */
@Named(value = "paysController")
@SessionScoped
public class PaysController implements Serializable {

    /**
     * Creates a new instance of PaysController
     */
    public PaysController() {
    }
    
    @EJB
    PaysFacade ejbFacade;
    
    private List<Pays> items = null;
    private Pays selected;

    public List<Pays> getItems() {
        if(items == null || items.isEmpty())
            items = ejbFacade.getAllPays();
        return items;
    }

    public void setItems(List<Pays> items) {
        this.items = items;
    }

    public Pays getSelected() {
        if(selected == null)
            selected = new Pays();
        return selected;
    }

    public void setSelected(Pays selected) {
        this.selected = selected;
    }
    
}
