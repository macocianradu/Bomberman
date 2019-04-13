/*
 * Decompiled with CFR 0.139.
 */
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver {
    private JButton retry;
    private JPanel overPanel;
    private JLabel overTxt;
    private JButton goBack;
    static JFrame gameOver = new JFrame("Bomberman");

    public GameOver() {
        this.$$$setupUI$$$();
        StartMenu.createWindow(gameOver, this.overPanel);
        this.retry.setSize((int)(Settings.resWidth * 27.0), (int)(Settings.resHeight * 0.2));
        this.goBack.setSize((int)(Settings.resWidth * 27.0), (int)(Settings.resHeight * 0.2));
        this.overTxt.setSize((int)(Settings.resWidth * 27.0), (int)(Settings.resHeight * 0.2));
        if (Creep.creepList.size() == 0) {
            this.overTxt.setText("you won");
        } else {
            this.overTxt.setText("Game Over");
        }
        this.retry.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameOver.retry();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.goBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameOver.gameOver.dispose();
                    new StartMenu();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void retry() throws IOException {
        gameOver.dispose();
        Game.paused = false;
        Game.game.setVisible(true);
    }

    private /* synthetic */ void $$$setupUI$$$() {
        JButton jButton;
        JPanel jPanel;
        JButton jButton2;
        JLabel jLabel;
        this.overPanel = jPanel = new JPanel();
        jPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1, false, false));
        Spacer spacer = new Spacer();
        jPanel.add((Component)spacer, new GridConstraints(0, 0, 1, 1, 0, 2, 1, 6, null, new Dimension(-1, 100), null));
        this.retry = jButton2 = new JButton();
        jButton2.setText("Retry");
        jPanel.add((Component)jButton2, new GridConstraints(3, 0, 1, 1, 0, 1, 3, 0, null, null, null));
        Spacer spacer2 = new Spacer();
        jPanel.add((Component)spacer2, new GridConstraints(2, 0, 1, 1, 0, 2, 1, 6, null, null, null));
        this.goBack = jButton = new JButton();
        jButton.setText("Back to Main Menu");
        jPanel.add((Component)jButton, new GridConstraints(3, 2, 1, 1, 0, 1, 3, 0, null, null, null));
        this.overTxt = jLabel = new JLabel();
        jLabel.setText("Game Over!");
        jPanel.add((Component)jLabel, new GridConstraints(1, 1, 1, 1, 0, 0, 0, 0, null, null, null));
    }

    public /* synthetic */ JComponent $$$getRootComponent$$$() {
        return this.overPanel;
    }

}

