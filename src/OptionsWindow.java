import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class OptionsWindow extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 500;
    public static final String WINDOW_TITLE = "Настройки игры";

    private MainWindow mainWindow;
    private GameOptions gameOptions;

    private JPanel jpOptions;
    private JPanel jpControls;

    private RadioGroup[] playersOptions = new RadioGroup[GameOptions.NUMBER_OF_PLAYERS];
    private LinkedSliders linkedSliders;


    OptionsWindow (MainWindow mainWindow, GameOptions gameOptions)
    {
        this.mainWindow = mainWindow;
        this.gameOptions = gameOptions;

        initOptionsWindow();
    }


    // Инициализируем окно
    private void initOptionsWindow ()
    {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle(WINDOW_TITLE);
        this.setLocationRelativeTo(mainWindow);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        // Панель для кнопок сохранения и отмены ставим вниз
        jpControls = new JPanel();
        jpControls.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(jpControls,BorderLayout.SOUTH);

        // Панель для настройки опций ставим в центр
        jpOptions = new JPanel();
        jpOptions.setLayout(new BoxLayout(jpOptions, BoxLayout.Y_AXIS));
        add(jpOptions, BorderLayout.CENTER);

        addControls();
        addPlayersOptions();
        addSliders();
    }


    // Добавляем управляющие кнопки
    private void addControls ()
    {
        // Иначе не знаю, как из листнера на само окно указать,
        // ибо this закономерно указывает на сам листнер
        OptionsWindow me = this;

        ActionListener lstSaveClick = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                save();
                dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
            }
        };

        JButton btnSave = new JButton("Сохранить изменения");
        jpControls.add(btnSave);
        btnSave.addActionListener(lstSaveClick);

        ActionListener lstCancelClick = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
            }
        };

        JButton btnCancel = new JButton("Отмена");
        jpControls.add(btnCancel);
        btnCancel.addActionListener(lstCancelClick);
    }


    // Показываем настройки типов игроков
    private void addPlayersOptions ()
    {
        for (int i=0; i<playersOptions.length; i++)
        {
            String playerName = gameOptions.getPlayerName(i+1);
            String[] descriptions = {Constants.HUMAN, Constants.COMPUTER};
            int selectedNumber;

            if (gameOptions.isHuman(i+1))
                selectedNumber = 0;
            else
                selectedNumber = 1;

            playersOptions[i] = new RadioGroup(playerName, descriptions, selectedNumber);
            jpOptions.add(playersOptions[i]);
        }
    }


    // Показываем слайдерами настройки размера поля и условий победы
    private void addSliders ()
    {
        linkedSliders = new LinkedSliders(gameOptions);
        jpOptions.add(linkedSliders);
    }


    // Сохраняем текущий вариант настроек в gameOptions
    public void save ()
    {
        for (int i=0; i<playersOptions.length; i++)
        {
            if (playersOptions[i].getState()==0)
                gameOptions.setHuman(i+1,true);
            else
                gameOptions.setHuman(i+1,false);
        }

        gameOptions.setFieldSize(linkedSliders.getFieldSize());
        gameOptions.setWinCondition(linkedSliders.getWinCondition());
    }

}
