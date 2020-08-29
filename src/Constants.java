// Константы, которые потенциально могут "принадлежать" разным классам

import java.awt.*;

public abstract class Constants
{
    public static final String HUMAN = "Human";
    public static final String COMPUTER = "Computer";

    public static final byte ZERO_VALUE = 0;
    public static final byte CROSS_VALUE = 1;
    public static final byte EMPTY_VALUE = -1;

    public static final String EMPTY_SIGN = " - ";
    public static final String ZERO_SIGN = " O ";
    public static final String CROSS_SIGN = " X ";

    public static final int LINE_V_SPACE = 5;
    public static final int LINE_H_SPACE = 10;
    public static final int LINE_HEIGHT = 20;

    public static final int HEADER_H_SPACE = 50;
    public static final int HEADER_V_SPACE = 10;
    public static final int HEADER_HEIGHT = 20;

    public static final Font HEADER_FONT = new Font ("Courier", Font.BOLD,15);


}
