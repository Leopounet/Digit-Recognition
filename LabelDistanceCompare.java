import java.util.Comparator;

public class LabelDistanceCompare implements Comparator<LabelDistance>
{
    /**
     * Overrides the comparison function to compare this kind of object.
     **/
    public int compare(LabelDistance lda, LabelDistance ldb) {
        if ( lda.p_distance > ldb.p_distance ) return -1;
        else if ( lda.p_distance == ldb.p_distance ) return 0;
        else return 1;
    }
}
