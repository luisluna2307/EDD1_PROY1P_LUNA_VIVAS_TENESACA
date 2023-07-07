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
 * @author DELL
 */
public class Eye {
    String eye;

    public Eye(String eye) {
        this.eye = eye;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }
    public static CDoublyLinkedList<Eye> loadEyes(String path){
        CDoublyLinkedList<Eye> eyes = new CDoublyLinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/informacionPredefinida/pathEyes.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                Eye eye = new Eye(sCurrentLine);
                eyes.add(eye);
                //System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(eyes);
        return eyes;
    }
}
