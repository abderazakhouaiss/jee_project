/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.FetchProfile.Item;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Chaimaa-abd
 */
public class ServerConfig {

    private static Item item;
    public static void upload(UploadedFile uploadedFile, String destinationPath, String nameOfUploadeFile) {
        try {
            String fileSavePath = destinationPath + "\\" + nameOfUploadeFile;
            System.out.println("ha file save path :::" + fileSavePath);
            InputStream fileContent = null;
            fileContent = uploadedFile.getInputstream();
            System.out.println("ha fileContent " + fileContent.toString());
            System.out.println("ha faile save path " + new File(fileSavePath).toPath().toString());
            Files.copy(fileContent, new File(fileSavePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("EX1: "+e.getMessage());
        } catch (Exception ex) {
            //Logger.getLogger(ServerConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("EX2: "+ex.getMessage());
        }
    }

}
