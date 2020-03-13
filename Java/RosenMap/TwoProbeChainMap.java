/**
 * Two probe map implementation.
 * 
 * @author Joseph Rosen
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class TwoProbeChainMap<Key, Value> implements Map<Key, Value> {
	private class Entry {
        public Key key;
        public Value value;

        public Entry (Key k, Value v) {
            key = k;
            value = v;
        }
    }
    
    private int N;
    private int M;
    
    private LinkedList<Entry>[] entries;

    public TwoProbeChainMap() {
    	this(997);
    }
    
    public TwoProbeChainMap(int M) {
        this.N = 0;
        this.M = M;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++)
            entries[i] = new LinkedList<>();
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    private int hash2(Key key) {
    	return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }
    
	public Value get(Key key) {
		int index1 = hash(key);
		int index2 = hash2(key);
		
		for (Entry entry : entries[index1]) {
			if (key.hashCode() == entry.key.hashCode()) {
				return entry.value;
			}
		}
		
		for (Entry entry : entries[index2]) {
			if (key.hashCode() == entry.key.hashCode()) {
				return entry.value;
			}
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		boolean added = false;
		int finalIndex = 0;
		int index1 = hash(key);
		int index2 = hash2(key);

		for (Entry entry : entries[index1]) {
			if (key.hashCode() == entry.key.hashCode()) {
				entry.value = val;
				added = true;
			}
		}
		
		for (Entry entry : entries[index2]) {
			if (key.hashCode() == entry.key.hashCode()) {
				entry.value = val;
				added = true;
			}
		}
		
		if (!added) {
			if (entries[index1].size() <= entries[index2].size()) {
				finalIndex = index1;
			} else {
				finalIndex = index2;
			}
			entries[finalIndex].add(new Entry(key, val));
			N++;
		}	
	}
	
	public void remove(Key key) {
		if (contains(key)) {
			Entry target = null;
			int index1 = hash(key);
			int index2 = hash2(key);
			int finalIndex = 0;
			
			for(Entry e : entries[index1]) {
				if (e.key == key) {
					target = e;
					finalIndex = index1;
				}
			}
			
			if (target == null) {
				for(Entry e : entries[index2]) {
					if (e.key == key) {
						target = e;
						finalIndex = index2;
					}
				}
			}
			entries[finalIndex].remove(target);
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
            for(Entry e : entries[i]) {
                    queue.add(e.key);
            }
        }
        return queue;
	}
}