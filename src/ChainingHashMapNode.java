
public class ChainingHashMapNode extends HashMapNode{

	private ChainingHashMapNode next;
	
	public ChainingHashMapNode(Object key, Object value) {
		super(key, value);
		next = null;
	}

	public ChainingHashMapNode getNext(){
		return this.next;
	}
	
	public void setNext(ChainingHashMapNode newNext){
		this.next = newNext;
	}
}
