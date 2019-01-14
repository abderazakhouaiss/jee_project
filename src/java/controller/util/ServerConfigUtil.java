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
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.mail.FetchProfile.Item;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Chaimaa-abd
 */
public class ServerConfigUtil {

    private static String rootPath = "/Users/mac/NetBeansProjects/FSG_WebSite/web/resources/images/UploadedImages";
    private static String departPath = "pfe.files.path";
    public static String path1 = "/Users/HP/Documents/NetBeansProjects/Pfe/web/resources/pdfCycle";
    public static String pdfPath = "C:\\Users\\ouss\\Documents\\NetBeansProjects\\stockfstg\\web\\resources\\pdfCycle";
    private static List<Item> articlePaths = new ArrayList();
    public static String getArticleFilePath() {
        return rootPath;

    }

    public static String getDepartFilePath() {
        return departPath;
    }

    public static String uploadPdf(UploadedFile uploadedFile, String type, String uploadedFileName) {
        try {
            String nameOfUploadedFile = uploadedFile.getFileName();
            File file = new File(nameOfUploadedFile.replace('\\', '/'));
            System.out.println(nameOfUploadedFile);
//            String fileSavePath = destinationPath + "\\" + nameOfUploadedFile;
           // String fileName = uploadedFileName + "." + FilenameUtils.getExtension(nameOfUploadedFile);
            String fileSavePath = pdfPath + "\\"+ nameOfUploadedFile;
            System.out.println(fileSavePath);
            InputStream fileContent = uploadedFile.getInputstream();
            Path path = new File(fileSavePath).toPath();
            System.out.println("ha lpath azin" +path);
            Files.copy(fileContent, path, StandardCopyOption.REPLACE_EXISTING);
            return "/resources/pdfCycle/" + nameOfUploadedFile;
        } catch (IOException e) {
            System.out.println("----- Mat uploada walo ==> No update==");
            JsfUtil.addErrorMessage(e, "Erreur Upload");
            return "";
        }

    }

    public static void upload(UploadedFile uploadedFile, String destinationPath) {
        try {
            String nameOfUploadedFile = uploadedFile.getFileName();
            String fileSavePath = destinationPath + "\\" + nameOfUploadedFile;
            System.out.println("ha file savePath*****" + fileSavePath);
            System.out.println("ha faile save path " + new File(fileSavePath).toPath().toString());
            InputStream fileContent = uploadedFile.getInputstream();
            Path path = new File(fileSavePath).toPath();
            System.out.println(path);
            Files.copy(fileContent, path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println("------- error upload image== No update==");
            JsfUtil.addErrorMessage(e, "Erreur Upload image");

        }

    }

}
