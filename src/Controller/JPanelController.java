package Controller;


import javax.swing.*;
import java.awt.*;

public class JPanelController extends JPanel {
    public int num;
    public JPanel p;

    public void switchToView(JPanel Panel, int n) {
        p=Panel;
        this.removeAll();
        this.add(Panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        num=n;
    }
}
