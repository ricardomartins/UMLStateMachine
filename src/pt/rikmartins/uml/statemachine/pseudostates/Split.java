package pt.rikmartins.uml.statemachine.pseudostates;

import pt.rikmartins.uml.statemachine.*;

/**
 * Created by ricardo on 06-08-2014.
 */
public class Split extends Junction implements Vertex.SingleTransitionTarget, Vertex.MultipleTransitionSource, Vertex.ElseTransitionSource {
    public Split(Region region) throws UMLStateMachineException {
        super(region);
    }

    @Override
    public pt.rikmartins.uml.statemachine.tools.BigStructure activate() throws UMLStateMachineException {
        return null;
    }

    @Override
    public BehavioralTransition getElseTransition() {
        return null;
    }

    @Override
    public void setElseTransition(BehavioralTransition transition) {

    }
}
