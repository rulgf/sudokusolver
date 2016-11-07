package sudoku;

public class node {
	//Dimensions of the node
	int row;
	int col;
	int value;
	
	public node(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void printNode(){
		System.out.println("Position: " + row + ", " + col);
	}
	
	public void updateValue(int value){
		this.value=value;
	}
}
