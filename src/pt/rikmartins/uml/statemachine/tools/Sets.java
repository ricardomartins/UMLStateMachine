package pt.rikmartins.uml.statemachine.tools;

import java.util.HashSet;

/**
 * Created by ricardo on 01-10-2014.
 */
public class Sets {
    public static <E> HashSet<E> newHashSet(E... elements){
        HashSet<E> result = new HashSet<E>();

        for (E element: elements)
            result.add(element);

        return result;
    }
}
