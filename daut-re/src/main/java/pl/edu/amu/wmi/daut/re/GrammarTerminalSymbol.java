package pl.edu.amu.wmi.daut.re;

/**
 * Klasa opisująca symbol terminalny gramatyki generatywnej.
 */
public class GrammarTerminalSymbol implements GrammarSymbol {

    private Character symbol;

    public GrammarTerminalSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean isTerminalSymbol() {
        return true;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }

}
