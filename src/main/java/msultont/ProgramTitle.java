/* AUTHORS
 * Muhammad Sulton Tauhid
 * Email	: msulton55@gmail.com
 * Instagram: msultont
 */
package msultont;

import javax.swing.*;
import java.awt.*;

// TODO: merapihkan projek berdasarkan design projek yang baik
// TODO: bikin fitness function

public class ProgramTitle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Main component of front page of the program.
            // Initiate all frame component.
            JFrame titleFrame = new JFrame("Program Title");
            JPanel titleTopPanel = new JPanel();
            JPanel titleMainPanel = new JPanel();
            JPanel titleFooterPanel = new JPanel();

            // Initiate all label component to be put inside the frame.
            JLabel titleHeader = new JLabel("Welcome\n");
            JLabel titleMainText = new JLabel("Sea Port Voyage Path System\n");
            JLabel titleFooter = new JLabel("Created by @msultont");

            // Initiate the button component to listen the event.
            JButton startButton = new JButton("Start");
            JButton exitButton = new JButton("Exit");

            // Define the sub-component settings.
            titleHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleHeader.setFont(new Font("Serif", Font.PLAIN, 45));
            titleMainText.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleMainText.setFont(new Font("Serif", Font.BOLD, 20));
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Put sub-component to the panel component.
            titleTopPanel.setLayout(new BoxLayout(titleTopPanel, BoxLayout.Y_AXIS));
            titleTopPanel.setPreferredSize(new Dimension(400, 100));
            titleTopPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            titleTopPanel.add(titleHeader);
            titleTopPanel.add(titleMainText);
            titleMainPanel.setLayout(new BoxLayout(titleMainPanel, BoxLayout.Y_AXIS));
            titleMainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            titleMainPanel.add(startButton);
            titleMainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            titleMainPanel.add(exitButton);
            titleFooterPanel.add(titleFooter);

            // Put all sub-components into main frame.
            titleFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            titleFrame.setBounds(567, 279, 400, 250);
            titleFrame.getContentPane().add(titleTopPanel, BorderLayout.PAGE_START);
            titleFrame.getContentPane().add(titleMainPanel, BorderLayout.CENTER);
            titleFrame.getContentPane().add(titleFooterPanel, BorderLayout.PAGE_END);
            titleFrame.setVisible(true);

            // Start button action listener.
            startButton.addActionListener(e -> {
                titleFrame.dispose();
                ProgramMenu.main();
            });

            // Exit action listener.
            exitButton.addActionListener(e -> {
                ProgramExit.main();
            });

        });
    }
}