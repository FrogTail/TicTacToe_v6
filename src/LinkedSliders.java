import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkedSliders extends JPanel
{
    public static final String FIELD_SIZE_PREFIX = "Field size - ";
    public static final String WIN_CONDITION_PREFIX = "Win condition - ";
    public static final String WIN_CONDITION_POSTFIX = " in a row";
    public static final int HEIGHT = 150;

    private JLabel fieldSizeHeader;
    private JLabel winConditionHeader;
    private JSlider jsFieldSize;
    private JSlider jsWinCondition;

    private int yShift = 0;

    private GameOptions gameOptions;

    LinkedSliders(GameOptions gameOptions)
    {
        this.gameOptions = gameOptions;

        setLayout(null);
        setPreferredSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));
        setMaximumSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));
        setMinimumSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));

        fieldSizeHeader = new JLabel();
        fieldSizeHeader.setText(FIELD_SIZE_PREFIX + gameOptions.getFieldSize());
        addHeader(fieldSizeHeader);

        jsFieldSize = new JSlider(GameOptions.MIN_FIELD_SIZE,GameOptions.MAX_FIELD_SIZE,gameOptions.getFieldSize());
        addSlider(jsFieldSize);

        winConditionHeader = new JLabel();
        winConditionHeader.setText(WIN_CONDITION_PREFIX + gameOptions.getWinCondition() + WIN_CONDITION_POSTFIX);
        addHeader(winConditionHeader);

        jsWinCondition = new JSlider(jsFieldSize.getMinimum(),jsFieldSize.getValue(),gameOptions.getWinCondition());
        addSlider(jsWinCondition);

        ChangeListener lstFieldSize = new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                int newMax = jsFieldSize.getValue();

                if (jsWinCondition.getValue()>newMax)
                    jsWinCondition.setValue(newMax);

                fieldSizeHeader.setText(FIELD_SIZE_PREFIX + jsFieldSize.getValue());
                winConditionHeader.setText(WIN_CONDITION_PREFIX + jsWinCondition.getValue() + WIN_CONDITION_POSTFIX);

                jsWinCondition.setMaximum(newMax);
            }
        };

        ChangeListener lstWinCondition = new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                winConditionHeader.setText(WIN_CONDITION_PREFIX + jsWinCondition.getValue() + WIN_CONDITION_POSTFIX);
            }
        };

        jsFieldSize.addChangeListener(lstFieldSize);
        jsWinCondition.addChangeListener(lstWinCondition);

    }


    // Настраиваем внешний вид заголовков и слайдеров
    private void addHeader (JLabel header)
    {
        header.setFont(Constants.HEADER_FONT);
        header.setBounds(Constants.HEADER_H_SPACE,Constants.HEADER_V_SPACE +yShift, 200, Constants.HEADER_HEIGHT);

        yShift += Constants.HEADER_V_SPACE + Constants.HEADER_HEIGHT;

        add(header);
    }

    private void addSlider (JSlider slider)
    {
        // Че-то странное.
        // При множителе 2 - слайдер прижимается вплотную к правому краю.
        // Есть ощущение, что длина слайдера некорректно считается.
        int width = OptionsWindow.DEFAULT_WIDTH - (Constants.LINE_H_SPACE * 3);
        slider.setBounds(Constants.LINE_H_SPACE,Constants.LINE_V_SPACE + yShift, width, Constants.LINE_HEIGHT);

        yShift += Constants.LINE_V_SPACE + Constants.LINE_HEIGHT;

        add(slider);
    }

    public int getFieldSize ()
    {return jsFieldSize.getValue();}

    public int getWinCondition ()
    {return jsWinCondition.getValue();}
}
