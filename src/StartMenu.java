/*
 * Decompiled with CFR 0.139.
 */
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu
extends JPanel {
    Image img;
    public JButton startGame;
    public JPanel mainPanel;
    public JButton settings;
    public JButton exit;
    private JLabel title;
    static JFrame mainMenu = new JFrame("Bomberman");
    static Game play;

    public StartMenu() throws IOException {
        this.$$$setupUI$$$();
        this.img = ImageIO.read(StartMenu.class.getResource("/Img/Title.png"));
        StartMenu.createWindow(mainMenu, this.mainPanel);
        BufferedImage resizedImage = (BufferedImage)this.img;
        ImageIcon icon = new ImageIcon(StartMenu.resize(resizedImage, (int)Settings.resWidth, (int)(Settings.resHeight * 0.4)));
        this.title.setIcon(icon);
        this.startGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                StartMenu.mainMenu.dispose();
                try {
                    StartMenu.this.startGame();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.settings.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                StartMenu.mainMenu.setVisible(false);
                try {
                    StartMenu.this.settings();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    void startGame() throws IOException {
        Settings.Dim = Settings.setDim(Map.layout.length, Map.layout[0].length);
        Creep.populateCreeps();
        play = new Game();
    }

    void settings() throws IOException {
        new SettingsMenu();
    }

    public static void createWindow(JFrame window, JPanel mainPanel) {
        window.setVisible(true);
        window.setContentPane(mainPanel);
        window.setSize((int)Settings.resWidth, (int)Settings.resHeight);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(3);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, 4);
        BufferedImage bimg = new BufferedImage(newW, newH, 2);
        Graphics2D g2d = bimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return bimg;
    }

    private /* synthetic */ void $$$setupUI$$$() {
        JButton jButton;
        JButton jButton2;
        JPanel jPanel;
        JLabel jLabel;
        JButton jButton3;
        this.mainPanel = jPanel = new JPanel();
        jPanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        this.exit = jButton2 = new JButton();
        jButton2.setText("Exit");
        jPanel.add((Component)jButton2, new GridConstraints(5, 0, 1, 1, 0, 0, 3, 0, null, new Dimension(100, 35), null));
        this.startGame = jButton3 = new JButton();
        jButton3.setText("Start Game");
        jPanel.add((Component)jButton3, new GridConstraints(1, 0, 1, 1, 0, 0, 3, 0, null, new Dimension(100, 35), null));
        this.settings = jButton = new JButton();
        jButton.setText("Settings");
        jPanel.add((Component)jButton, new GridConstraints(3, 0, 1, 1, 0, 0, 3, 0, null, new Dimension(100, 35), null));
        Spacer spacer = new Spacer();
        jPanel.add((Component)spacer, new GridConstraints(2, 0, 1, 1, 0, 2, 1, 6, null, null, null));
        Spacer spacer2 = new Spacer();
        jPanel.add((Component)spacer2, new GridConstraints(4, 0, 1, 1, 0, 2, 1, 6, null, null, null));
        this.title = jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon(this.getClass().getResource("/Title.png")));
        jLabel.setText("");
        jPanel.add((Component)jLabel, new GridConstraints(0, 0, 1, 1, 0, 0, 0, 0, null, new Dimension(-1, 250), null));
    }

    public /* synthetic */ JComponent $$$getRootComponent$$$() {
        return this.mainPanel;
    }

}

