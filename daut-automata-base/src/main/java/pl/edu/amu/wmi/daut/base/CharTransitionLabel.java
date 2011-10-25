package pl.edu.amu.wmi.daut.base;

class CharTransitionLabel extends TransitionLabel {
        /**
         * Konstruuje etykietę oznaczoną znakiem 'c'.
         */
        public CharTransitionLabel(char c) {
            ch = c;
        }

        public boolean canBeEpsilon() {
            return false;
        }

        public boolean canAcceptCharacter(char c) {
            return c == ch;
        }

        public boolean isEmpty() {
            return false;
        }

        public char getChar() {
            return ch;
        }

        protected TransitionLabel intersectWith(TransitionLabel label) {
            return label.canAcceptCharacter(ch_) ? this : new EmptyTransitionLabel();
        }

        private char ch;
    }
