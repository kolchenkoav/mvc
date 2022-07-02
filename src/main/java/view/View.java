package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

//Метод setDefaultCloseOperation класса JFrame.
// С помощью данного метода разработчик говорит JFrame, который он создает, что необходимо сделать при закрытии окна.
// Очень часто в этот метод передают константу JFrame.EXIT_ON_CLOSE.
// Если методу setDefaultCloseOperation передать эту константу, то при закрытии окна приложение будет прекращать работу.

public class View extends JFrame {
    private JTextArea textArea;
    private JButton sendButton;
    private  JLabel info;

    private Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }

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
        info = new JLabel("");

        add(textArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
        add(info, BorderLayout.NORTH);

        /**
         * Обработка нажатия кнопки
         */
        sendButton.addActionListener(e -> {
            int pin;
            String textPin = this.textArea.getText().trim();
            textArea.setText("");
            try {
                String pattern = "(?=.*[0-9]).{4,}";
                if (textPin.matches(pattern)) {
                    pin = Integer.parseInt(textPin);
                    info.setText("Got it: " + textPin + " waiting...");

                    // обработка Pin-кода
                    AtomicBoolean result = new AtomicBoolean(false);
                    Thread thread = new Thread(() -> {
                        sendButton.setEnabled(false);
                        textArea.setEnabled(false);
                        result.set(controller.checkPin(pin));
                        String resultMessage;
                        if (result.get()) {
                            resultMessage = "Right";
                        } else {
                            resultMessage = "Wrong, try again";
                        }
                        info.setText(resultMessage);
                        textArea.setText("");
                        textArea.setEnabled(true);
                        sendButton.setEnabled(true);
                    });
                    thread.start();

                } else {
                    info.setText("Only numbers allowed...");
                    textArea.setText("");
                }

            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }

        });

        setVisible(true);
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }
}
