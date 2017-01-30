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
		//What is the condition to expand underlying array?
		if(size==elems.length){
			doubleElems();
	    }
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

		//left over elem needs to be nulled
		elems[elemToMoveUp-1] = null;
		size = size - 1;

		//What's the condition that you want to halve the size of array?
		if(size <= elems.length/4 && elems.length > 10 ){
			halveElems();
		}

		return removed;
	}

	public void add(int index, Cow c) {
		//index out of bounds
		//could add at end
		if(index > size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");

		if(size == elems.length){
			doubleElems();
		}

		//shift up elements on right of index
		for(int i=size-1; i>=index; i--){
			elems[i+1] = elems[i];
		}

		elems[index] = c;
		size = size + 1;
	}

	//probably could consolidate into 'resize' method for
	//less repeated code

	private void doubleElems(){
		Cow[] newElems = new Cow[elems.length * 2];

		for(int i=0; i<size; i++){ //*could use elems.length only if clearing
			newElems[i] = elems[i];
		}

		this.elems = newElems; //don't need keyword this
	}

	private void halveElems(){
		Cow[] newElems = new Cow[elems.length/2];

		for(int i = 0; i<size; i++){
			newElems[i] = elems[i];
		}

		elems = newElems;

	}
}
