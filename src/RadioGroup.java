import javax.swing.*;
import java.awt.*;

public class RadioGroup extends JPanel
{
    public static final int HEIGHT = 90;

    private Label header;
    private JRadioButton[] radioButtons;
    private ButtonGroup buttonGroup = new ButtonGroup();

    private int yShift = 0;

    RadioGroup (String headerText, String[] descriptions, int selectedNumber)
    {
        setLayout(null);
        setPreferredSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));
        setMaximumSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));
        setMinimumSize(new Dimension(OptionsWindow.DEFAULT_WIDTH,HEIGHT));

        buildHeader(headerText);
        buildRButtons(descriptions,selectedNumber);
    }


    // Создаем набор кнопок
    private void buildRButtons (String[] descriptions, int selectedNumber)
    {
        radioButtons = new JRadioButton[descriptions.length];

        for (int i=0; i<radioButtons.length; i++)
        {
            radioButtons[i] = new JRadioButton(descriptions[i]);

            radioButtons[i].setBounds(Constants.LINE_H_SPACE, Constants.LINE_V_SPACE +yShift, 200,Constants.LINE_HEIGHT);
            yShift += Constants.LINE_V_SPACE + Constants.LINE_HEIGHT;

            buttonGroup.add(radioButtons[i]);

            if (i==selectedNumber)
                radioButtons[i].setSelected(true);

            add(radioButtons[i]);
        }
    }


    // Создаем типовой заголовок
    private void buildHeader (String headerText)
    {
        header = new Label(headerText);
        header.setFont(Constants.HEADER_FONT);
        header.setBounds(Constants.HEADER_H_SPACE,Constants.HEADER_V_SPACE,200,Constants.HEADER_HEIGHT);
        yShift += Constants.HEADER_V_SPACE + Constants.HEADER_HEIGHT;
        add(header);
    }

    // Возвращаем номер выбранного пункта
    public int getState ()
    {
        for (int i=0; i<radioButtons.length; i++)
        {
            if (radioButtons[i].isSelected())
                return i;
        }

        return -1;
    }
}
