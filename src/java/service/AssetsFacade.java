/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Assets;
import config.ApplicationConfig;
import controller.util.ServerConfig;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author asus-pc
 */
@Stateless
public class AssetsFacade extends AbstractFacade<Assets> {

    @PersistenceContext(unitName = "ReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetsFacade() {
        super(Assets.class);
    }
    
    public Assets createAsset(UploadedFile file){
        if (file != null) {
            ServerConfig.upload(file, ApplicationConfig.LIEN_ABSOLU, ApplicationConfig.FILE_NAME+ApplicationConfig.FILE_TYPE);
            Assets assets = new Assets();
            assets.setUrl(ApplicationConfig.SAVE_DIRECTORY + ApplicationConfig.FILE_NAME+ApplicationConfig.FILE_TYPE);
            assets.setId(generateId());
            create(assets);
            return assets;
        }
        return null;
    }
    
    public Assets createPropertAsset(UploadedFile file,int i){
        System.out.println(file.getFileName());
        if (file.getFileName() != null && !file.getFileName().isEmpty()) {
            ServerConfig.upload(file, ApplicationConfig.LIEN_ABSOLU, ApplicationConfig.FILE_NAME+"_"+i+ApplicationConfig.FILE_TYPE);
            Assets assets = new Assets();
            assets.setUrl(ApplicationConfig.SAVE_DIRECTORY + ApplicationConfig.FILE_NAME+"_"+i+ApplicationConfig.FILE_TYPE);
            assets.setId(generateId());
            create(assets);
            return assets;
        }
        return null;
    }

    private Long generateId() {
        return generateId("Assets", "id");
    }
}
