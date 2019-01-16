package collections;


public class GenericNode<E> {

    public GenericNode<E> next = null;
    private E contents;

    /**
     * Get the contents of a node
     * 
     * @return
     */
    public E getContents() {
        return contents;
    }

    /**
     * Set the contents of a node
     * 
     * @param c the value to be set
     */
    public void  setContents(E c) {
        contents = c;
    }

}
