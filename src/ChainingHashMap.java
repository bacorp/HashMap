import java.util.ArrayList;
import java.util.List;


public class ChainingHashMap<K, V> extends HashMap<K, V>{

	public ChainingHashMap(int multiplier, int modulus){
		super(4000, multiplier, modulus);
	}
	public ChainingHashMap(int hashMapSize, int multiplier, int modulus){
		super(hashMapSize, multiplier, modulus);
	}
	
	public int hash(K key){
		int temp = Math.abs((multiplier * Math.abs(key.hashCode())) % modulus) % capacity;
		return temp;
	}
	
	public int[] getFullestBuckets(){
		
		int cellNum = 0, nodeNum = 0;
		for(int i = 0 ; i < capacity; ++i){
			ChainingHashMapNode chmn = (ChainingHashMapNode) array[i];
			int count = 0;
			while(chmn != null && chmn.getKey() != null){
				count++;
				chmn = chmn.getNext();
			}
			
			if(count > nodeNum){
				nodeNum = count;
				cellNum = i;
			}
		}
		
		return new int[]{nodeNum, cellNum};
	}
	
	public List<K> keys(){
		List<K> sets = new ArrayList<K>();
		for(int i = 0 ; i < capacity; ++i){
			ChainingHashMapNode chmn = (ChainingHashMapNode) array[i];
			while(chmn != null && chmn.getKey() != null){
				sets.add((K) chmn.getKey());
				chmn = chmn.getNext();
			}
		}
		return sets;
	}
	
	public V put(K key, V value) {
		
		if(size == capacity) return null;
		
		int index = this.hash(key);
		ChainingHashMapNode chmn = (ChainingHashMapNode) array[index];
		if(chmn != null && chmn.getKey() !=  null){
			
			if(key.equals(chmn.getKey())){
				array[index] = new ChainingHashMapNode(key, value);
				return value;
			}else{
				this.putCollision++;
				// collision here
				ChainingHashMapNode next = chmn.getNext();
				while(next != null){
					
					if(next.getKey().equals(key)){
						chmn.setNext(new ChainingHashMapNode(key, value));
						return value;
					}else{
						chmn = next;
						next = chmn.getNext();
					}	
				}
				
				chmn.setNext(new ChainingHashMapNode(key, value));
				size++;
			}
		}else{
			// no collision here
			array[index] = new ChainingHashMapNode(key, value);
			size++;
		}
		return value;
	}

	public V get(K key) {
		
		int index = this.hash(key);
		ChainingHashMapNode chmn = (ChainingHashMapNode) array[index];
		while(chmn!= null && chmn.getKey() != null){
			if(chmn.getKey().equals(key)){
				return (V) chmn.getValue();
			}
			chmn = chmn.getNext();
		}
		return null;
	}
	
	public V remove(K key) {
		
		int index = this.hash(key);
		ChainingHashMapNode chmn = (ChainingHashMapNode) array[index];
		ChainingHashMapNode next = chmn.getNext();
		
		// on the cell
		if(chmn != null && chmn.getKey() != null && chmn.getKey().equals(key)){		
			if(next == null) 
				array[index] = new ChainingHashMapNode(null, null);
			else
				array[index] = next;
			size--;
			return (V) chmn.getValue();
		}
		
		// on the link
		while(next!= null && next.getKey() != null){
			if(next.getKey().equals(key)){
				ChainingHashMapNode son = next.getNext();
				if(son != null){
					chmn.setNext(son);
				}else{
					chmn.setNext(null);
				}
				size--;
				return (V) next.getValue();				
			}
			chmn = next;
			next = next.getNext();
		}
		return null;
	}
	
	public static void main(String...args){
		ChainingHashMap<String, Integer> a = new ChainingHashMap<String, Integer>(1, 10);
		System.out.println(a.put("first", 23));
		System.out.println(a.put("first", 24));
		System.out.println(a.put("first1", 25));
		System.out.println(a.get("first"));
		System.out.println(a.get("first1"));
		System.out.println(a.get("first2"));
		System.out.println(a.keys().size());
		System.out.println(a.remove("first"));System.out.println(a.remove("sec"));
		System.out.println(a.keys().size());
	}
}
