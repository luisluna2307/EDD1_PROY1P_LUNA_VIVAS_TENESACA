/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

import java.util.Iterator;

/**
 *
 * @author DELL
 */
public class ArrayList<E> implements List<E> {

    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }

    private boolean isFull() {
        return elements.length == effectiveSize;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }

    @Override
    public String toString() {
        String result = "[";
        if (isEmpty()) {
            return result + "]";
        } else {
            for (int i = 0; i < effectiveSize - 1; i++) {
                result += elements[i].toString() + ", ";
            }
            result += elements[effectiveSize - 1].toString() + "]";
            return result;
        }
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    /* EN ESTE TALLER, USTED DEBE IMPLEMENTAR LOS SIGUIENTES MÉTODOS */
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        int index = effectiveSize;
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(this.effectiveSize - 1);
    }

    @Override
    public boolean add(E element, int index) {
        if (element == null) {
            return false;
        } else if (index < 0 || index > this.effectiveSize) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        effectiveSize++;
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];

        }
        elements[index] = element;
        return true;

    }

    @Override
    public E remove(int index) {
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.effectiveSize || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.effectiveSize--;
        }
        return elementToRemove;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    public boolean swap(int pos1, int pos2) {

        if (isEmpty()) {
            return false;
        }

        if (pos1 > effectiveSize || pos2 > effectiveSize) {
            return false;
        }

        E elemento1 = this.get(pos1);

        E elemento2 = this.get(pos2);

        E temp = elemento2;

        this.set(pos2, elemento1);

        this.set(pos1, temp);

        return true;

    }

    public boolean reverse() {

        if (this.isEmpty()) {
            return false;
        }

        ArrayList<E> aux = new ArrayList<E>();

        for (int i = this.effectiveSize - 1; i > -1; i--) {
            aux.addLast(this.elements[i]);
        }

        for (int i = 0; i < aux.effectiveSize; i++) {
            this.elements[i] = aux.get(i);
        }

        return true;

    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            return -2;
        }
        if (isEmpty()) {
            return -1;
        }
        if (e == elements[effectiveSize]) {
            return size() - 1;
        }
        int index = 0;
        if (!isEmpty()) {
            for (int i = 0; i < effectiveSize; i++) {
                if (elements[i].equals(e)) {
                    index = i;
                    return i;
                }
            }
        }
        return -2;
    }

    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return null;
        }
        return new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E element = elements[cursor];
                    cursor++;
                    return element;
                }
                return null;
            }

        };

    }

}
