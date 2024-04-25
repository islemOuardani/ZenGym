package Model;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.extras.FlatSVGIcon;


public class SvgPanel extends JPanel {
    private FlatSVGIcon svgIcon;

    public SvgPanel(String svgPath) {
        svgIcon = new FlatSVGIcon(svgPath);

        setPreferredSize(new Dimension(svgIcon.getIconWidth(), svgIcon.getIconHeight()));
        setMinimumSize(new Dimension(svgIcon.getIconWidth(), svgIcon.getIconHeight()));
        setMaximumSize(new Dimension(svgIcon.getIconWidth(), svgIcon.getIconHeight()));
        setSize(new Dimension(svgIcon.getIconWidth(), svgIcon.getIconHeight()));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        svgIcon.paintIcon(this, g, 0, 0);
    }
}
