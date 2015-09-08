
public class DoubleHashMap<K, V> extends HashMap<K, V>{
	
	protected int secondaryMod;
	
	public DoubleHashMap(int multiplier, int modulus, int secondaryMod) {
		super(4000, multiplier, modulus);
		this.secondaryMod = secondaryMod;
	}

	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryMod) {
		super(hashMapSize, multiplier, modulus);
		this.secondaryMod = secondaryMod;
	}
	
	public int hash(K key) {
		
		int j = 0;
		int key1 = (multiplier * Math.abs(key.hashCode())) % modulus;	
		int key2 = secondaryHash(key);	
		
		int y = key1 % capacity;
		HashMapNode pivot = array[y];
		boolean isCollision = false;
		while(pivot != null && pivot.getKey() != null){
			
			// overwrite
			if(key.equals(pivot.getKey())){
				return y;
			}else{
				isCollision = true;
				this.totalCollision++;
				j++;
				
				if(j > capacity){
					j = 0;
					y = -1;
					break;
				}
				
				y = (key1 + j * key2) % capacity;
				pivot = array[y];
			}
		}
		
		if(j > this.maxCollision){
			this.maxCollision = j;
		}
		
		if(y != -1 && isCollision) this.putCollision++;
		
		return y;
	}
	
	public int secondaryHash(K key){
		int tmp = this.secondaryMod - (Math.abs(key.hashCode()) % this.secondaryMod);
		return tmp;
	}
	
	public static void main(String...args){
		DoubleHashMap<String, Integer> a = new DoubleHashMap<String, Integer>(1, 10, 223);
		System.out.println(a.put("first", 23));
		System.out.println(a.put("first", 24));
		System.out.println(a.put("first1", 25));
		System.out.println(a.get("first"));
		System.out.println(a.keys().size());
		System.out.println(a.remove("first"));
		System.out.println(a.remove("first2"));
		System.out.println(a.keys().size());
	}
}
