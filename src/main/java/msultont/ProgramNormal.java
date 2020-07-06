package msultont;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;

public class ProgramNormal {

    private static final JLabel resultPath = new JLabel();
    private static final JLabel resultDistance = new JLabel();
    private static final JLabel resultTime = new JLabel();
    private static final JLabel resultFreq = new JLabel();
    private static final JLabel resultTotalLoad = new JLabel();
    private static final JCheckBox showMap = new JCheckBox("Show Map");

    public static void main() {
        SwingUtilities.invokeLater(() -> {

            JFrame normalFrame = new JFrame("Normal Mode");
            JPanel mainPanel = new JPanel();
            GroupLayout layout = new GroupLayout(mainPanel);

            JLabel originLabel = new JLabel("Origin");
            JLabel destinationLabel = new JLabel("Destination");
            JLabel velocityLabel = new JLabel("Velocity");
            JTextField originInput = new JTextField();
            JTextField destinationInput = new JTextField();
            JTextField velocityInput = new JTextField();
            JButton startButton = new JButton("Start");
            JButton adjacentButton = new JButton("Adjacent List");
            JButton backButton = new JButton("Back");
            JButton exitButton = new JButton("Exit");

            mainPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(originLabel)
                                                            .addComponent(destinationLabel)
                                                            .addComponent(velocityLabel))
                                            .addGroup(layout.createParallelGroup()
                                                            .addComponent(originInput)
                                                            .addComponent(destinationInput)
                                                            .addComponent(velocityInput)
                                                            .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(startButton)
                                                                            .addComponent(adjacentButton))
                                                            .addComponent(backButton)
                                                            .addComponent(exitButton)
                                                            .addComponent(showMap)
                                                            .addComponent(resultPath)
                                                            .addComponent(resultDistance)
                                                            .addComponent(resultTime)
                                                            .addComponent(resultFreq)
                                                            .addComponent(resultTotalLoad))
            );

            layout.linkSize(SwingConstants.HORIZONTAL, startButton, adjacentButton, backButton, exitButton);

            layout.setVerticalGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(originLabel)
                                                          .addComponent(originInput))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(destinationLabel)
                                                          .addComponent(destinationInput))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(velocityLabel)
                                                          .addComponent(velocityInput))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(startButton)
                                                          .addComponent(adjacentButton))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(backButton))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(exitButton))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(showMap))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(resultPath))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(resultDistance))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(resultTime))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(resultFreq))
                                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                          .addComponent(resultTotalLoad))
            );

            normalFrame.add(mainPanel);
            normalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            normalFrame.setBounds(495, 229, 515, 325);
            normalFrame.setVisible(true);

            startButton.addActionListener(e1 -> {
                if (originInput.getText().isEmpty() && destinationInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Origin & destination must not be empty", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } else if (originInput.getText().isEmpty()) {
                    JOptionPane
                            .showMessageDialog(null, "Origin must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (destinationInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Destination must not be empty!", "Error", 0);
                } else {
                    start(originInput.getText(), destinationInput.getText(), velocityInput.getText());
                }


            });

            adjacentButton.addActionListener(e -> {
                ProgramAdjacent.main(0);
            });

            backButton.addActionListener(e -> {
                normalFrame.dispose();
                ProgramMenu.main();
            });

            exitButton.addActionListener(e -> {
                ProgramExit.main();
            });

            normalFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    ProgramMenu.main();
                }
            });

        });
    }

    private static void start(String origin, String destination, String velocityInput) {
        Graph g = new Graph("graph_list/KOORDINAT PELABUHAN-SELAT v8.4.txt");
        List<Node> n = g.shortestPath(origin, destination);

        DecimalFormat df = new DecimalFormat("#.##");
        String pathDistance = df.format(Node.pathLength(n));
        String velocity = df.format(Double.parseDouble(velocityInput));

        FitnessFunction function = new FitnessFunction(Double.parseDouble(pathDistance), Double.parseDouble(velocity));

        resultPath.setText("Path: " + n.toString());
        resultDistance.setText("Distance: " + pathDistance + " KM");
        resultTime.setText("Estimated time arrive: " + df.format(function.getTime()) + " hours");
        resultFreq.setText("Frequency of visits per year: " + (int) function.getFreq() + " times");
        resultTotalLoad.setText("Total load per 100 TEU: " + df.format(function.getTotalCapacity()) + " TEU");

        if (showMap.isSelected()) {
            Map.main(n);
        }
    }
}
