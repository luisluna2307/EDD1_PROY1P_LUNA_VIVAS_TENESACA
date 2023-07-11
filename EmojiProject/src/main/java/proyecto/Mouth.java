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
public class Mouth {

    String path;

    public Mouth(String mouth) {
        this.path = mouth;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String mouth) {
        this.path = mouth;
    }

    public static CDoublyLinkedList<Mouth> loadMouths(String path) {
        CDoublyLinkedList<Mouth> mouths = new CDoublyLinkedList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("src/main/resources/informacionPredefinida/pathMouths.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                Mouth mouth = new Mouth(sCurrentLine);
                mouths.addLast(mouth);
                //  System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(mouths);
        return mouths;
    }
}
