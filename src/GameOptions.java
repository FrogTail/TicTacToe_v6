import java.io.IOException;

public class GameOptions
{
    public static final int MIN_FIELD_SIZE = 3;
    public static final int MAX_FIELD_SIZE = 20;

    public static final int NUMBER_OF_PLAYERS = 2;

    public static final int DEFAULT_FIELD_SIZE = 9;
    public static final int DEFAULT_WIN_CONDITION = 5;

    private String[] playersNames;
    private boolean[] humanPlayers;
    private byte[] playersSignsValues;
    private String[] playersSigns;

    private int winCondition;
    private int fieldSize;

    GameOptions()
    {
        setDefaults();
    }


    public void setDefaults ()
    {
        fieldSize = DEFAULT_FIELD_SIZE;
        winCondition = DEFAULT_WIN_CONDITION;

        playersNames = new String[] {"Player 1", "Player 2"};
        humanPlayers = new boolean[] {true, false};
        playersSignsValues = new byte[] {Constants.CROSS_VALUE, Constants.ZERO_VALUE};
        playersSigns = new String[] {Constants.CROSS_SIGN, Constants.ZERO_SIGN};
    }


    // Загрузка и сохранение настроек в файл (потом реализую)
    public void loadFromFile ()
    {}

    public void saveToFile ()
    {}


    // Сеттеры
    public void setFieldSize (int fieldSize)
    {
        // Насколько правильно тут приводить значения к допустимым?
        // Или лучше кинуть эксепшн, если присланное значение не вписывается?
        if (fieldSize<MIN_FIELD_SIZE)
        {
            fieldSize=MIN_FIELD_SIZE;
        }
        else if (fieldSize>MAX_FIELD_SIZE)
        {
            fieldSize=MAX_FIELD_SIZE;
        }

        this.fieldSize=fieldSize;

        setWinCondition(winCondition);
    }

    public void setWinCondition(int winCondition)
    {
        if (winCondition<MIN_FIELD_SIZE)
        {
            winCondition = MIN_FIELD_SIZE;
        }
        else if (winCondition>fieldSize)
        {
            winCondition = fieldSize;
        }

        this.winCondition = winCondition;
    }

    public void setHuman (int playerNumber, boolean isHuman)
    {
        // Нужно ли в подобных случаях метать собственный эксепшн,
        // если присланный номер выходит за рамки массива,
        // чтобы тот, кто будет пользоваться этой функцией снаружи
        // знал, что может прислать неправильное значение
        // и надо ловить и обрабатывать эксепшн?

        humanPlayers[playerNumber-1] = isHuman;
    }

    // Геттеры
    public int getFieldSize()
    {return fieldSize;}

    public int getWinCondition()
    {return winCondition;}

    public boolean isHuman (int playerNumber)
    {
        // Не знаю, как лучше - делать:
        // чтобы получаемый методом номер соответствовал номеру игрока
        // или соответствовал номеру в массиве?
        return humanPlayers[playerNumber-1];
    }

    public String getPlayerName (int playerNumber)
    {return playersNames[playerNumber-1];}

    public byte getPlayerSignValue (int playerNumber)
    {return playersSignsValues[playerNumber-1];}

    public String getPlayerSign (int playerNumber)
    {return playersSigns[playerNumber-1];}

}
