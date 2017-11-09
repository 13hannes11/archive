package edu.kit.informatik;

/**
 * The Class VectorInt.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class VectorInt {

    /** The data. */
    private int[] data;

    /**
     * Instantiates a new vector int.
     *
     * @param vec
     *            the data of the vector
     */
    public VectorInt(int[] vec) {
        this.data = vec;
    };

    /**
     * Scalar product of two vectors.
     *
     * @param vec1
     *            the first vector
     * @param vec2
     *            the second vector
     * @return the scalar product
     */
    public static int scalarProduct(VectorInt vec1, VectorInt vec2) {
        if (vec1.data.length != vec2.data.length)
            return 0;
        int sum = 0;
        for (int i = 0; i < vec1.data.length; i++) {
            sum += vec1.data[i] * vec2.data[i];
        }
        return sum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VectorInt) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != ((VectorInt) obj).data[i])
                    return false;
            }
            return true;
        }
        return false;
    }
}