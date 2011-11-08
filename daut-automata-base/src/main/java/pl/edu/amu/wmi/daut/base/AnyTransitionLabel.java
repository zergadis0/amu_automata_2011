package automaty;

/*
 * @author cole1911
 */

/*
* Implementacja Transition Label reprezentujaca
* przejscie po dowolnym znaku.
*/
public class AnyTransitionLabel extends TransitionLabel {

    @Override
    public boolean canBeEpsilon() {
        return false;
    }

    @Override
    public boolean canAcceptCharacter(char c) {
        return true;
    }

    @Override
    public boolean isEmpty() {
         return false;
    }

    @Override
    public TransitionLabel intersectWith(TransitionLabel label) {
          return label.isEmpty() ? new EmptyTransitionLabel() : this;
    }
}