package root.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ActionResultFrame extends JFrame {
    private static ActionResultFrame instance;
    private JFrame callingFrame;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JList<Object> resultList;
    private JPanel returnPanel;
    private JButton returnToActionSelectionButton;
    
    private ActionResultFrame(final String title) {
        super(title);
        setContentPane(resultPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        returnToActionSelectionButton.addActionListener(this::onMouseClick);
    }
    
    private void onMouseClick(final ActionEvent e) {
        final JButton button = (JButton) e.getSource();
        setVisible(false);
        if (button.equals(returnToActionSelectionButton)) {
            callingFrame.setVisible(true);
        }
    }
    
    public static ActionResultFrame getInstance() {
        if (null == instance) {
            instance = new ActionResultFrame("Register Management App v1.0");
        }
        return instance;
    }
    
    public void load(final String resultTitle, final Object[] resultArray, final JFrame callingFrame) {
        resultLabel.setText(resultTitle);
        resultList.setListData(resultArray);
        this.callingFrame = callingFrame;
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        setVisible(true);
    }
}


