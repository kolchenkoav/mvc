import controller.Controller;
import model.Model;
import view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        // требование ORACLE запуск окна в потоке
        SwingUtilities.invokeLater(() -> {
            view.init();
        });
    }
}
