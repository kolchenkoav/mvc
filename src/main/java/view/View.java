package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

//Метод setDefaultCloseOperation класса JFrame.
// С помощью данного метода разработчик говорит JFrame, который он создает, что необходимо сделать при закрытии окна.
// Очень часто в этот метод передают константу JFrame.EXIT_ON_CLOSE.
// Если методу setDefaultCloseOperation передать эту константу, то при закрытии окна приложение будет прекращать работу.

public class View extends JFrame {
    private JTextArea textArea;
    private JButton sendButton;
    private  JLabel info;

    //private static WindowListener WL;

    public void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 800;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        //JFrame frame = new JFrame();

        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        setTitle("MVC");

        setResizable(false);
        //frame.addWindowListener(WL);

        setDefaultCloseOperation(EXIT_ON_CLOSE);


        textArea = new JTextArea();
        sendButton = new JButton("Send");
        info = new JLabel("Info");

        add(textArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
        add(info, BorderLayout.NORTH);

        sendButton.addActionListener(e -> {
            int pin;
            String textPin = this.textArea.setText();
            pin = Integer.parseInt(textPin);

            info.setText("Got it");

            // обработка Pin-кода
        });

        setVisible(true);
        //frame.show();
        //setSize(400, 300);
        //setVisible(true);
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }
}
