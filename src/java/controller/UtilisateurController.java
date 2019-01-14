package controller;

import bean.Assets;
import bean.Propriete;
import bean.Reservation;
import bean.Utilisateur;
import controller.util.SessionUtil;
import java.io.IOException;
import service.UtilisateurFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;
import service.AssetsFacade;
import service.ProprieteFacade;
import service.ReservationFacade;

@Named("utilisateurController")
@SessionScoped
public class UtilisateurController implements Serializable {

    @EJB
    private service.UtilisateurFacade ejbFacade;
    @EJB
    private AssetsFacade assetsFacade;
    @EJB
    private ReservationFacade reservationFacade;
    @EJB
    private ProprieteFacade proprieteFacade;
    private List<Utilisateur> items = null;
    private Utilisateur selected, utilisateurInscre;
    private String messageErreur = "";
    private String ville, payss;
    private UploadedFile file;
    private Propriete propriete;
    public UploadedFile getFile() {
        return file;
    }

    public List<Reservation> getReservations() {
        return reservationFacade.getReservationByUser(utilisateurInscre,true);
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }
    
    public List<Propriete> getProprietes(){
        return proprieteFacade.getProprieteByUser(utilisateurInscre, true);
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPayss() {
        return payss;
    }

    public void setPayss(String payss) {
        this.payss = payss;
    }

    public Utilisateur getUtilisateurInscre() {
        if (utilisateurInscre == null) {
            utilisateurInscre = (Utilisateur) SessionUtil.getConnectedUser();
        }
        return utilisateurInscre;
    }

    public void setUtilisateurInscre(Utilisateur utilisateurInscre) {
        this.utilisateurInscre = utilisateurInscre;
    }

    public List<Utilisateur> getItems() {
        return items;
    }

    public void setItems(List<Utilisateur> items) {
        this.items = items;
    }

    public UtilisateurController() {
    }

    public String getMessageErreur() {
        return messageErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public Utilisateur getSelected() {
        if (selected == null) {
            selected = new Utilisateur();
        }
        return selected;
    }

    public void setSelected(Utilisateur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UtilisateurFacade getFacade() {
        return ejbFacade;
    }

    public Utilisateur prepareCreate() {
        selected = new Utilisateur();
        initializeEmbeddableKey();
        return selected;
    }

    public Utilisateur getUtilisateur(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Utilisateur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Utilisateur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void create() throws IOException {
        int result = ejbFacade.createUtilisateur(selected);
        if (result == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../profile/Profile.xhtml");
//            JsfUtil.rediredct("../profile/Profile.xhtml");
        } else {
            messageErreur = "Tous les champs sont requierts";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur:", messageErreur));
        }
        selected = new Utilisateur();
    }

    public void edit() {
        Assets as = assetsFacade.createAsset(file);
        ejbFacade.editerUtilisateur(utilisateurInscre,as, payss, ville);
    }

    public void seConnecter() throws IOException {
        int resultat = ejbFacade.seConnnecter(selected);
        FacesContext context = FacesContext.getCurrentInstance();
        switch (resultat) {
            case -5:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tous les champs sont requierts"));
                break;
            case -4:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email incorrect."));
                break;
            case -3:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Mot de passe incorrect."));
                break;
            case -2:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Votre compte est désactivé."));
                break;
            case 1:
                FacesContext.getCurrentInstance().getExternalContext().redirect("../profile/Profile.xhtml");
                break;
        }
    }
    public String getAssetUrl(){
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return origRequest.getContextPath() + utilisateurInscre.getAsset().getUrl();
    }
    public String formatDate(Date date){
        SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
        return sm.format(date);
    }
    public void checkLogin() throws IOException{
        if(SessionUtil.getConnectedUser() == null)
            FacesContext.getCurrentInstance().getExternalContext().redirect("../connexion/Connexion.xhtml");
    }
    
    public void logOut() throws IOException{
        SessionUtil.deconnect("user");
        utilisateurInscre = null;
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../connexion/Connexion.xhtml");
    }
}
