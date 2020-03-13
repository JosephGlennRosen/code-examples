/**
 * Linear probing map implementation.
 * 
 * @author Joseph Rosen
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class LinearProbingMap<Key, Value> implements Map<Key, Value> {
	
	private class Entry<Key, Value> {
        public Key key;
        public Value value;
        
        public Entry(Key k, Value v) {
            key = k;
            value = v;
        }
    }
    
    private int N;
    private int M;

    private Entry<Key, Value>[] entries;
    
    public LinearProbingMap() {
        this(997);
    }
    
    public LinearProbingMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new Entry[M];
    }
    
    public int hash(Key key, int i) {
    	return ((key.hashCode() & 0x7fffffff) + i) % M;
    }
		
	public void put(Key key, Value val) {
		boolean added = false;
		int i = 0;
		int hash = hash(key, i);
		
		Entry<Key, Value> current = entries[hash];
		
		while (current != null) {
			if (current.key.hashCode() == key.hashCode()) {
				current.value = val;
				added = true;
				break;
			}
			
			i++;
			hash = hash(key, i);
			current = entries[hash];
		}

		if (!added) {
			entries[hash] = new Entry<Key, Value>(key, val);
			N++;
		}
	}

	public Value get(Key key) {
		int i = 0;
		
		Entry<Key, Value> result = entries[hash(key, i)];
		
		if (result != null) {
			if (result.key.hashCode() == key.hashCode()) {
				return result.value;
			}
			
			while (result.key.hashCode() != key.hashCode()) {
				i++;

				result = entries[hash(key, i)];
				
				if (result != null) {
					if (result.key.hashCode() == key.hashCode()) {
						return result.value;
					}
				}
			}
		}	
		return null;
	}

	public void remove(Key key) {
		if (contains(key)) {
			int i = 0;
			int hash = hash(key, i);

			while (entries[hash].key.hashCode() != key.hashCode()) {
				i++;
				hash = hash(key, i);
			}

			entries[hash] = null;
			N--;
		}

	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			if (entries[i] != null) {
				queue.add(entries[i].key);
			}
		}
		return queue;
	}
}