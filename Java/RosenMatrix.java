/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author (Joseph Rosen), Ruben Acuna
 * @version (1.1)
 */
public class RosenMatrix implements Matrix {

		private int row;
		private int column;
		private int[][] matrix;

		
		/**
	     * Constructor for the RosenMatrix class. Creates a RosenMatrix from
	     * the two-dimensional array.   
	     *
	     * @param matrix is used to construct a RosenMatrix
	     */
		RosenMatrix(int[][] matrix) {

			if (matrix.length == 0) {
				row = 0;
				column = 0;
			}

			else {
				row = matrix.length;
				column = matrix[0].length;
				this.matrix = new int[row][column];

				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						this.matrix[i][j] = matrix[i][j];
					}
				}
			}
		}

		/**
	     * Returns the element at particular point in the matrix.
	     * @param y y position
	     * @param x x position
	     * @return element
	     */
		@Override
		public int getElement(int y, int x) {
			return matrix[x][y];
		}

		/**
	     * Returns the number of rows in the matrix.
	     * @return rows
	     */
		@Override
		public int getRows() {
			return row;
		}

		 /**
	     * Returns the number of columns in the matrix.
	     * @return columns
	     */
		@Override
		public int getColumns() {
			return column;
		}

		/**
	     * Returns this matrix scaled by a factor. That is, computes kA where k is a
	     * constant and A is a matrix (this object).
	     * 
	     * @param scalar scalar
	     * @return matrix
	     */
		@Override
		public Matrix scale(int scalar) {	
			RosenMatrix scaledMatrix = new RosenMatrix(matrix);

			for(int i = 0; i < scaledMatrix.getRows(); i++) {
				for (int j = 0; j < scaledMatrix.getColumns(); j++) {
					scaledMatrix.matrix[i][j] *= scalar;
				}
			}
			return scaledMatrix;
		}

		 /**
	     *  Returns this matrix added with another matrix. That is, computes A+B 
	     *  where A and B are matrices (this object, and another respectively).
	     * @param other addend
	     * @return matrix
	     * @throws RuntimeException if matrices do not have matching dimensions.
	     */
		@Override
		public Matrix plus(Matrix other) throws RuntimeException{
			if (other.getRows() != this.getRows() || other.getColumns() != this.getColumns()) {
				throw new RuntimeException("Invalid matrix dimensions");
			}
			
			RosenMatrix sumMatrix = new RosenMatrix(matrix);

			for(int i = 0; i < sumMatrix.getRows(); i++) {
				for (int j = 0; j < sumMatrix.getColumns(); j++) {
					sumMatrix.matrix[i][j] = 
							( sumMatrix.getElement(j, i) + other.getElement(j, i) );
				}
			}
			return sumMatrix;
		}

		 /**
	     * Returns this matrix subtracted by another matrix. That is, computes A-B 
	     *  where A and B are matrices (this object, and another respectively).
	     * @param other subtrahend
	     * @return matrix
	     * @throws RuntimeException if matrices do not have matching dimensions.
	     */
		@Override
		public Matrix minus(Matrix other) throws RuntimeException{
			if (other.getRows() != this.getRows() || other.getColumns() != this.getColumns()) {
				throw new RuntimeException("Invalid matrix dimensions");
			}
			
			RosenMatrix differenceMatrix = new RosenMatrix(matrix);

			for(int i = 0; i < differenceMatrix.getRows(); i++) {
				for (int j = 0; j < differenceMatrix.getColumns(); j++) {
					differenceMatrix.matrix[i][j] = 
							( differenceMatrix.getElement(j, i) - other.getElement(j, i) );
				}
			}
			return differenceMatrix;
		}
		
		 /**
	     * Returns true if this matrix matches another matrix.
	     * @param other another matrix
	     * @return equality
	     */
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof RosenMatrix)) {
				return false;
			}
			RosenMatrix data =  (RosenMatrix) other;

			if (this.getRows() != data.getRows() || this.getColumns() != data.getColumns()) {
				return false;
			}

			for (int rows = 0; rows < data.getRows(); rows++) {
				for (int columns = 0; columns < data.getColumns(); columns++) {
					if (this.matrix[rows][columns] != data.matrix[rows][columns]) {
						return false;
					}
				}
			}
			return true;
		}

		/**
		 * Returns a string representation of this matrix. A new line character will
		 * separate each row, while a space will separate each column.
		 * @return string representation
		 */
		@Override
		public String toString() {
			String returnValue = "";

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					returnValue += this.matrix[i][j] + " ";
				}
				returnValue += "\n";
			}
			return returnValue;
		}

    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        Matrix m1 = new RosenMatrix(data1);
        Matrix m2 = new RosenMatrix(data2);
        Matrix m3 = new RosenMatrix(data3);
        
        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        
        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        
        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }
}