package com.company;
/**
 * Iterator class, allows traversal of the set of neighbors
 * of given permutation. There will be n(n-1)/2 neighbors,
 * obtained by swapping all pairs of elements in the permutation.
 *
 * @author Antonio Hernandez
 */
public class PermutationNeighborhood{

    private final int[] p; //permutation
    private final int SIZE; //size of permutation
    private int loc1; //loc1 and loc2 are the locations of
    private int loc2; //p that will be swapped next

    /**
     * Initializes permutation of this object and locations
     * whose values will be swapped.
     *
     * @param a permutation whose neighborhood is to be generated
     */
    public PermutationNeighborhood(int[] a){
        SIZE = a.length;
        p = new int[SIZE];
        System.arraycopy(a, 0, p, 0, SIZE);

        loc1 = 1;
        loc2 = 2;
    }

    /**
     * Returns true if at least a neighbor remains to be generated; false
     * otherwise.
     */
    public boolean hasNext() {
        return loc1 != SIZE - 2;
    } //not counting the last element since its thesame as last

    /**
     * Returns next permutation neighbor.
     *
     * @return next permutation neighbor, if one exists; null otherwise
     */
    public int[] next(){
        if (hasNext()){
            //copy p to a
            int[] a = new int[SIZE];
            System.arraycopy(p, 0, a, 0, SIZE);
            
            //exchange elements at locations loc1 and loc2
            a[loc1] = p[loc2];
            a[loc2] = p[loc1];

            //advance loc1 and loc2
            if (loc2 == SIZE - 2){ //dont switch last element since it has to stay the same as last
                loc1++;
                loc2 = loc1 + 1;
            }
            else loc2++;

            return a;
        }
        else return null;
    }
}
