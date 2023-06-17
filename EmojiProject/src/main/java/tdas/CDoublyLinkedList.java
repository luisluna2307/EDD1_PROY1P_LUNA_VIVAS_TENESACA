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
    protected DoublyNodeList<E> head;		 //the first node of the list
	protected DoublyNodeList<E> tail;		 //the last node of the list
	protected DoublyNodeList<E> location;	 // true if element found, else false
	protected int numElements;		 // Number of elements in this list
	protected boolean found;         // true if element found, else false
    
        
    public int getIndex(E element){
        int i;
        location = head;
        found = false;
        for(int j=0;j<size();j++){
            if(location.getContent().equals(element)){ 	 
                found = true;
                return j;
            }else{
                location = location.getNext();
            }
        }
        return 0;
    }
    public DoublyNodeList<E> getHead() {
        return head;
    }

    public void setHead(DoublyNodeList<E> head) {
        this.head = head;
    }

    public DoublyNodeList<E> getTail() {
        return tail;
    }

    public void setTail(DoublyNodeList<E> tail) {
        this.tail = tail;
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

	
	public CDoublyLinkedList()
	//default constructor
	{
		head = null;
		tail = null;
		numElements = 0;
		location = null;
	}
	
	public boolean isEmpty()
	//true if list is empty, else false
	{
		return (head == null);
	}
	
	public int size()
	// Determines the number of elements on this list
	{
		return numElements;
	}
	
	protected void find(E target)
    // Searches list for an occurrence of an element. If successful, sets instance variables
    // found to true, location to node containing the element, and previous
    // to the node that links to location. If not successful, sets found to false.
    {
      location = head;
      found = false;
      if(!isEmpty()){ 	  
    	  do 
          {
            if (location.getContent().equals(target))  // if they match
            {
             found = true;
             return;
            }
            else
            {
                location = location.getNext();
            }
          }while(location != tail.getNext());
      }
      
    }
    private DoublyNodeList<E> getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        DoublyNodeList<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        DoublyNodeList<E> current = getNode(index);
        return current.getContent();
    }
    public boolean contains (E element)
	// Returns true if this list contains an element e such that 
	// e.equals(element), otherwise returns false.
	{
		 find(element);
		 return found;
	}
    
	protected void findPosition(int position) 
	//finds position in this list
	// Assumes Zero based indexing 
	{
		int start = 0;
		location = head;
		found = false;
	
		if ((!isEmpty()) && (position >= 0) && (position <= size())) {
			do {
				// move search to the next node
				location = location.getNext();
				start++;

			} while ((location != head) && start < position);
			found = true;
		}

	}
	
	public E get(E data)
	  // Returns an element e from this list such that e.equals(element);
	  // if no such element exists, returns null.
	  {
	    find(data);    
	    if (found)
	      return location.getContent();
	    else
	      return null;
	  }
//        public E getByIndex(int i){
//            return E;
//        }
	
	public void reset()
	  // Initializes current position for an iteration through this list,
	  // to the first element on this list.
	  {
	      location  = head;
	  }
	
	
	public void add(E data)
	// Adds element to this list.
	{
		DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);   // Reference to the new node being added
	   	
	   	 if (isEmpty())            // Adding into an empty list
	   	 {
	   		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);	   	 
	   	 }
	   	 else                      // Adding into a non-empty list
	   	 {
	   		 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);
	   	 }
	   	 numElements++;
	}

    
    public void addFront(E element) 
    //adds new element to the front of the list
    {
    	DoublyNodeList<E> newNode = new DoublyNodeList<E>(element);   // Reference to the new node being added
	   	
    	if (isEmpty())            // Adding into an empty list
	   	 {
    		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);
	   	 }
	   	 else                       // Adding into a non-empty list
	   	 {
	   		newNode.setNext(head);
	   		head.setPrevious(newNode);
	   	    head = newNode;
	   	    head.setPrevious(tail);
	   	    tail.setNext(head);
	   	 }
	   	 numElements++;
	   	
    }
    
    public void addBack(E data)
    //adds new element to the back of the list
    {
    	DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);   // Reference to the new node being added
	   	
	   	 if (isEmpty())            // Adding into an empty list
	   	 {
	   		 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);	   	 
	   	 }
	   	 else                      // Adding into a non-empty list
	   	 {
	   		 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);
	   	 }
	   	 numElements++;
	   	
    }
    
    public void addAtPosition(E data, int position)
    //adds new element to the specified position
    {
   	 DoublyNodeList<E> newNode = new DoublyNodeList<E>(data);

		if (isEmpty()) {
			// add element to an empty list
			 head = newNode;
	   	     tail = newNode;   	    
	   	     head.setPrevious(tail);
	   	     tail.setNext(head);
			
		} else if (position <= 0 ) {
			// insert at front of the list
			newNode.setNext(head);
	   		head.setPrevious(newNode);
	   	    head = newNode;
	   	    head.setPrevious(tail);
	   	    tail.setNext(head);
	   	  
		} else if (position >= size()) {
			// if position does not exist, perform add at the most efficient
			// position for circular doubly linked list, the most efficient position is
			// at the end.		
			 tail.setNext(newNode);
	   		 newNode.setPrevious(tail);
	   	     tail = newNode;
	   	     tail.setNext(head);	

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


	public boolean remove (E element)
    // Removes an element e from this list such that e.equals(element)
    // and returns true; if no such element exists, returns false.
    {
      find(element);
      if (found)
      { 
    	if(location == head && size() == 1) //removes the only existing element
    		                                //empties the list
    	{
    		head = null;
    		tail = null;
    		
    	}else if (location == head)    //removes first node
    	{   		
    		head = head.getNext(); 
            head.setPrevious(tail);
            tail.setNext(head); 
            
        }else if(location == tail)		//removes last node
        {
        	
        	tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail); 
        }
        else{						 // removes node at location
        	location.getPrevious().setNext(location.getNext());  
        	location.getNext().setPrevious(location.getPrevious());  	
        }
        numElements--;
      }
      return found;
    }
	
	 public void removeFront()
	 //removes the first element in the list
	 {
		 
	        if(!isEmpty()){  
	        	
	        	if(head.getNext() == head){  //if the first element is the only element in the list,	        		             //it empties the list
	        		head = null;
	        		tail = null;
	        	}else{			//removes the first element
	              head = head.getNext();
	              head.setPrevious(tail);
	              tail.setNext(head);              
	        	}
	        }
	        numElements--;
	 }
	 
	 public void removeBack()
	 //removes the last element in this list
	 {
		 
	     if(!isEmpty()){
	    	 
	    	 if(head.getNext() == head){ //if the last element is the only element in the list,
	                          //it empties the list      	
	    		 head = null;
	    		 tail = null;
	         }else{				//removes the last element
	            tail = tail.getPrevious();
	            tail.setNext(head);
	            head.setPrevious(tail); 
	         }
	     }
	     numElements--;
	    }
	 
	 public void removeAtPosition(int position)
	 //removes the element in the specified position
	 {
		 if(position <= 0){		//removes front element
			 head = head.getNext();	
			 head.setPrevious(tail);
             tail.setNext(head);   
		 }else if(position >= size() - 1){ //remove last element
			 
			 tail = tail.getPrevious(); 
	         tail.setNext(head);
	         head.setPrevious(tail); 
			 
		 }else{
			   //general case
			   //determines the position
			    findPosition(position);
			    
			    //removes the element in the specified position
				location.getPrevious().setNext(location.getNext());
				location.getNext().setPrevious(location.getPrevious());
		 }
		 numElements--;
	 }
    
    public String toString() 
    // prints the elements of the list in a nicely formated manner in forward order
    {
    	String item = "List: [ ";
        
    		DoublyNodeList<E> current = head;
 
    		if(!isEmpty()){
    			
    			do{
        			item += current.getContent() + " ";
        			current = current.getNext();
        		}while(current != head);
    			
    		}
    		item += "]";
        return item;

    }
    
    public E next(int index){
        DoublyNodeList<E> node = getNode(index);
        return node.getNext().getContent();
    }
            
            
    public String printReverse() 
    // prints the elements of the list in a nicely formated manner in reverse order
    {
    	
    	String item = "List: [ ";
        
    		DoublyNodeList<E> current = tail;
 
    		if(!isEmpty()){
    			do{
        			item += current.getContent   () + " ";
        			current = current.getPrevious();
        		}while(current != tail);
    		}
    		item += "]";
        return item;

    }
    
    public CDoublyLinkedList<E> getTen(LinkedList<E> actual){
        CDoublyLinkedList<E> nueva = new CDoublyLinkedList<>();
        int pos;
        int nums=10;
        Stack<Integer> numsRandoms= new Stack<Integer>();
        for (int i=0;i<nums ;i++){
            pos = (int) Math.floor(Math.random()*actual.size());
            while (numsRandoms.contains(pos)){
                pos= (int) Math.floor(Math.random()*actual.size());
            }
            numsRandoms.push(pos);
        }
        
        for(Integer i:numsRandoms){
            nueva.addBack(actual.get(i));
        }
        return nueva;     
        
    }
    
}
