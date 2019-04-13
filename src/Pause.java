/*
 * Decompiled with CFR 0.139.
 */
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Pause {
    private JPanel pausePanel;
    private JButton quit;
    private JButton resume;
    private JSlider volume;
    private JButton setVolume;

    public Pause() {
        this.$$$setupUI$$$();
        final JFrame Pause2 = new JFrame("Bomberman - Paused");
        Pause2.setVisible(true);
        Pause2.setContentPane(this.pausePanel);
        Pause2.setSize(new Dimension((int)(Settings.resWidth * 0.75), (int)(Settings.resHeight * 0.9)));
        Pause2.setDefaultCloseOperation(0);
        Pause2.setLocationRelativeTo(null);
        this.volume.setValue(((int)Settings.volume + 34) * 100 / 40);
        this.resume.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Game.paused = false;
                Pause.this.pausePanel.removeAll();
                Pause2.dispose();
            }
        });
        this.setVolume.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsMenu.setVolume(Pause.this.volume.getValue());
            }
        });
        this.quit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private /* synthetic */ void $$$setupUI$$$() {
        JButton jButton;
        JButton jButton2;
        JPanel jPanel;
        JButton jButton3;
        JSlider jSlider;
        this.pausePanel = jPanel = new JPanel();
        jPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1, false, false));
        this.quit = jButton2 = new JButton();
        jButton2.setText("Quit");
        jPanel.add((Component)jButton2, new GridConstraints(3, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        this.resume = jButton3 = new JButton();
        jButton3.setText("Resume");
        jPanel.add((Component)jButton3, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        this.volume = jSlider = new JSlider();
        jSlider.setMajorTickSpacing(10);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintLabels(true);
        jSlider.setPaintTicks(false);
        jSlider.setValue(75);
        jPanel.add((Component)jSlider, new GridConstraints(1, 1, 1, 1, 0, 0, 6, 0, null, null, null));
        this.setVolume = jButton = new JButton();
        jButton.setText("Set Volume");
        jPanel.add((Component)jButton, new GridConstraints(2, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        Spacer spacer = new Spacer();
        jPanel.add((Component)spacer, new GridConstraints(1, 0, 1, 1, 0, 1, 6, 1, null, null, null));
        Spacer spacer2 = new Spacer();
        jPanel.add((Component)spacer2, new GridConstraints(1, 2, 1, 1, 0, 1, 6, 1, null, null, null));
    }

    public /* synthetic */ JComponent $$$getRootComponent$$$() {
        return this.pausePanel;
    }

}

