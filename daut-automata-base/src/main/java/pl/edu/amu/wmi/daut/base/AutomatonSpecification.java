package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.ArrayList;
// mała modyfikacja na potrzeby pierwszych ćwiczeń
/**
 * Klasa abstrakcyjna reprezentująca specyfikację (opis) automatu
 * (jakie są stany, przejścia, który stan jest stanem początkowym,
 * które stany są stanami akceptującymi).
 *
 * Uwaga: klasa ta nie reprezentuje działającego automatu (nie ma tu funkcji
 * odpowiadających na pytanie, czy automat akceptuje napis, czy nie),
 * tylko "zawartość" automatu.
 */
abstract class AutomatonSpecification {

    // metody "budujące" automat

    /**
     * Dodaje nowy stan do automatu.
     *
     * Zwraca dodany stan.
     */
    public abstract State addState();

    /**
     * Dodaje przejście od stanu 'from' do stanu 'to' etykietowane etykietą transitionLabel.
     */
    public abstract void addTransition(State from, State to, TransitionLabel transitionLabel);

    /**
     * Oznacza stan jako początkowy.
     */
    public abstract void markAsInitial(State state);

    /**
     * Oznacza stan jako końcowy (akceptujący).
     */
    public abstract void markAsFinal(State state);

    // metody zwracające informacje o automacie

    /**
     * Zwraca listę wszystkich stanów.
     *
     * Stany niekoniecznie muszą być zwrócone w identycznej
     * kolejności jak były dodane.
     */
    public abstract List<State> allStates();

    /**
     * Zwraca listę wszystkich przejść wychodzących ze stanu 'from'.
     *
     * Przejścia niekoniecznie muszą być zwrócone w identycznej
     * kolejności jak były dodane.
     */
    public abstract List<OutgoingTransition> allOutgoingTransitions(State from);

    /**
     * Zwraca stan początkowy.
     */
    public abstract State getInitialState();

    /**
     * Zwraca true wgdy stan jest stanem końcowym.
     */
    public abstract boolean isFinal(State state);
    
    //true-istnieją stany zbędne
    public boolean uselessStates()
    {
       boolean flag1=true;
       boolean flag2= false;
       State q = getInitialState();
       List<State> stack = new ArrayList<State>();
       List<OutgoingTransition> outTrans = new ArrayList<OutgoingTransition>();
       List<State> used = new ArrayList<State>();
       used = allStates();
       int x=0;
       
       while (true){
           if (flag1==true){
               outTrans = allOutgoingTransitions(q);
               for (int i=1; i<=outTrans.size(); i++){
                   stack.add(outTrans.get(i).getTargetState());
               }
           }
           if (!stack.isEmpty()){
               flag1=true;
               q=stack.get(stack.size());
               for (int i=1;i<=used.size();i++){
                   if (used.get(i)==q){
                       flag2=true;
                       x=i;
                       break;
                   }
               }
               if (flag2){
                   used.remove(x);
                   flag2=false;
                   continue;
               }
               else flag1=false;
           }
           else break;
       }
       
       for (int i=1;i<=used.size();i++){
           if (used.get(i)!=null){
               return true;
           }
       }
       return false;
    }
    
};
