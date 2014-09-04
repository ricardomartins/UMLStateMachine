package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.BehavioralState;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ricardo on 04-09-2014.
 */
public class BehavioralStateSet extends HashSet<BehavioralState> {
    @Override
    public String toString() {
        String result = "(";

        Iterator<BehavioralState> behavioralStateIterator = this.iterator();
        while(behavioralStateIterator.hasNext()){
            result += behavioralStateIterator.next().toString();
            if (behavioralStateIterator.hasNext())
                result += ", ";
        }
        result += ")";

        return result;
    }
}
