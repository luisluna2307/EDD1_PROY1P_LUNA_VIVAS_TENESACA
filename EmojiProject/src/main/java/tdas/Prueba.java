/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

/**
 *
 * @author ASUS
 */
public class Prueba {

    public static void main(String[] args) {
        CDoublyLinkedList<Integer> lista = new CDoublyLinkedList<>();
        lista.add(18);
        lista.add(4);
        lista.add(5);
        lista.add(2);
        lista.add(1);
        lista.add(23);
        int indice = lista.getIndex(23);
        // System.out.println("indice"+indice);
        // System.out.println(lista.getHead().getContent());
        LinkedList<Integer> lista2 = new LinkedList<>();
        lista2.addLast(3);
        lista2.addLast(4);
        lista2.addLast(5);
        //System.out.println("lista:"+lista.toString());
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i) + " ..");

        }
        System.out.println(lista.getNode(0).getPrevious().getContent().toString());
//       for (int i = 0; i <3 ; i++) {
//           int j= lista2.get(i);
////           System.out.println(lista2.toString());
//           lista2.set(i, lista.getNode(lista.getIndex(j)).getNext().getContent());
//           System.out.println(lista.getNode(lista.getIndex(j)).getPrevious().getContent()+"  ..");
////           System.out.println(lista2.get(i));
////           System.out.println(lista2.toString());
//
//       }

//       for (int i = 0; i < lista.size(); i++) {
//           System.out.println(lista.get(i));
//           
//       }
    }

}
