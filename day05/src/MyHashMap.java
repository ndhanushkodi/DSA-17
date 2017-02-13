import java.util.*;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many or too few entries.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> implements Map<K, V> {

	// average number of entries per map before we grow the map
	private static final double ALPHA = 1.0;
	// average number of entries per map before we shrink the map
	private static final double BETA = .25;

	// resizing factor: (new size) = (old size) * (resize factor)
	private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

	private static final int MIN_MAPS = 16;

	// list of maps
	protected List<MyLinearMap<K,V>> maps;
	private int size = 0;

	public MyHashMap() {
		makeMaps(MIN_MAPS);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Initialize maps
	 */
	protected void makeMaps(int size) {
		maps = new ArrayList<>();
		for (int i=0; i<size; i++) {
			maps.add(new MyLinearMap<K, V>());
		}
	}

	protected MyLinearMap<K, V> chooseMap(Object key) {
		int index = key==null ? 0 : Math.abs(key.hashCode()) % maps.size();
		return maps.get(index);
	}

	@Override
	public boolean containsKey(Object key) {
		MyLinearMap<K,V> map = chooseMap(key);
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for (MyLinearMap<K,V> map : maps) {
			if (map.containsValue(value)) return true;
		}

		return false;
	}

	protected void rehash(double growthFactor) {
		List<MyLinearMap<K,V>> oldMaps = maps;
		int newSize = (int) (maps.size() * growthFactor);

		makeMaps(newSize);

		for (MyLinearMap<K,V> map : oldMaps) {
			for (Map.Entry<K,V> entry : map.getEntries()) {
				put(entry.getKey(), entry.getValue());
			}
		}
	}

	@Override
	public V get(Object key) {
		MyLinearMap<K,V> m = chooseMap(key);
		return m.get(key);
	}

	@Override
	public V put(K key, V value) {
		// TODO
		MyLinearMap<K,V> map = chooseMap(key);
		size -= map.size();
		V oldValue = map.put(key, value);
		size += map.size();

		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * ALPHA) {
			size = 0;
			rehash(GROWTH_FACTOR);
		}
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		// TODO
		MyLinearMap<K,V> map = chooseMap(key);
		size -= map.size();
		V oldValue = map.remove(key);
		size += map.size();

		// check if the number of elements per map is below the threshold
		// make sure the number of maps doesn't go too low
		if (maps.size() * SHRINK_FACTOR >= MIN_MAPS && size() < maps.size() * BETA) {
			size = 0;
			rehash(SHRINK_FACTOR);
		}
		return oldValue;
//				MyLinearMap<K,V> m = chooseMap(key);
//		size -= maps.size();
//		V oldValue = m.remove(key);
//		size+=maps.size();
//
//		if (maps.size()*SHRINK_FACTOR >= MIN_MAPS && size < maps.size() * BETA) {
//			size = 0;
//			rehash(SHRINK_FACTOR);
//		}
//		return oldValue;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public void clear() {
		for (int i=0; i<maps.size(); i++) {
			maps.get(i).clear();
		}
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		Collection<V> ll = new LinkedList<>();
		for (MyLinearMap<K,V> map : maps) {
			ll.addAll(map.values());
		}
		return ll;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K,V>> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.getEntries());
		}
		return set;
	}
}
//import java.util.*;
//
///**
// * Implementation of a HashMap using a collection of MyLinearMap and
// * resizing when there are too many or too few entries.
// *
// * @author downey
// * @param <K>
// * @param <V>
// *
// */
//public class MyHashMap<K, V> implements Map<K, V> {
//
//	// average number of entries per map before we grow the map
//	private static final double ALPHA = 1.0;
//	// average number of entries per map before we shrink the map
//	private static final double BETA = .25;
//
//	// resizing factor: (new size) = (old size) * (resize factor)
//	private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;
//
//	private static final int MIN_MAPS = 16;
//
//	// list of maps
//	protected List<MyLinearMap<K,V>> maps;
//	private int size = 0;
//
//	public MyHashMap() {
//		makeMaps(MIN_MAPS);
//	}
//
//	@Override
//	public int size() {
//		return size;
//	}
//
//	@Override
//	public boolean isEmpty() {
//		return size() == 0;
//	}
//
//	/**
//	 * Initialize maps
//	 */
//	protected void makeMaps(int size) {
//		maps = new ArrayList<>();
//		for(int i = 0; i<size;i++){
//			maps.add(new MyLinearMap<>());
//		}
//	}
//
//	protected MyLinearMap<K, V> chooseMap(Object key) {
//		int index = key==null ? 0 : Math.abs(key.hashCode()) % maps.size();       //from the reading
//		return maps.get(index);
////        MyLinearMap<K,V> chosen;
////     if (key == null){
////         chosen = maps.get(0);
////        } else {
////         chosen = maps.get(key.hashCode() % maps.size());
////        }
////    return chosen;
//	}
//
//	@Override
//	public boolean containsKey(Object key) {
//		MyLinearMap<K,V> m = chooseMap(key);
//		return m.containsKey(key);
//	}
//
//	@Override
//	public boolean containsValue(Object value) {
//		for (MyLinearMap<K,V> m : maps) {
//			if(m.containsValue(value)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	protected void rehash(double growthFactor) {
//		List<MyLinearMap<K,V>> oldMap = maps;
//		makeMaps((int)(maps.size()*growthFactor));
//		for(MyLinearMap<K,V> m : oldMap){
//			for(Map.Entry<K,V> entry : m.getEntries()){
//				put(entry.getKey(),entry.getValue());
//			}
//		}
//
//	}
//
//	@Override
//	public V get(Object key) {
//		MyLinearMap<K,V> m = chooseMap(key);
//		return m.get(key);
//	}
//
//	@Override
//	public V put(K key, V value) {
//		MyLinearMap<K,V> m = chooseMap(key);
//		size-=m.size();
//		V val = m.put(key, value);
//		size+=m.size();
//		if (size > maps.size() * ALPHA) {
//			size = 0;
//			rehash(GROWTH_FACTOR);
//		}
//		return val;
//
//	}
//
//	@Override
//	public V remove(Object key) {
//		MyLinearMap<K,V> m = chooseMap(key);
//		size -= maps.size();
//		V oldValue = m.remove(key);
//		size+=maps.size();
//
//		if (maps.size()*SHRINK_FACTOR >= MIN_MAPS && size < maps.size() * BETA) {
//			size = 0;
//			rehash(SHRINK_FACTOR);
//		}
//		return oldValue;
//	}
//
//	@Override
//	public void putAll(Map<? extends K, ? extends V> m) {
//		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
//			put(entry.getKey(), entry.getValue());
//		}
//	}
//
//	public void clear() {
//		for (int i=0; i<maps.size(); i++) {
//			maps.get(i).clear();
//		}
//		size = 0;
//	}
//
//	@Override
//	public Set<K> keySet() {
//		Set<K> set = new HashSet<>();
//		for (MyLinearMap<K,V> map : maps) {
//			set.addAll(map.keySet());
//		}
//		return set;
//	}
//
//	@Override
//	public Collection<V> values() {
//		Collection<V> ll = new LinkedList<>();
//		for (MyLinearMap<K,V> map : maps) {
//			ll.addAll(map.values());
//		}
//		return ll;
//	}
//
//	@Override
//	public Set<Entry<K, V>> entrySet() {
//		Set<Entry<K,V>> set = new HashSet<>();
//		for (MyLinearMap<K,V> map : maps) {
//			set.addAll(map.getEntries());
//		}
//		return set;
//	}
//
//
//}
