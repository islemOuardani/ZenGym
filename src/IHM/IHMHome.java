package IHM;

import Config.ColorPalette;
import Model.ImagePanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IHMHome extends JPanel {
    ImagePanel bg;
    JLabel timeLabel,dateLabel;
    JPanel date;
    Timer timer,dater;
    public IHMHome() {
        this.setLayout(new BorderLayout());

        bg = new ImagePanel("ressources/bgHome.png");

        date = new JPanel(new FlowLayout());
        date.setBackground(new Color(0,0,0,0));
        date.setPreferredSize(new Dimension(400,50));
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Franklin Gothic Demi Cond",Font.BOLD,35));
        dateLabel.setForeground(Color.WHITE);
        date.add(dateLabel);
        dater = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDate();
            }
        });
        dater.start();

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Franklin Gothic Demi Cond",Font.BOLD,35));
        timeLabel.setForeground(Color.WHITE);
        date.add(timeLabel);
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();

        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(150,300));
        north.setBackground(new Color(0,0,0,0));
        bg.add(north,BorderLayout.NORTH);
        bg.add(date,BorderLayout.WEST);
        this.add(bg, BorderLayout.CENTER);
    }
    private void updateTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = sdf.format(now);
        timeLabel.setText(timeStr);
        this.repaint();
        this.revalidate();
    }
    private void updateDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String timeStr = sdf.format(now);
        dateLabel.setText(timeStr);
        this.repaint();
        this.revalidate();
    }
}
