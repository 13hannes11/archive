package edu.kit.informatik;

/**
 * The Class Matrix.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Matrix {

    /**
     * The matrix. (represents the data of the matrix ([row][column]) starting
     * with (0,0))
     */
    private int[][] data;

    /**
     * Instantiates a new matrix of which every element is 0
     *
     * @param iElements
     *            the number of rows
     * @param jElements
     *            the number of columns
     */
    public Matrix(int iElements, int jElements) {
        data = new int[iElements][jElements];
        for (int i = 0; i < iElements; i++) {
            for (int j = 0; j < jElements; j++) {
                data[i][j] = 0;
            }
        }
    }

    /**
     * Instantiates a new matrix from a data array
     *
     * @param data
     *            the data of the matrx
     */
    public Matrix(int[][] data) {
        this.data = data;
    }

    /**
     * Gets a single element of the matrix at the position (row,column).
     *
     * @param i
     *            the row
     * @param j
     *            the column
     * @return the element at (i,j)
     */
    public int getSingleElement(int i, int j) {
        if (i < data.length && j < data[i].length)
            return data[i][j];
        else
            return -1;
    }

    /**
     * Sets a single element of the matrix to a value
     *
     * @param i
     *            row of the element
     * @param j
     *            column of the element
     * @param val
     *            the value that should be set at (i,j)
     */
    public void setSingleElement(int i, int j, int val) {
        if (i < data.length && j < data[i].length) {
            data[i][j] = val;
        }
    }

    /**
     * Gets the number of columns.
     *
     * @return the number of columns
     */
    public int getColumnCount() {
        return data[0].length;
    }

    /**
     * Gets the number of rows.
     *
     * @return the number of rows
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Gets a row as vector.
     *
     * @param row
     *            the row that should be returned as vector
     * @return the row converted to a vector
     */
    public VectorInt getRowVector(int row) {
        int[] arr = new int[data[row].length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = data[row][i];
        }
        return new VectorInt(arr);
    }

    /**
     * Gets a column vector.
     *
     * @param column
     *            the column that should be returned as vector
     * @return the column converted to a vector
     */
    public VectorInt getColumnVector(int column) {
        int[] arr = new int[data.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = data[i][column];
        }
        return new VectorInt(arr);
    }

    /**
     * Addition of two matrices.
     *
     * @param m
     *            the first matrix
     * @param n
     *            the second matrix
     * @return the result
     */
    public static Matrix addition(Matrix m, Matrix n) {
        if (n.getColumnCount() != m.getColumnCount() && n.getRowCount() != m.getColumnCount())
            return null;

        int[][] result = new int[n.getRowCount()][n.getColumnCount()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = n.getSingleElement(i, j) + m.getSingleElement(i, j);
            }
        }
        return new Matrix(result);
    }

    /**
     * Cross product of two matrices. (m will be multiplied by n [n x m])
     *
     * @param n
     *            the second matrix
     * @param m
     *            the first matrix
     * @return the result
     */
    public static Matrix crossProduct(Matrix n, Matrix m) { // n x m
        if (n.getColumnCount() != m.getRowCount())
            return null;
        int[][] result = new int[n.getRowCount()][m.getColumnCount()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                // Skalarprodukt
                result[i][j] = VectorInt.scalarProduct(n.getRowVector(i), m.getColumnVector(j));
            }
        }
        return new Matrix(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Matrix && ((Matrix) obj).getColumnCount() == this.getColumnCount()
                && ((Matrix) obj).getRowCount() == this.getRowCount()) {
            for (int i = 0; i < this.data.length; i++) {
                for (int j = 0; j < this.data[i].length; j++) {
                    if (this.data[i][j] != ((Matrix) obj).getSingleElement(i, j))
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                str += " " + String.format("% 4d", this.data[i][j]);
            }
            str += "\n";
        }
        return str;
    }
}
