import java.util.ArrayList;
import java.util.List;

public class PasswordHashMap<K, V> {

	protected int multiplier;
	protected int modulus;
	protected int size;
	protected int capacity;
	protected HashMapNode[] array;
	
	// stats
	protected int putCollision = 0;
	protected int totalCollision = 0;
	protected int maxCollision = 0;
	

	public PasswordHashMap(int multiplier, int modulus) {
		this.size = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		array = new HashMapNode[4000];
		this.capacity = 4000;
	}

	public PasswordHashMap(int hashMapSize, int multiplier, int modulus) {
		this.size = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		array = new HashMapNode[hashMapSize];
		this.capacity = hashMapSize;
	}

	// hashing
	public int hash(K key) {
		
		int count = 0;
		int temp = Math.abs((multiplier * Math.abs(key.hashCode())) % modulus) % capacity;
		int origin = temp;
		HashMapNode pivot = array[temp];
		boolean isCollision = false;
		while(pivot != null && pivot.getKey() != null){
			
			if(key.equals(pivot.getKey())){
				return temp;
			}else{
				isCollision = true;
				//this.totalCollision++;
				count++;
				temp = (temp + 1) % capacity;
				if(temp == origin){
					temp = -1;
					break;
				}else{
					pivot = array[temp];
				}
			}
		}
		
		//if(isCollision) this.putCollision++;
		//if(count > this.maxCollision){
		//	this.maxCollision = count;
		//}
		
		return temp;
	}

	public int size(){
		return this.size;
	}
	
	// the map
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	public List<K> keys(){
		List<K> sets = new ArrayList<K>();
		for(int i = 0; i < capacity; ++i){
			if(array[i] != null && array[i].getKey() != null){
				sets.add((K) array[i].getKey());
			}
		}
		return sets;
	}
	
	public V put(K key, V value) {
		
		if(size == capacity) return null;
		
		int pivot = this.hash(key);
		
		if(pivot != -1){
			array[pivot] = new HashMapNode(key, value);
			size++;
			return value;
		}else{
			return null;
		}
	}

	public V get(K key) {
		
		for(int i = 0; i < capacity; ++i){
			HashMapNode tmp = array[i];
			if(tmp != null && tmp.getKey() != null && tmp.getKey().equals(key)){
				return (V) tmp.getValue();
			}
		}
		return null;
	}

	public V remove(K key) {
		int pivot = -1;
		HashMapNode value = null;
		
		for(int i = 0; i < capacity; ++i){
			HashMapNode tmp = array[i];
			if(tmp != null && tmp.getKey() != null && tmp.getKey().equals(key)){
				pivot = i;
				break;
			}
		}
		
		if(pivot != -1){
			value = array[pivot];
			array[pivot] = new HashMapNode(null, null);
			size--;
			return (V) value.getValue(); 
		}
		
		return null;
	}
	
//	public int putCollisions(){
//		return this.putCollision;
//	}
//	public int totalCollisions(){
//		return this.totalCollision;
//	}
//	public int maxCollisions(){
//		return this.maxCollision;
//	}
//	public void resetStatistics(){
//		this.putCollision = 0;
//		this.maxCollision = 0;
//		this.totalCollision = 0;
//	}

	
	public static void main(String...args){
		PasswordHashMap<String, Integer> a = new PasswordHashMap<String, Integer>(1, 10);
		System.out.println(a.put("first", 23));
		System.out.println(a.put("first", 24));
		System.out.println(a.keys().size());
		System.out.println(a.remove("first"));
		System.out.println(a.keys().size());
	}
}
