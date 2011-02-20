package org.avaje.one2many.tools;

/**
 * Helper to do a series of comparisons until we encounter a difference or nulls
 * 
 * @version $Revision$, $Date$
 * @since 1.4, 2011-01
 * @author Paul Mendelson
 * @see ComparatorUtils#compareNullSafe(Object, Object, boolean)
 */
public class NullSafeComparison {
    private int comparison;

    public int getComparison() {
        return comparison;
    }

    public boolean needsMore(Object o1, Object o2) {
        comparison = ComparatorUtils.compareNullSafe(o1, o2, true);
        return comparison == ComparatorUtils.NULL_SAFE_OBJECTS || (comparison == 0 && o1 != null);
    }
}
