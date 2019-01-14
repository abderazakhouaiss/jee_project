/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Assets;
import bean.Propriete;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import service.AssetsFacade;
import service.ProprieteAssetsFacade;
import service.ProprieteFacade;

/**
 *
 * @author asus-pc
 */
@Named(value = "proprieteController")
@SessionScoped
public class ProprieteController implements Serializable {

    /**
     * Creates a new instance of ProprieteController
     */
    public ProprieteController() {
    }
    private Propriete selected;
    private List<Propriete> items;
    private String laVille,lePays;
    @EJB
    private ProprieteFacade ejbFacade;
    @EJB
    private AssetsFacade assetsFacade;
    @EJB
    private ProprieteAssetsFacade proprieteAssetsFacade;
    private UploadedFile image1;
    private UploadedFile image2;
    private UploadedFile image3;
    private UploadedFile image4;

    public UploadedFile getImage1() {
        return image1;
    }

    public void setImage1(UploadedFile image1) {
        this.image1 = image1;
    }

    public UploadedFile getImage2() {
        return image2;
    }

    public void setImage2(UploadedFile image2) {
        this.image2 = image2;
    }

    public UploadedFile getImage3() {
        return image3;
    }

    public void setImage3(UploadedFile image3) {
        this.image3 = image3;
    }

    public UploadedFile getImage4() {
        return image4;
    }

    public void setImage4(UploadedFile image4) {
        this.image4 = image4;
    }
    

    public String getLaVille() {
        return laVille;
    }

    public void setLaVille(String laVille) {
        this.laVille = laVille;
    }

    public String getLePays() {
        return lePays;
    }

    public void setLePays(String lePays) {
        this.lePays = lePays;
    }
    
    
    
    public Propriete getSelected() {
        if(selected == null)
            selected = new Propriete();
        return selected;
    }

    public void setSelected(Propriete selected) {
        this.selected = selected;
    }

    public List<Propriete> getItems() {
        return items;
    }

    public void setItems(List<Propriete> items) {
        this.items = items;
    }
    
    public void add(){
        Propriete p= ejbFacade.createProperty(selected,lePays,laVille);
        Assets a1 = assetsFacade.createPropertAsset(image1, 1);
        Assets a2 = assetsFacade.createPropertAsset(image2, 2);
        Assets a3 = assetsFacade.createPropertAsset(image3, 3);
        Assets a4 = assetsFacade.createPropertAsset(image4, 4);
        proprieteAssetsFacade.createPA(p, a1);
        proprieteAssetsFacade.createPA(p, a2);
        proprieteAssetsFacade.createPA(p, a3);
        proprieteAssetsFacade.createPA(p, a4);
        selected = new Propriete();
    }
    
    public List<Propriete> getNVP(){
        return ejbFacade.getNoValidatedProperties();
    }
    
    public void valider(Propriete propriete) throws IOException{
        int res = ejbFacade.validate(propriete);
        if(res == 1)
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administration/ValidatePropriete.xhtml");
    }
}
