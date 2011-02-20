package org.avaje.one2many.tools;

/**
 * Tools to assist creation of Comparators.
 * 
 * @version $Revision: 27952 $, $Date: 2011-01-05 17:27:18 -0500 (Wed, 05 Jan 2011) $
 * @since 1.4, 2011-01
 * @author Paul Mendelson
 * 
 */
public class ComparatorUtils {
    /** Constant for a comparison operation that determined both objects were not null */
    public static final int NULL_SAFE_OBJECTS = Integer.MIN_VALUE;

    /**
     * Compares two (possibly) Comparable objects that may be <code>null</code>.
     * 
     * A boolean parameter is used to specify how to compare <code>null</code> to non-null values.
     * 
     * @param <T>
     *            Any class that implements Comparable.
     * @param o1
     * @param o2
     * @param nullFirst
     *            If <code>true</code>, <code>null</code> values are considered less than non-null values;
     * @return -1, 0, 1 if <code>o1</code> is less than, equal to, greater than <code>o2</code>,
     * {@link#NULL_SAFE_OBJECTS} if o1 is not null and o2 is not null and o1 is not Comparable.
     */
    public static int compareNullSafe(Object o1, Object o2, boolean nullFirst) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null && o2 != null) {
            return nullFirst ? -1 : 1;
        }
        if (o1 != null && o2 == null) {
            return nullFirst ? 1 : -1;
        }
        if (o1 instanceof Comparable)
            return ((Comparable) o1).compareTo(o2);
        return NULL_SAFE_OBJECTS;
    }

    public static int compareNullSafe(Object o1, Object o2) {
        return compareNullSafe(o1, o2, true);
    }

}
