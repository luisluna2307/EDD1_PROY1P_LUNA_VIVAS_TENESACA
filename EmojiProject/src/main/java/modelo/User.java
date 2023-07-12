/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controladores.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tdas.ArrayList;

/**
 *
 * @author DELL
 */
public class User {

    private String user;
    private String password;
    private String proyectos;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProyectos() {
        return proyectos;
    }

    public void setProyectos(String proyectos) {
        this.proyectos = proyectos;
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> cuentas = new ArrayList();
        try ( BufferedReader br = new BufferedReader(new FileReader(App.filePathUsers + "users.txt"))) {
            String linea = br.readLine();
            while (linea != null) {
                String[] cuenta = linea.strip().split(",");
                User u = new User(String.valueOf(cuenta[0].strip()), String.valueOf(cuenta[1].strip()));
                cuentas.addLast(u);
                linea = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("error 1");
        }
        return cuentas;
    }

    public static boolean verifyUser(ArrayList<User> userList, String user, String pass) {
        boolean found = false;
        for (User u : userList) {
            if (u.getUser().equals(user) && u.getPassword().equals(pass)) {
                found = true;
            }
        }
        return found;
    }

    public static User returnUser(ArrayList<User> userList, String user, String pass) {
        User usuario = null;
        for (User u : userList) {
            if (u.getUser().equals(user) && u.getPassword().equals(pass)) {
                usuario = u;
            }
        }
        return usuario;
    }

}
