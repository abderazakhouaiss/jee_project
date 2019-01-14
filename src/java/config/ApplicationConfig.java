/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author asus-pc
 */
public class ApplicationConfig {

    public static final String LIEN_ABSOLU;
    public static final String FILE_TYPE = ".jpg";
    public static final String FILE_NAME = String.valueOf(new Date().getTime());
    public static final String SAVE_DIRECTORY ;

    static {
        SAVE_DIRECTORY = "/assets/";
        LIEN_ABSOLU = FacesContext.getCurrentInstance().getExternalContext().getRealPath(SAVE_DIRECTORY);
    }
}
