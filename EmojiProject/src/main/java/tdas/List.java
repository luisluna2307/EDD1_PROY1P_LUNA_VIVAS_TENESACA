/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tdas;

/**
 *
 * @author DELL
 */
public interface List<E> extends Iterable<E> {

    public boolean addFirst(E e); // inserta el elemento e al inicio

    public boolean addLast(E e); // inserta el elemento e al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public int size();// devuelve el numero de elementos de la lista

    public boolean isEmpty();// devuelve si la lista esta llena o no

    public void clear();// remueve todos lois elementos de la lista

    boolean contains(E Element);   // verifica si un lemento se encuentra en esa lista

    boolean add(E Element, int index); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index

    int indexOf(E Element);  // retorna el indice donde se encuentra el elemento    

}
