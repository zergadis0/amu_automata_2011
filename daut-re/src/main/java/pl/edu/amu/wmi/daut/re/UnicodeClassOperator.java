package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.CharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;


class UnknownUnicodeClassException extends RuntimeException {
}

/**
 * Unicode class.
 */
public class UnicodeClassOperator extends NullaryRegexpOperator {

    private String str;

    private static final Map<String, String> MAP_OF_UNICODE_CLASS = createMap();

    private static Map<String, String> createMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("Arabic", UnicodeBlock.ARABIC.toString());
        result.put("Armenian", UnicodeBlock.ARMENIAN.toString());
        result.put("Bengali", UnicodeBlock.BENGALI.toString());
        result.put("Bopomfo", UnicodeBlock.BOPOMOFO.toString());
        result.put("Braille", UnicodeBlock.BRAILLE_PATTERNS.toString());
        result.put("Buhid", UnicodeBlock.BUHID.toString());
        result.put("Cherokee", UnicodeBlock.CHEROKEE.toString());
        result.put("Cyrillic", UnicodeBlock.CYRILLIC.toString());
        result.put("Deseret", UnicodeBlock.DESERET.toString());
        result.put("Devanagari", UnicodeBlock.DEVANAGARI.toString());
        result.put("Ethiopic", UnicodeBlock.ETHIOPIC.toString());
        result.put("Gregorian", UnicodeBlock.GEORGIAN.toString());
        result.put("Gothic", UnicodeBlock.GOTHIC.toString());
        result.put("Greek", UnicodeBlock.GREEK.toString());
        result.put("Gujarati", UnicodeBlock.GUJARATI.toString());
        result.put("Gurmukhi", UnicodeBlock.GURMUKHI.toString());
        result.put("Hangul", UnicodeBlock.HANGUL_JAMO.toString());
        result.put("Hanunoo", UnicodeBlock.HANUNOO.toString());
        result.put("Hebrew", UnicodeBlock.HEBREW.toString());
        result.put("Hiragana", UnicodeBlock.HIRAGANA.toString());
        result.put("Kanbun", UnicodeBlock.KANBUN.toString());
        result.put("Kannada", UnicodeBlock.KANNADA.toString());
        result.put("Katakana", UnicodeBlock.KATAKANA.toString());
        result.put("Khmer", UnicodeBlock.KHMER.toString());
        result.put("Lao", UnicodeBlock.LAO.toString());
        result.put("Latin", UnicodeBlock.LATIN_1_SUPPLEMENT.toString());
        result.put("Limbu", UnicodeBlock.LIMBU.toString());
        result.put("Linear B", UnicodeBlock.LINEAR_B_SYLLABARY.toString());
        result.put("Malayalam", UnicodeBlock.MALAYALAM.toString());
        result.put("Mongolian", UnicodeBlock.MONGOLIAN.toString());
        result.put("Myanmar", UnicodeBlock.MYANMAR.toString());
        result.put("Ogham", UnicodeBlock.OGHAM.toString());
        result.put("Old Italic", UnicodeBlock.OLD_ITALIC.toString());
        result.put("Oriya", UnicodeBlock.ORIYA.toString());
        result.put("Osmanya", UnicodeBlock.OSMANYA.toString());
        result.put("Runic", UnicodeBlock.RUNIC.toString());
        result.put("Shavian", UnicodeBlock.SHAVIAN.toString());
        result.put("Sinhala", UnicodeBlock.SINHALA.toString());
        result.put("Syriac", UnicodeBlock.SYRIAC.toString());
        result.put("Tagalog", UnicodeBlock.TAGALOG.toString());
        result.put("Tagbanwa", UnicodeBlock.TAGBANWA.toString());
        result.put("Tai Le", UnicodeBlock.TAI_LE.toString());
        result.put("Tamil", UnicodeBlock.TAMIL.toString());
        result.put("Telugu", UnicodeBlock.TELUGU.toString());
        result.put("Thaana", UnicodeBlock.THAANA.toString());
        result.put("Thai", UnicodeBlock.THAI.toString());
        result.put("Tibetan", UnicodeBlock.TIBETAN.toString());
        result.put("Ugaritic", UnicodeBlock.UGARITIC.toString());
        result.put("Yijing", UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS.toString());
        result.put("Yi", UnicodeBlock.YI_SYLLABLES.toString()
                + UnicodeBlock.YI_RADICALS.toString());

        return Collections.unmodifiableMap(result);
    }

    /**
     * konstruktor Unicode classes.
     */
    public UnicodeClassOperator(String a) {
        if (a.substring(0, 1).equals("\\p"))
            a = a.substring(2);
        if (a.substring(0, 0).equals("{"))
            a = a.substring(1);
        if (a.endsWith("}"))
            a = a.substring(0, a.length() - 1);

        str = MAP_OF_UNICODE_CLASS.get(a);
        if (str == null)
            throw new UnknownUnicodeClassException();
    }

    /**
     * Generuje automat.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        automaton.addTransition(q0, q1, new CharClassTransitionLabel(str));

        return automaton;
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new UnicodeClassOperator(params.get(0));
        }

        @Override
        public int numberOfParams() {
            return 1;
        }
    }
}

