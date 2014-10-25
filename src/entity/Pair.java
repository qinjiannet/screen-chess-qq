package entity;

//Author 在线疯狂
//Homepage http://bookshadow.com
public class Pair<Key, Value> {
	private Key key;
	private Value value;
	
	public Pair (){
	}
	
	public Pair(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "<" + key + "," + value + ">";
	}
}
