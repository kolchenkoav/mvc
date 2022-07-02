package model;

import java.util.concurrent.TimeUnit;

public class Model {
    private int pin = 1234;

    public int getPin() {
        try {
            TimeUnit.SECONDS.sleep(5); // задержка 5 секунд для эмуляции длинного запроса
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
