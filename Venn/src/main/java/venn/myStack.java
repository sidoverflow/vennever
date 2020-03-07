package venn;


public interface myStack {
	
	// push a state onto my stack
	boolean push(Operation state);
	
	// pop my stack
	Operation pop();
	
	// checks for the state in stack
	boolean contains(Operation state);
	
	
	//returns the size of the stack
	int size();
	
	// clears the entire stack
	void clear();
	
	//checks if my stack is empty
	boolean isEmpty();

}
