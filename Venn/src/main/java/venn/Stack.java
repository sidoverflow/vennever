package venn;

import java.util.Arrays;

public class Stack implements myStack {

	private static final int DEFAULT_CAPACITY = 1024;
	
	private Operation[] store;
	private int size = 0;
	private int capacity;
	
	public Stack() {
		capacity = DEFAULT_CAPACITY;
		store =  new Operation[DEFAULT_CAPACITY];
		}
	
	public Stack(int capacity) {
		this.capacity = capacity;
		store =  new Operation[capacity];
	}
	@Override
	public boolean push(Operation state) {
		
		if (state != null) {
			if (size >= store.length) {
				int newSize = size + (size >> 1);
				store = Arrays.copyOf(store, newSize);
			}
			store[size++] = state;
		}
		return true;
	}

	@Override
	public Operation pop() {
	
		if (size <= 0)
			return null;
		
		Operation state = store[--size];
		store[size] = null;
		
		int reducedSize = size;
		
		if (size >= capacity && size < (reducedSize << 1)){
			System.arraycopy(store, 0, store, 0, size);
		}
		return state;
}

	@Override
	public boolean contains(Operation state) {
		boolean found = false;

		for ( int i = 0; i < size; i++) {
			Operation element = store[i];
			
			if(element.equals(state))
				return true;
		}
		return found;
	}
	
	
	@Override
	public int size() {
			return size;
	}

	@Override
	public void clear() {

		for ( int i = 0; i < size; i++) {
			store[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	


	
	
}
