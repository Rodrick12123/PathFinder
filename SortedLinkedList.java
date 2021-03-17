import java.util.Comparator;
import java.util.ArrayList;
class Node<Country> {
  private Country data;
  private Node<Country> next;
  private Node<Country> prev;


  public Country getData() {
      return data;
  }
  public void setData(Country data) {
      this.data = data;
  }
  public Node<Country> getNext() {
    return next;
  }
  public void setNext(Node<Country> next) {
      this.next = next;
  }
  public Node<Country> getPrev() {
    return prev;
  }
}
public class SortedLinkedList<Country> {

  public int count;
  public Node<Country> head, tail;
  public String cond;
  public boolean sort;
  public Object[] array;


  public SortedLinkedList(String c, boolean TF){
    String cond = c;
    boolean sort = TF;
    CountryComparator n = new CountryComparator(cond, sort);
    count = 0;

  }

  /**
    * Adds item to the list in sorted order.
    */
  public void add(Country item){
    Node<Country> node = new Node<Country>();
    node.setData(item);
    count++;
    if(head == null){
        tail = head = node;         
    }
    else{
        tail.setNext(node);
        tail = node;
    }
  }
  /**
    * Remove the first occurence of targetItem from the list, 
    * shifting everything after it up one position. targetItem
    * is considered to be in the list if an item that is equal
    * to it (using .equals) is in the list.
    * (This convention for something being in the list should be
    * followed throughout.)
    * @return true if the item was in the list, false otherwise
    */
  public boolean remove(Country item){
    Node<Country> tmp = head, rNode = null;
    for(;;){
        if(tmp == null)break;
        if(tmp.getData().equals(item)){
          count--;
          break;
        }
        rNode = tmp;
        tmp = tmp.getNext();
    }
    if(tmp.getData().equals(item)){          
        tmp = tmp.getNext();
        rNode.setNext(tmp); 
        return true;       
    }else{
      return false;
    }
  
    
  }
  /**
    * Remove the item at index position from the list, shifting everything
    *  after it up one position.
    * @return the item, or throw an IndexOutOfBoundsException if the index is out of bounds.
    */
  public Country remove(int idx){

    if (idx == 0) {
      Node<Country> tmp = head;
      head = tmp.getNext();
      count--;      
      return tmp.getData(); 
         
    }
    else if ((idx > count - 1) || (idx < 0)){
      System.out.println("Index out of bounds");
      System.exit(0);
    }else {
      Node<Country> curr = head;
      for (int i = 0; i < idx; i++) {
          curr = curr.getNext();
      }

      Node<Country> temp = curr;
      curr = temp.getPrev();
      curr.setNext(temp.getNext());
      count--;
      return temp.getData();
    }

  }


  /**
    * Returns the first position of targetItem in the list.
    * @return the position of the item, or -1 if targetItem is not in the list
    */
  public int getPosition(Country item){
    Node<Country> tmp = head, rNode = null;
    Node<Country> curr = head;
    int loc = 0;
    for (int i = 0; i < count; i++) {
      curr = curr.getNext();
      if(tmp.getData().equals(item)){
        loc = i;
        return i;
      }
      rNode = tmp;
      tmp = tmp.getNext();
      
    }
    if(tmp.getData().equals(item)){
      return loc;
    }else{
      return -1;
    }
    
  }
  /** 
    * Returns the item at a given index.
    * @return the item, or throw an IndexOutOfBoundsException if the index is out of bounds.
    */
  public Country get(int idx){
    Country item = head.getData();
    if (idx == 0) {
      count--;  
      return head.getData();     
         
    }
    else if ((idx > count - 1) || (idx < 0)) {
      System.out.println("Index out of bounds");
      System.exit(0);
    }else {
      Node<Country> curr = head;
      for (int i = 0; i < idx; i++) {
        curr = curr.getNext();
        item = curr.getData();
      }
      return item;
    }
  }
  /** Returns true if the list contains the target item. */
  public boolean contains(Country item){
    Node<Country> tmp = head;
    for(;;){
        if(tmp == null)break;
        if(tmp.getData().equals(item)){
          return true;
        }
    }

  }
  /** Re-sorts the list according to the given comparator.
    * All future insertions should add in the order specified
    * by this comparator.
    */
  public void resort(String c, boolean TF){
    String cond = c;
    boolean sort = TF;
    CountryComparator n = new CountryComparator(cond, sort);


  }

  /** Returns the length of the list: the number of items stored in it. */
  public int size(){
    return count;
  }
  /** Returns true if the list has no items stored in it. */
  public boolean isEmpty(){
    Node<Country> tmp = head;
    int LC = 0;
    for(int i = 0; i < count; i++){
        if(tmp == null)break;
        if(tmp.getData() == null){
          LC = LC + 1;
        }

    }
    if(LC == size()){
      return true;
    }else{
      return false;
    }

  }
  /** Returns an array version of the list.  Note that, for technical reasons,
    * the type of the items contained in the list can't be communicated
    * properly to the caller, so an array of Objects gets returned.
    * @return an array of length length(), with the same items in it as are
    *         stored in the list, in the same order.
    */

    
  public Object[] toArray(){
    Node<Country> tmp = head;
    
    Object[] array = new Object[count] ;
    
    for(int i = 0; i < count; i++){
        if(tmp == null)break;
        array[i] = tmp.getData();
        tmp = tmp.getNext();
    }
    return array;
  }
    
  /** Returns an iterator that begins just before index 0 in this list. */

  

  /** Removes all items from the list. */
  public void clear(){
    while(!isEmpty()){
      remove(0);
    }

  }

  public static void main(String[] args){


  }



}