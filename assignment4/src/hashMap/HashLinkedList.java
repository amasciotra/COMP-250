package hashMap;


public class HashLinkedList<K,V>{
	/*
	 * Fields
	 */
	private HashNode<K,V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList(){
		head = null;
		size = 0;
	}


	/*
	 *Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value){
		
		// ADD CODE BELOW HERE
		HashNode<K,V> newNode = new HashNode<K,V> (key, value);
		size++;
		if (head == null){
			head = newNode;		
		}
		else {
			newNode.next = head;
			head = newNode;
		}		

		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

	public HashNode<K,V> getListNode(K key){
		// ADD CODE BELOW HERE
		
		HashNode<K,V> tmp = head;
		if (tmp==null){
			return null;
			
		}while (!tmp.getKey().equals(key) && tmp.next != null){
			tmp = tmp.next;
			
			
		}
		if (tmp.getKey().equals(key)){
			return tmp;
		}else {
			return null;
		}
		

		// ADD CODE ABOVE HERE
	}


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

	public HashNode<K,V> removeFirst(){
		// ADD CODE BELOW HERE
		

		if (head == null){
			return null;	
		}
		HashNode<K,V> tmp = head;
		head = tmp.next;
		size --;
		return tmp;


		// ADD CODE ABOVE HERE
	
	}

	/*
	 * Remove Node by key from linked list 
	 */

	public HashNode<K,V> remove(K key){
		// ADD CODE BELOW HERE
		//HashNode<K,V> tmp;
		HashNode<K,V> tmp = head;
		HashNode<K,V> remove = head;
		
		if(tmp.getKey().equals(key)){
			return removeFirst();
			
		}
		while (!tmp.next.getKey().equals(key) && tmp.next != null){
			tmp = tmp.next;
		}
		if (tmp.next.getKey().equals(key)){
			remove = tmp.next;
			tmp.next = remove.next;
			size--;
			return remove;
		}
		else{
			return null;
		}
		
		
		
	}


	/*
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty(){
		return size == 0? true:false;
	}

	int size(){
		return this.size;
	}

	//ADD YOUR HELPER  METHODS BELOW THIS

	//makes a cloned linkedlist using the clone nodes from HashNode file
public HashLinkedList<K,V> copylist (HashLinkedList<K,V> list){
		
		HashLinkedList<K,V>  tmp= new HashLinkedList<K,V>();
		HashNode<K,V> newCopynode = list.head;
		HashNode <K,V> newNode;
		
		while(newCopynode != null){
			
	        newNode = newCopynode.clone(newCopynode);  
	        tmp.add(newNode.getKey(), newNode.getValue());
			newCopynode= newCopynode.next;
		}
		
		return tmp;
	
	}





	//ADD YOUR HELPER METHODS ABOVE THIS


}
