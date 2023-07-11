/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

import java.util.Stack;

/**
 *
 * @author DELL
 */
public class CDoublyLinkedList<E> {

    protected DoublyNodeList<E> first;		 //the first node of the list
    protected DoublyNodeList<E> last;		 //the last node of the list
    protected DoublyNodeList<E> location;	 // true if element found, else false
    protected int numElements;		 // Number of elements in this list
    protected boolean found;         // true if element found, else false

    public int getIndex(E element) {
        int i;
        DoublyNodeList<E> node = this.getFirst();
        for (int j = 0; j < size(); j++) {
            if (node.getContent().equals(element)) {
                return j;
            } else {
                node = node.getNext();
            }
        }
        return 0;
    }

    public DoublyNodeList<E> getFirst() {
        return first;
    }

    public void setHead(DoublyNodeList<E> head) {
        this.first = head;
    }

    public DoublyNodeList<E> getTail() {
        return last;
    }

    public void setTail(DoublyNodeList<E> tail) {
        this.last = tail;
    }

    public DoublyNodeList<E> getLocation() {
        return location;
    }

    public void setLocation(DoublyNodeList<E> location) {
        this.location = location;
    }

    public int getNumElements() {
        return numElements;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public CDoublyLinkedList() //default constructor
    {
        first = null;
        last = null;
        last = first;
        numElements = 0;
        location = null;
    }

    public boolean isEmpty() //true if list is empty, else false
    {
        return (first == null);
    }

    public int size() // Determines the number of elements on this list
    {
        return numElements;
    }

    protected void find(E target) // Searches list for an occurrence of an element. If successful, sets instance variables
    // found to true, location to node containing the element, and previous
    // to the node that links to location. If not successful, sets found to false.
    {
        location = first;
        found = false;
        if (!isEmpty()) {
            do {
                if (location.getContent().equals(target)) // if they match
                {
                    found = true;
                    return;
                } else {
                    location = location.getNext();
                }
            } while (location != last.getNext());
        }

    }

    public DoublyNodeList<E> getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        DoublyNodeList<E> current = this.getFirst();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

//    public DoublyNodeList<E> getPreviousNode(int index) {
//        if (index < 0 || index >= size()) {
//            throw new IndexOutOfBoundsException("Invalid index.");
//        }
//        DoublyNodeList<E> current = this.getFirst();
//        for (int i = 0; i < index; i++) {
//            current = current.getNext();
//        }
//        return current.getPrevious();
//    }
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        DoublyNodeList<E> current = getNode(index);
        return current.getContent();
    }

    public boolean contains(E element) // Returns true if this list contains an element e such that 
    // e.equals(element), otherwise returns false.
    {
        find(element);
        return found;
    }

    protected void findPosition(int position) //finds position in this list
    // Assumes Zero based indexing 
    {
        int start = 0;
        location = first;
        found = false;

        if ((!isEmpty()) && (position >= 0) && (position <= size())) {
            do {
                // move search to the next node
                location = location.getNext();
                start++;

            } while ((location != first) && start < position);
            found = true;
        }

    }

    public E get(E data) // Returns an element e from this list such that e.equals(element);
    // if no such element exists, returns null.
    {
        find(data);
        if (found) {
            return location.getContent();
        } else {
            return null;
        }
    }
//        public E getByIndex(int i){
//            return E;
//        }

    public void reset() // Initializes current position for an iteration through this list,
    // to the first element on this list.
    {
        location = first;
    }

    public void add(E data) // Adds element to this list.
    {
        DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);   // Reference to the new node being added

        if (isEmpty()) // Adding into an empty list
        {
            first = newNode;
            last = newNode;
            first.setPrevious(last);
            last.setNext(first);
        } else // Adding into a non-empty list
        {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
            last.setNext(first);
        }
        numElements++;
    }

    public void addFirst(E element) //adds new element to the front of the list
    {
        DoublyNodeList<E> newNode = new DoublyNodeList<E>(element);   // Reference to the new node being added

        if (isEmpty()) // Adding into an empty list
        {
            first = newNode;
            last = newNode;
            first.setPrevious(last);
            last.setNext(first);
        } else // Adding into a non-empty list
        {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
            first.setPrevious(last);
            last.setNext(first);
        }
        numElements++;

    }

    public void addLast(E data) //adds new element to the back of the list
    {
        DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);   // Reference to the new node being added

        if (isEmpty()) // Adding into an empty list
        {
            first = newNode;
            last = newNode;
            first.setPrevious(last);
            last.setNext(first);
        } else // Adding into a non-empty list
        {
            last.setNext(newNode);
            newNode.setPrevious(this.last);
            last = newNode;
            last.setNext(first);
            first.setPrevious(last);
        }
        numElements++;

    }

    public void addAtPosition(E data, int position) //adds new element to the specified position
    {
        DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);

        if (isEmpty()) {
            // add element to an empty list
            first = newNode;
            last = newNode;
            first.setPrevious(last);
            last.setNext(first);

        } else if (position <= 0) {
            // insert at front of the list
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
            first.setPrevious(last);
            last.setNext(first);

        } else if (position >= size()) {
            // if position does not exist, perform add at the most efficient
            // position for circular doubly linked list, the most efficient position is
            // at the end.		
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
            last.setNext(first);

        } else {
            /* General Case */
            // determine location where to perform insert
            findPosition(position);

            //inserts the elements to the specified position
            newNode.setPrevious(location.getPrevious());
            newNode.setNext(location);
            location.getPrevious().setNext(newNode);
            location.setPrevious(newNode);

        }
        numElements++;
    }

    public boolean remove(E element) // Removes an element e from this list such that e.equals(element)
    // and returns true; if no such element exists, returns false.
    {
        find(element);
        if (found) {
            if (location == first && size() == 1) //removes the only existing element
            //empties the list
            {
                first = null;
                last = null;

            } else if (location == first) //removes first node
            {
                first = first.getNext();
                first.setPrevious(last);
                last.setNext(first);

            } else if (location == last) //removes last node
            {

                last = last.getPrevious();
                last.setNext(first);
                first.setPrevious(last);
            } else {						 // removes node at location
                location.getPrevious().setNext(location.getNext());
                location.getNext().setPrevious(location.getPrevious());
            }
            numElements--;
        }
        return found;
    }

    public void removeFirst() //removes the first element in the list
    {

        if (!isEmpty()) {

            if (first.getNext() == first) {  //if the first element is the only element in the list,	        		             //it empties the list
                first = null;
                last = null;
            } else {			//removes the first element
                first = first.getNext();
                first.setPrevious(last);
                last.setNext(first);
            }
        }
        numElements--;
    }

    public void removeLast() //removes the last element in this list
    {

        if (!isEmpty()) {

            if (first.getNext() == first) { //if the last element is the only element in the list,
                //it empties the list      	
                first = null;
                last = null;
            } else {				//removes the last element
                last = last.getPrevious();
                last.setNext(first);
                first.setPrevious(last);
            }
        }
        numElements--;
    }

    public void removeAtPosition(int position) //removes the element in the specified position
    {
        if (position <= 0) {		//removes front element
            first = first.getNext();
            first.setPrevious(last);
            last.setNext(first);
        } else if (position >= size() - 1) { //remove last element

            last = last.getPrevious();
            last.setNext(first);
            first.setPrevious(last);

        } else {
            //general case
            //determines the position
            findPosition(position);

            //removes the element in the specified position
            location.getPrevious().setNext(location.getNext());
            location.getNext().setPrevious(location.getPrevious());
        }
        numElements--;
    }

    public String toString() // prints the elements of the list in a nicely formated manner in forward order
    {
        String item = "List: [ ";

        DoublyNodeList<E> current = first;

        if (!isEmpty()) {

            do {
                item += current.getContent() + " ";
                current = current.getNext();
            } while (current != first);

        }
        item += "]";
        return item;

    }

    public E next(int index) {
        DoublyNodeList<E> node = getNode(index);
        return node.getNext().getContent();
    }

    public String printReverse() // prints the elements of the list in a nicely formated manner in reverse order
    {

        String item = "List: [ ";

        DoublyNodeList<E> current = last;

        if (!isEmpty()) {
            do {
                item += current.getContent() + " ";
                current = current.getPrevious();
            } while (current != last);
        }
        item += "]";
        return item;

    }

    public CDoublyLinkedList<E> getTen(LinkedList<E> actual) {
        CDoublyLinkedList<E> nueva = new CDoublyLinkedList<>();
        int pos;
        int nums = 10;
        Stack<Integer> numsRandoms = new Stack<Integer>();
        for (int i = 0; i < nums; i++) {
            pos = (int) Math.floor(Math.random() * actual.size());
            while (numsRandoms.contains(pos)) {
                pos = (int) Math.floor(Math.random() * actual.size());
            }
            numsRandoms.push(pos);
        }

        for (Integer i : numsRandoms) {
            nueva.addLast(actual.get(i));
        }
        return nueva;

    }

}
