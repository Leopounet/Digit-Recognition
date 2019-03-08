package com.labeldistance;

import java.util.Comparator;

/**
 * Creates the compare function for LabelDistance Objects.
 **/
public class LabelDistanceCompare implements Comparator<LabelDistance>
{
    /**
     * Overrides the comparison function to compare this kind of object.
     * @param lda The first LabelDistance object
     * @param ldb The second LabelDistance object
     **/
    public int compare(LabelDistance lda, LabelDistance ldb) {
        // Objects are ordered by their distance attributes
        if(lda.p_distance > ldb.p_distance)
        {
            return 1;
        }
        else if(lda.p_distance == ldb.p_distance)
        {
            return 0;
        }
        return -1;
    }
}
