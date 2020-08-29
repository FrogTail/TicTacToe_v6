import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 600;
    public static final String WINDOW_TITLE = "Tic-Tac-Toe v6";

    private JButton btnNewGame = new JButton("Новая игра");
    private JButton btnOptions = new JButton("Изменить настройки");
    private JButton btnSurrender = new JButton("Сдаться");
    private JPanel panelMainMenu = new JPanel(new GridLayout(1,3));

    private Field field;

    private GameOptions gameOptions;

    MainWindow ()
    {
        gameOptions = new GameOptions();

        initMainWindow();
    }


    // Инициализируем главное окно
    private void initMainWindow ()
    {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(WINDOW_TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.add(panelMainMenu,BorderLayout.NORTH);
        panelMainMenu.add(btnNewGame);
        panelMainMenu.add(btnSurrender);
        panelMainMenu.add(btnOptions);

        initMainMenuButtons();
        showSplashScreen();

        // !!! ВОТ !!! отсюда это поле нормально добавляется и видно
        //field = new Field(gameOptions.getFieldSize());
        //add(field, BorderLayout.CENTER);
    }


    // Инициализируем кнопки главного меню
    private void initMainMenuButtons ()
    {
        ActionListener lstBtnNewGame = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {startNewGame();}
        };

        ActionListener lstBtnOptions = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {openOptionsWindow();}
        };

        ActionListener lstBtnSurrender = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {surrender();}
        };

        btnNewGame.addActionListener(lstBtnNewGame);
        btnOptions.addActionListener(lstBtnOptions);
        btnSurrender.addActionListener(lstBtnSurrender);
    }


    // Экран заставки
    void showSplashScreen ()
    {
        btnNewGame.setEnabled(true);
        btnOptions.setEnabled(true);
        btnSurrender.setEnabled(false);
    }


    // Новая игра
    private void startNewGame ()
    {
        btnNewGame.setEnabled(false);
        btnOptions.setEnabled(false);
        btnSurrender.setEnabled(true);

        // !!! ВОТ !!! это поле отсюда не добавляется или его не видно
        // При копипасте кода в конец метода initMainWindow - все сразу отображается
        // Никак не могу понять, в чем проблема
        field = new Field(gameOptions.getFieldSize());
        add(field, BorderLayout.CENTER);
    }


    // Открыть окно опций
    private void openOptionsWindow ()
    {
        btnNewGame.setEnabled(false);
        btnOptions.setEnabled(false);
        btnSurrender.setEnabled(false);

        OptionsWindow optionsWindow = new OptionsWindow(this, gameOptions);

        // Не знаю, насколько это оптимально,
        // но это то, что удалось накопать и реализовать самому :)
        WindowListener wl = new WindowListener()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                showSplashScreen();
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        };

        optionsWindow.addWindowListener(wl);
    }


    // Сдаться
    void surrender ()
    {
        showSplashScreen();
    }


}
