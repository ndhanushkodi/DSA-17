public class MyArrayList {
    private Cow[] elems;
	private int size;

	public MyArrayList() {
		elems = new Cow[10];
		size = 0;
	}

	public MyArrayList(int capacity) {
		elems = new Cow[capacity];
		size = 0;
	}

	public void add(Cow c) {
		//if(size==elems.length){
		//doubleSize();
	   //}
		elems[size] = c;
		size++;
	}

	public int size() {
		return size;
	}

	public Cow get(int index) {
		if(index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
		return elems[index];
	}

	public Cow remove(int index) {
		Cow removed = elems[index];

		int elemToMoveUp = index+1; //start at next index

		while(elemToMoveUp < size){
			elems[elemToMoveUp-1] = elems[elemToMoveUp];
			elemToMoveUp++;
		}

		size = size - 1;
		return removed;
	}

	public void add(int index, Cow c) {
		// TODO
	}
}
