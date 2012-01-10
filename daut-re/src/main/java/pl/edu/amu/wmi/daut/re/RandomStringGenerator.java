package pl.edu.amu.wmi.daut.base;

import java.util.Random;

public class RandomStringGenerator {

    private final String alphabet;

    private Random random;

    private static final double lambda = 0.1;

public RandomStringGenerator(String alphabet) {
    random = new Random();
    if (alphabet == null) {
        throw new IllegalArgumentException("Alphabet can be empty - but cannot be null.");
    }
    StringBuilder alphabetNonRepetitive = new StringBuilder();
    for (int i = 0; i < alphabet.length(); i++) {
        char character = alphabet.charAt(i);
        if (!doesContains(alphabetNonRepetitive.toString(), character)) {
            alphabetNonRepetitive.append(character);
        }
    }
    this.alphabet = alphabetNonRepetitive.toString();
}

private boolean doesContains(String str, char characterToFind) {
    for (int i = 0; i < str.length(); i++) {
        char character = str.charAt(i);
        if (character.equals(characterToFind) {
            return true;
        }
    }
    return false;
}

    public String getRandomString() {
        StringBuffer sb = new StringBuffer();
        int length = 0;
        if (alphabet.length() != 0) {
            while (shouldWordBeLonger(length)) {
            //while (random.nextBoolean()) {
                char randomCharacter = getRandomCharacterFromAlphabet();
                sb.append(randomCharacter);
            }
        }
        return sb.toString();
    }

    private char getRandomCharacterFromAlphabet() {
        int characterPosition = random.nextInt(alphabet.length());
        return alphabet.charAt(characterPosition);
    }

    private boolean shouldWordBeLonger(int x) {
        return random.nextDouble() > getProbabilityDensity(x);
    }

    private double getProbabilityDensity(int x) {
        return lambda * Math.pow(Math.E, -x * lambda);
    }
}
