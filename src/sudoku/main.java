package sudoku;

public class main {
	static int sudoku[][] = { 
			{ 2, 0, 0, 8, 0, 4, 0, 0, 6 }, 
			{ 0, 0, 6, 0, 0, 0, 5, 0, 0 }, 
			{ 0, 7, 4, 0, 0, 0, 9, 2, 0 }, 
			{ 3, 0, 0, 0, 4, 0, 0, 0, 7 }, 
			{ 0, 0, 0, 3, 0, 5, 0, 0, 0 }, 
			{ 4, 0, 0, 0, 6, 0, 0, 0, 9 }, 
			{ 0, 1, 9, 0, 0, 0, 7, 4, 0 }, 
			{ 0, 0, 8, 0, 0, 0, 2, 0, 0 }, 
			{ 5, 0, 0, 6, 0, 8, 0, 0, 1 } };
	
	/*static int sudoku[][] = { 
			{ 7, 0, 1, 5, 2, 0, 3, 6, 4 }, 
			{ 3, 5, 9, 1, 0, 4, 8, 2, 7 }, 
			{ 6, 2, 4, 3, 8, 7, 5, 9, 0 }, 
			{ 4, 1, 0, 8, 9, 6, 7, 0, 5 }, 
			{ 0, 7, 3, 2, 4, 1, 6, 8, 9 }, 
			{ 8, 9, 6, 7, 0, 3, 1, 4, 0 }, 
			{ 9, 0, 8, 4, 0, 5, 2, 7, 6 }, 
			{ 0, 4, 7, 6, 3, 2, 9, 5, 8 }, 
			{ 2, 6, 5, 9, 7, 8, 4, 1, 0 } };*/
	
	public boolean checkValue(node Node, int content){
		if(sudoku[Node.row][Node.col] != 0) {
			return false;
		}
		
		//Check  row
		for (int r = 0; r < 9; r++) {
			if(sudoku[Node.row][r] == content)
				return false;
		}
		
		//Check column
		for(int c = 0; c < 9; c++){
			if(sudoku[c][Node.col] == content){
				return false;
			}
		}
		
		//Check inner box
		int xi = (Node.row /3)*3;
		int yi = (Node.col /3)*3;
		int xf = xi + 2;
		int yf = yi + 2;
		
		for(int x = xi; x <= xf; x++){
			for(int y = yi; y <= yf; y++){
				if(sudoku[x][y]== content){
					return false;
				}
			}
		}
		return true;
	}
	
	public node getNextNode(node Actual){
		int a_row = Actual.row;
		int a_col = Actual.col;
		
		int n_col = a_col+1;
		int n_row = a_row;
		
		if(n_col>8){
			n_col = 0;
			n_row++;
			if(n_row>8){
				return null;
			}
		}
		
		node New = new node(n_row, n_col);
		
		return New;
	}
	
	public boolean resolveSudoku(node Node){
		
		if(Node == null){
			return true;
		}
		
		//Check if the actual position has a value
		node actual_cell = Node;
		while(sudoku[actual_cell.row][actual_cell.col] != 0){
			actual_cell = this.getNextNode(actual_cell);
			if(actual_cell == null){
				return true;
			}
		}
		
		int v = 1;
		
		while(v < 10){
			boolean check = this.checkValue(actual_cell, v);
			
			if(!check){
				v++;
			}else{
				sudoku[actual_cell.row][actual_cell.col] = v;
				//v++;
				node n_node = this.getNextNode(actual_cell);
				boolean next = this.resolveSudoku(n_node);
				if(next){
					return true;
				}else{
					sudoku[actual_cell.row][actual_cell.col]=0;
				}
			}
			
		}
				
		
		return false;
	}
	
	//Main function
	public static void main(String[] args) {
		System.out.println("Real: ");
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++){
				System.out.print(sudoku[row][col]);
			}
			System.out.println();
	 	}
		System.out.println();
		node first = new node(0, 0);
		boolean resolve = new main().resolveSudoku(first);
		if(resolve){
			System.out.println("Resolved: ");
			
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++){
					System.out.print(sudoku[row][col]);
				}
				System.out.println();
		 	}
		}else{
			System.out.println("The sudoku cannot be resolved");
		}
	}
}
