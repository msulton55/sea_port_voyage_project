package msultont;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProgramHelp {
    public static void main() {

        SwingUtilities.invokeLater(() -> {

            JFrame errorFrame = new JFrame();
            JDialog errorDialog = new JDialog(errorFrame);
            JPanel errorPanel = new JPanel();
            JLabel errorMessage = new JLabel();

            errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));

            try {
                File file = new File("help.txt");

                if (!Desktop.isDesktopSupported()) {
                    errorMessage.setText("Desktop is not supported!");
                    errorDialog.add(errorMessage, BorderLayout.CENTER);
                    errorDialog.setTitle("Unsupported Platform");
                    errorDialog.setBounds(567, 279, 400, 200);
                    errorDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    errorDialog.setVisible(true);
                }

                Desktop desktop = Desktop.getDesktop();

                if (file.exists()) {
                    desktop.open(file);
                } else {
                    errorMessage.setText("File not found!");
                    errorPanel.add(Box.createRigidArea(new Dimension(0, 30)));
                    errorPanel.add(errorMessage);
                    errorDialog.add(errorPanel, BorderLayout.CENTER);
                    errorDialog.setTitle("File not found");
                    errorDialog.setBounds(567, 279, 400, 100);
                    errorDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    errorDialog.setVisible(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
