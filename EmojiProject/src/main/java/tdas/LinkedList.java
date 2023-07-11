package tdas;

import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    private NodeList<E> first;
    private NodeList<E> last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        nuevo.setNext(this.getFirst());
        if (this.isEmpty()) {
            this.setLast(nuevo);
        }
        this.setFirst(nuevo);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        if (this.isEmpty()) {
            this.setFirst(nuevo);
        } else {
            this.getLast().setNext(nuevo);
        }

        this.setLast(nuevo);

        return true;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            if (size() == 1) {
                last = null;
            }
            NodeList<E> node = first;
            first = first.getNext();
            node.setNext(null);
            return node.getContent();
        }
        return null;
    }

    @Override
    public E removeLast() {

        if (!isEmpty()) {
            NodeList<E> deleted = last;
            if (first == last) {
                return removeFirst();
            }
            if (first.getNext() == last) {

                last = first;
                first.setNext(null);
                return deleted.getContent();
            }
            NodeList<E> n;
            for (n = first; n.getNext().getNext() != last; n = n.getNext()) {
            }
            last = n.getNext();
            n.getNext().setNext(null);
            return deleted.getContent();
        }
        return null;
    }

    @Override
    public int size() {
        int cont = 0;
        if (!isEmpty()) {
            NodeList<E> t;
            for (t = this.getFirst(); t != null; t = t.getNext()) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    @Override
    public boolean add(E element, int index) {
        int size = size();
        if (element != null && index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(element);
                return true;
            } else if (index == size) {
                addLast(element);
                return true;
            } else {
                NodeList<E> n = first;
                for (int i = 0; i < index - 1; i++) {
                    n = n.getNext();
                }
                NodeList<E> newNode = new NodeList(element);
                newNode.setNext(n.getNext());
                n.setNext(newNode);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            if (index == 0) {
                return removeFirst();
            } else if (index == size - 1) {
                return removeLast();
            } else {
                NodeList<E> n = first;

                for (int i = 0; i < index - 1; i++) {
                    n = n.getNext();
                }
                NodeList<E> deleted = n.getNext();
                n.setNext(n.getNext().getNext());
                deleted.setNext(null);
                return deleted.getContent();
            }
        }
        return null;
    }

    @Override
    public E get(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n.getContent();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            E old = n.getContent();
            n.setContent(element);
            return old;
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            s += t.getContent() + " ";
        }
        return s;
    }

    public boolean swap(int pos1, int pos2) {

        if (isEmpty()) {
            return false;
        }

        if (first == null || last == null) {
            return false;
        }

        if (pos1 > this.size() || pos2 > this.size()) {
            return false;
        }

        NodeList<E> nodeTmp = new NodeList<>(this.get(pos1));

        NodeList<E> nodeTmp2 = new NodeList<>(this.get(pos2));

        if (pos1 == 0) {
            nodeTmp2.setNext(first.getNext());
            first = nodeTmp2;
        }

        if (pos2 == 0) {
            nodeTmp.setNext(first.getNext());
            first = nodeTmp;
        }

        NodeList<E> n = first;

        for (int i = 0; i < this.size(); i++) {

            if (i + 1 == pos1 && (n.getNext() != null)) {
                nodeTmp2.setNext(n.getNext().getNext());
                n.setNext(nodeTmp2);
            }
            if (i + 1 == pos2 && (n.getNext() != null)) {
                nodeTmp.setNext(n.getNext().getNext());
                n.setNext(nodeTmp);
            }
            n = n.getNext();

        }

        return true;
    }

    public boolean reverse() {
        if (this.isEmpty()) {
            return false;
        }

        NodeList<E> current = first;
        NodeList<E> previo = null;
        NodeList<E> siguiente = null;

        while (current != null) {
            siguiente = current.getNext();
            current.setNext(previo);
            previo = current;
            current = siguiente;
        }
        first = previo;

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return null;
        }
        return new Iterator<E>() {

            NodeList<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = node.getContent();
                    node = node.getNext();
                    return data;
                }
                return null;
            }

        };
    }

    @Override
    public boolean contains(E e) {
        if (isEmpty()) {
            return false;
        }
        NodeList<E> node = first;
        while (node != null) {
            if (e.equals(node.getContent())) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            return -2;
        }
        if (isEmpty()) {
            return -1;
        }
        if (e == last.getContent()) {
            return size() - 1;
        }

        NodeList<E> node = first;
        int index = 0;
        while (node != last) {
            if (node.getContent() == e) {
                return index;
            }
            index++;
            node = node.getNext();
        }

        return -2;
    }

    /* Metodo reverse Iterator */
    public Iterator<E> reverseIterator() {
        if (this.isEmpty()) {
            return null;
        }

        Iterator<E> itAux = this.iterator();
        LinkedList<E> lAux = new LinkedList<>();

        while (itAux.hasNext()) {
            lAux.addFirst(itAux.next());
        }

        return new Iterator<E>() {
            NodeList<E> node = lAux.first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = node.getContent();
                    node = node.getNext();
                    return data;
                }
                return null;
            }
        };
    }

}
