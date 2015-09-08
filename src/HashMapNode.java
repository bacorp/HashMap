
public class HashMapNode {
	
	protected Object key;
	protected Object value;
	
    public HashMapNode(Object key, Object value){
    	this.key = key;
    	this.value = value;
    }

    public Object getKey(){
    	return this.key;
    }

    public Object getValue(){
    	return this.value;
    }

    public void setValue(Object newValue){
    	this.value = newValue;
    }
}

