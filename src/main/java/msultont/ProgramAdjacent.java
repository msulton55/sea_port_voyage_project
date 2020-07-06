package msultont;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgramAdjacent {

    /**
     * The identifier is used to indicate the behavior of the window.
     * There are 2 identifiers: 0 and 1.
     * 0 means the ProgramAdjacent window will be attached to the right side.
     * 1 means the ProgramAdjacent window will be attached on the same position as parent window.
     *
     * @param identifier 0 means window to the right, 1 means window on same position as parent window.
     */
    public static void main(int identifier) {
        SwingUtilities.invokeLater(() -> {

            JFrame adjacentFrame = new JFrame("Program Adjacent");
            JPanel adjacentPanel = new JPanel();
            GroupLayout layout = new GroupLayout(adjacentPanel);

            JLabel portNameLabel = new JLabel("Port name");
            JLabel resultPath = new JLabel();
            JTextField portNameInput = new JTextField();
            JButton checkButton = new JButton("Check");

            adjacentPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                                            .addComponent(portNameLabel)
                                            .addGroup(layout.createParallelGroup()
                                                            .addComponent(portNameInput)
                                                            .addComponent(checkButton)
                                                            .addComponent(resultPath))
            );

            layout.setVerticalGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(portNameLabel)
                                                          .addComponent(portNameInput))
                                          .addComponent(checkButton)
                                          .addComponent(resultPath)
            );

            adjacentFrame.add(adjacentPanel);
            adjacentFrame.setBounds(1015, 236, 350, 250);
            adjacentFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            adjacentFrame.setVisible(true);

            if (identifier == 1) {
                adjacentFrame.setBounds(567, 279, 350, 250);
                adjacentFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        ProgramMenu.main();
                    }
                });
            }

            checkButton.addActionListener(e -> {
                Graph g = new Graph("graph_list/KOORDINAT PELABUHAN-SELAT v8.4.txt");
                String results = g.vertices.get(portNameInput.getText()).adjlist.keySet().toString();
                resultPath.setText("<html><p>Path: " + results + "</p></html>");
            });


        });
    }
}
