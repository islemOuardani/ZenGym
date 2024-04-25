package IHM;

import Config.ColorPalette;

import javax.swing.*;
import java.awt.*;

public class IHMPersonnels extends JPanel {
    JLabel lbSoon;
    public IHMPersonnels() {
        this.setLayout(new BorderLayout());
        this.setBackground(ColorPalette.BACKGROUND.getColor());
        this.setPreferredSize(new Dimension(500,400));
        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(100,200));
        empty.setBackground(new Color(0,0,0,0));
        this.add(empty,BorderLayout.NORTH);

        lbSoon = new JLabel("COMING SOON");
        lbSoon.setFont(new Font("Franklin Gothic Demi Cond",Font.BOLD|Font.ITALIC,60));
        lbSoon.setForeground(ColorPalette.PRIMARY.getColor());
        this.add(lbSoon,BorderLayout.CENTER);
    }
}
