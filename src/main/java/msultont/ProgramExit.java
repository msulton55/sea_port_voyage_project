package msultont;

import javax.swing.*;
import java.awt.*;

public class ProgramExit {
    public static void main() {
        SwingUtilities.invokeLater(() -> {

            JFrame exitFrame = new JFrame();
            JDialog exitDialog = new JDialog(exitFrame, "Program Exit");
            JPanel headerPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JLabel exitLabel = new JLabel("Are you sure?");
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");

            exitDialog.setBounds(620, 327, 300, 150);

            yesButton.addActionListener(e13 -> System.exit(1));
            noButton.addActionListener(e12 -> exitFrame.dispose());

            exitLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            exitLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            headerPanel.add(exitLabel);

            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            buttonPanel.add(Box.createRigidArea(new Dimension(80, 0)));
            buttonPanel.add(yesButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
            buttonPanel.add(noButton);

            exitDialog.add(headerPanel, BorderLayout.PAGE_START);
            exitDialog.add(buttonPanel, BorderLayout.CENTER);
            exitDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            exitDialog.setVisible(true);

        });
    }
}
