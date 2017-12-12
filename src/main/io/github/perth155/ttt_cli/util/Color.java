package io.github.perth155.ttt_cli.util;

/**
 * Class contains some ANSI escape codes to print color to console
 * using some protected static variables, that are used as constants.
 */

public class Color
{
    protected static final String BLACK = "\033[1;90m";   // X Foreground     
    protected static final String WHITE = "\033[1;97m"; // O Foreground

    protected static final String WHITE_BG = "\033[47m"; // X Background
    protected static final String BLACK_BG = "\033[40m"; // O Background

    protected static final String RESET = "\033[0m"; 
}
