package com.kalher.movietime.utility;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class CollectionUtility {

    public static boolean isCollectionNullOrEmpty(Collection< ? > c) {
        return c == null || c.isEmpty ();
    }

    public static <T> boolean isArrayNullOrEmpty(T[] c) {
        return c == null || c.length == 0;
    }

    public static boolean isMapNullOrEmpty(Map< ? , ? > c) {
        return c == null || c.isEmpty ();
    }

    public static int getMapSize(Map< ? , ? > c) {
        return c != null ? c.size() : 0;
    }

    public static boolean isCollectionNullOrEmptyByRemovingNullObj(Collection< ? > c) {
        if (c == null) {
            return true;
        }
        Iterator itr = c.iterator ();
        if(itr.hasNext ()) {
            if(itr.next () == null) {
                itr.remove ();
            }
        }
        return c.isEmpty ();
    }

}
