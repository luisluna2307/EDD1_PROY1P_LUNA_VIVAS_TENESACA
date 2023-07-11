/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tdas.ArrayList;
import tdas.CDoublyLinkedList;

/**
 *
 * @author DELL
 */
public class Face {

    String path;

    public Face(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static CDoublyLinkedList<Face> loadFaces(String path) {
        CDoublyLinkedList<Face> faces = new CDoublyLinkedList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("src/main/resources/informacionPredefinida/pathFaces.txt"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                Face face = new Face(sCurrentLine);
                faces.addLast(face);
                // System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(faces);
        return faces;
    }

    @Override
    public String toString() {
        return "Face{" + "path=" + path + '}';
    }

}
