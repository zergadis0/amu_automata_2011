package pl.edu.amu.wmi.daut.base;

/**
 * Przejście po podanym znaku.
 */
class CharTransitionLabel extends TransitionLabel {
        /**class CharTransitionLabel extends TransitionLabel {

         * Konstruuje etykiety oznaczone znakiem 'c'.
         */
        public CharTransitionLabel(char c) {
            ch_ = c;
        }

        public boolean canBeEpsilon() {
            return false;
        }

        public boolean canAcceptCharacter(char c) {
            return c == ch_;
        }

        public boolean isEmpty() {
            return false;
        }

        public char getChar() {
            return ch_;
        }

        protected TransitionLabel intersectWith(TransitionLabel label) {
            return label.canAcceptCharacter(ch_) ? this : new EmptyTransitionLabel();
        }

        private char ch_;
    }