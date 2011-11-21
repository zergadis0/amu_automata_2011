import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;



    static private void putTransitionLabelInSet(HashSet<TransitionLabel> TSet,
            TransitionLabel transitionLabel) {
        if (!transitionLabel.isEmpty())
            if (!transitionLabel.getClass().equals((new AnyTransitionLabel()).getClass()))
                if (transitionLabel.getClass().equals((new CharTransitionLabel('a'))
                        .getClass())) {
                    TSet.add(transitionLabel);
                } else if (transitionLabel.getClass().equals(new CharSetTransitionLabel(
                        new HashSet<Character>()).getClass())) {
                    for (char sign : ((CharSetTransitionLabel)transitionLabel).getCharSet()) {
                        TSet.add(new CharTransitionLabel(sign));
                    }
                } else if (transitionLabel.getClass().equals((new CharRangeTransitionLabel(
                        'a', 'b')).getClass())) {
                    int radix = Character.MIN_RADIX + 1;
                    for (int iterator = Character.forDigit((
                            (CharRangeTransitionLabel)transitionLabel).getFirstChar(), radix);
                        iterator < Character.forDigit((
                            (CharRangeTransitionLabel)transitionLabel).getSecondChar(), radix) + 1;
                        iterator++) {
                        TSet.add(new CharTransitionLabel(Character.forDigit(iterator, radix)));
                    }
                }
     
    }

    
    static public void determinize2(AutomatonSpecification nfa,
            DeterministicAutomatonSpecification resultDfa) throws StructureException {

      
        if (resultDfa.isEmpty()) {

            
            HashSet<TransitionLabel> TSet = new HashSet<TransitionLabel>();

            
            List<State> KList = nfa.allStates();

            
            for (State s : KList){
                for (OutgoingTransition oT : nfa.allOutgoingTransitions(s)) {
                    if(TSet.isEmpty()){
                        putTransitionLabelInSet(TSet, oT.getTransitionLabel());
                    }
                    boolean isUnique = true;
                    for (TransitionLabel t : TSet) {
                        if (t.intersect(oT.getTransitionLabel()).equals(t)){
                            isUnique = false;
                            break;
                        }
                    }
                    if (isUnique)
                        putTransitionLabelInSet(TSet, oT.getTransitionLabel());
                }
            }

            
            int nrOfDFAStates = (int)Math.pow((double)2,(double)(nfa.countStates()));
            
            
            for (int i = 0; i < nrOfDFAStates; i++) {
                resultDfa.addState();
            }
            List<State> KPrimList = resultDfa.allStates();
            List<PowerSetElement> KPrimAdditionalList = new Vector<PowerSetElement>();
            PowerSetElement.giveAllPowerSetElements(KPrimAdditionalList, KPrimList, KList);

            
            Vector<Integer> FList = new Vector<Integer>();
            for (State seeker : KList) {
                if (nfa.isFinal(seeker)) {
                    FList.add(KList.indexOf(seeker));
                }
            }

            
            for (Integer endState : FList) {
                for (PowerSetElement structure : KPrimAdditionalList) {
                    if ((!resultDfa.isFinal(structure.getDFAState())) &&
                            (structure.getNFAStates().contains(KList.get(endState))))
                        resultDfa.markAsFinal(structure.getDFAState());
                }
            }

           
            {
                HashSet<State> initialStates = new HashSet<State>();
                initialStates.add(nfa.getInitialState());
                for (PowerSetElement structure : KPrimAdditionalList) {
                    if (initialStates.equals(structure.getNFAStates())) {
                        resultDfa.markAsInitial(structure.getDFAState());
                        break;
                    }
                }
            }

           
            for (PowerSetElement structure : KPrimAdditionalList) {
                if (structure.getNFAStates().isEmpty()) {
                    for (PowerSetElement targetStructure : KPrimAdditionalList) {
                        if (targetStructure.getNFAStates().isEmpty())
                            resultDfa.addTransition(structure.getDFAState(),
                                targetStructure.getDFAState(), new AnyTransitionLabel());
                        else 
                            resultDfa.addTransition(structure.getDFAState(),
                                targetStructure.getDFAState(), new EmptyTransitionLabel());
                    }
                } else {
                    for (TransitionLabel t : TSet) {
                        HashSet<State> whereTo = new HashSet<State>();
                        for (State s : structure.getNFAStates()) {
                            for (OutgoingTransition oT : nfa.allOutgoingTransitions(s)) {
                                if (oT.getTransitionLabel().toString().equals(t.toString())) {
                                    whereTo.add(oT.getTargetState());
                                }
                            }
                        }
                        PowerSetElement thereGoesNow = structure;
                        for (PowerSetElement targetStructure : KPrimAdditionalList) {
                            if (whereTo.equals(targetStructure.getNFAStates())) {
                                thereGoesNow = targetStructure;
                                break;
                            }
                        }
                        
                        resultDfa.addTransition(structure.getDFAState(),
                                thereGoesNow.getDFAState(), t);
                    }
                }
            }
        } else throw new StructureException();
    }
