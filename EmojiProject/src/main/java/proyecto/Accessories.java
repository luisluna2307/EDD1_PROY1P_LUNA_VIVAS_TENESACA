/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tdas.CDoublyLinkedList;

/**
 *
 * @author ASUS
 */
public class Accessories {
    private String path;

    public Accessories(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public static CDoublyLinkedList<Accessories> loadAccessories(String path) {
        CDoublyLinkedList<Accessories> accessories = new CDoublyLinkedList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("src/main/resources/informacionPredefinida/pathAccessories.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                Accessories accessorie = new Accessories(sCurrentLine);
                accessories.addLast(accessorie);
                //System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(eyes);
        return accessories;
    }
}
