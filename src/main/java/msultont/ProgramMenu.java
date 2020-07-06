package msultont;

import javax.swing.*;
import java.awt.*;

public class ProgramMenu {
    public static void main() {
        SwingUtilities.invokeLater(() -> {

            JFrame menuFrame = new JFrame("Program Menu");
            JPanel menuPanel = new JPanel();
            JPanel headerPanel = new JPanel();
            JPanel bodyPanel = new JPanel();
            GroupLayout layout = new GroupLayout(bodyPanel);

            JLabel headerLabel = new JLabel("Choose program options");
            JButton normalButton = new JButton("Normal");
            JButton manualButton = new JButton("Manual");
            JButton adjacentButton = new JButton("Adjacent List");
            JButton helpButton = new JButton("Help");

            headerPanel.add(headerLabel);

            bodyPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup()
                                                            .addComponent(normalButton)
                                                            .addComponent(manualButton)
                                                            .addComponent(adjacentButton)
                                                            .addComponent(helpButton))
            );

            layout.linkSize(SwingConstants.HORIZONTAL, normalButton, manualButton, adjacentButton, helpButton);

            layout.setVerticalGroup(layout.createSequentialGroup()
                                          .addComponent(normalButton)
                                          .addComponent(manualButton)
                                          .addComponent(adjacentButton)
                                          .addComponent(helpButton)
            );

            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
            menuPanel.add(headerPanel);
            menuPanel.add(bodyPanel);

            menuFrame.add(menuPanel, BorderLayout.CENTER);
            menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            menuFrame.setBounds(567, 279, 400, 200);
            menuFrame.setVisible(true);

            normalButton.addActionListener(e -> {
                menuFrame.dispose();
                ProgramNormal.main();
            });

            manualButton.addActionListener(e -> {
                menuFrame.dispose();
                ProgramManual.main();
            });

            adjacentButton.addActionListener(e -> {
                menuFrame.dispose();
                ProgramAdjacent.main(1);
            });

            helpButton.addActionListener(e -> {
                ProgramHelp.main();
            });

        });

    }
}
