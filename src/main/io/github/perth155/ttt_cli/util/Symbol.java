package io.github.perth155.ttt_cli.util;

public enum Symbol {
    X,
    O,
    EMPTY;

    /**
     * Get the opponents symbol from players symbol.
     * @param s provided symbol.
     * @return opponents symbol.
     */
    public static Symbol oppositeSymbol(Symbol s)
    {
        if(s == Symbol.X)
            return Symbol.O;
        else
            return Symbol.X;
    }

    public static String toString(Symbol s)
    {
        if(s == Symbol.X)
            return "X";
        else
            return "O";
    }
}

