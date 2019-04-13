/*
 * Decompiled with CFR 0.139.
 */
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class SettingsMenu {
    public JPanel settingsPanel;
    private JTextField width;
    private JTextField height;
    private JButton setRes;
    private JSlider volume;
    private JButton setVol;
    private JButton back;
    private JLabel widthLabel;
    private JLabel heightLabel;
    static JFrame settings = new JFrame("Bomberman");
    public static boolean exists = false;

    public SettingsMenu() throws IOException {
        this.$$$setupUI$$$();
        this.volume.setValue(((int)Settings.volume + 34) * 100 / 40);
        StartMenu.createWindow(settings, this.settingsPanel);
        this.width.setText(Double.toString(Settings.resWidth));
        this.height.setText(Double.toString(Settings.resHeight));
        this.setRes.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsMenu.settings.dispose();
                SettingsMenu.this.setResolution();
            }
        });
        this.setVol.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsMenu.setVolume(SettingsMenu.this.volume.getValue());
            }
        });
        this.back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsMenu.settings.dispose();
                SettingsMenu.this.goBack();
            }
        });
    }

    public void setResolution() {
        Settings.resWidth = Double.valueOf(this.width.getText());
        Settings.resHeight = Double.valueOf(this.height.getText());
        StartMenu.createWindow(settings, this.settingsPanel);
    }

    public static void setVolume(int volume) {
        Settings.volume = (float)volume * 40.0f / 100.0f - 34.0f;
        Main.bgSound.setVolume(Settings.volume);
    }

    public void goBack() {
        try {
            new StartMenu();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private /* synthetic */ void $$$setupUI$$$() {
        JSlider jSlider;
        JLabel jLabel;
        JButton jButton;
        JButton jButton2;
        JTextField jTextField;
        JPanel jPanel;
        JTextField jTextField2;
        JLabel jLabel2;
        JButton jButton3;
        this.settingsPanel = jPanel = new JPanel();
        jPanel.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1, false, false));
        this.width = jTextField = new JTextField();
        jPanel.add((Component)jTextField, new GridConstraints(1, 0, 1, 1, 9, 1, 6, 0, null, new Dimension(150, -1), null));
        this.height = jTextField2 = new JTextField();
        jPanel.add((Component)jTextField2, new GridConstraints(1, 2, 1, 1, 9, 1, 6, 0, null, new Dimension(150, -1), null));
        this.setRes = jButton = new JButton();
        jButton.setText("Set Resolution");
        jPanel.add((Component)jButton, new GridConstraints(2, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        this.volume = jSlider = new JSlider();
        jSlider.setMajorTickSpacing(10);
        jSlider.setMaximum(100);
        jSlider.setMinimum(0);
        jSlider.setPaintLabels(true);
        jSlider.setPaintTicks(false);
        jPanel.add((Component)jSlider, new GridConstraints(3, 1, 1, 1, 8, 1, 6, 0, null, null, null));
        this.setVol = jButton3 = new JButton();
        jButton3.setText("Set Volume");
        jPanel.add((Component)jButton3, new GridConstraints(4, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        this.back = jButton2 = new JButton();
        jButton2.setText("Back");
        jPanel.add((Component)jButton2, new GridConstraints(5, 1, 1, 1, 0, 1, 3, 0, null, null, null));
        this.widthLabel = jLabel2 = new JLabel();
        jLabel2.setText("Width:");
        jPanel.add((Component)jLabel2, new GridConstraints(0, 0, 1, 1, 10, 0, 0, 0, null, null, null));
        this.heightLabel = jLabel = new JLabel();
        jLabel.setText("Height:");
        jPanel.add((Component)jLabel, new GridConstraints(0, 2, 1, 1, 10, 0, 0, 0, null, null, null));
    }

    public /* synthetic */ JComponent $$$getRootComponent$$$() {
        return this.settingsPanel;
    }

}

