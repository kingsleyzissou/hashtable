package collections;

import java.util.Iterator;

public class GenericList<E> implements Iterable<E>{

    public GenericNode<E> head;

    public GenericList() {
    	this.head = null;
    }

    /**
     * Insert method for generic LinkList 
     * 
     * @param e element to be added
     * @return true if inserted
     */
    public boolean insert(E e) {
        if(contains(e)) return false;
        GenericNode<E> node = new GenericNode<>();
        node.setContents(e);
        node.next  = head;
        head = node;
        return true;
    }

    /**
     * Check if the LinkList contains the element
     * 
     * @param e element to be checked
     * @return true if it is in the LinkList
     */
    public boolean contains(E e) {
        for(E node : this) {
            if(node.equals(e)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Remove the element from the LinkList
     * 
     * @param e element to be removed
     * @return true if it has been removed
     */
    public boolean remove(E e) {
        if(head == null) return false;
        if(head.getContents().equals(e)) {
            head = head.next;
            return true;
        }

        GenericNode<E> current = head;
        GenericNode<E> next;

        while(current.next != null) {
            next = current.next;
            if(next.getContents().equals(e)) {
                current.next = next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Get the contents of a particular node
     * 
     * @param contents the contents being searched for
     * @return the contents of the node
     */
    public Object getContents(E contents) {

        GenericNode<E> getElementContents = head;
        while(getElementContents != null && getElementContents.getContents() != contents)
            getElementContents = getElementContents.next;
        if(getElementContents != null) {
            return getElementContents.getContents();
        }
        return getElementContents;

    }

    /**
     * Get the number of elements in the LinkList
     * 
     * @return number of elements
     */
    public int size() {
        int i = 0;
        for(E ignored : this)
            i++;
        return i;
    }

    /**
     * Merge two LinkLists together
     * 
     * @param listToMerge
     */
    public void merge(GenericList<E> listToMerge) {
        for(E item : listToMerge) {
            this.insert(item);
        }
    }

    /**
     * Return the CustomeIterator for the LinkList
     * 
     */
    public Iterator<E> iterator() {

        return new GenericIterator(head) ;
    }

    /**
     * @GenericIterator
     *
     * nested iterator class in the linked list
     *
     *
     *
     */
    public class GenericIterator implements Iterator<E> {

        private GenericNode<E> pos;

        public GenericIterator(GenericNode<E> node) {
            pos = node;
        }

        /**
         * Check if there is another element in the list
         * 
         */
        @Override
        public boolean hasNext() {

            return pos != null;
        }

        /**
         * Get the contents of the next element in the list
         * 
         */
        @Override
        public E next() {

            GenericNode<E> temp = pos;
            pos = pos.next;
            return temp.getContents();
        }

    }


}
