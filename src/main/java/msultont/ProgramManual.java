package msultont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/*
    TODO: Buat fungsi tombol Delete Row
    TODO: Integrasiin ke google maps
 */

public class ProgramManual {

    private static final JLabel resultPath = new JLabel();
    private static final JLabel resultDistance = new JLabel();
    private static final ArrayList<JTextField> destinations = new ArrayList<>();
    private static final ArrayList<JComponent> newComp = new ArrayList<>();
    private static int componentCounter = 0;
    private static int destinationCounter = 1;

    public static void main() {
        SwingUtilities.invokeLater(() -> {

            JFrame manualFrame = new JFrame("Manual Mode");
            JPanel manualTopPanel = new JPanel();
            JPanel manualBottomPanel = new JPanel();
            JPanel boxLayoutPanel = new JPanel();
            GroupLayout topLayout = new GroupLayout(manualTopPanel);
            GroupLayout bottomLayout = new GroupLayout(manualBottomPanel);
            GroupLayout.ParallelGroup parallel;
            GroupLayout.ParallelGroup parallel2;
            GroupLayout.SequentialGroup sequential;

            JLabel originLabel = new JLabel("Origin");
            JLabel destinationLabel = new JLabel("Destination 1");
            JTextField firstOrigin = new JTextField();
            JTextField firstDestination = new JTextField();

            JButton startButton = new JButton("Start");
            JButton addButton = new JButton("Add Row");
            JButton deleteButton = new JButton("Delete Row");
            JButton adjacentButton = new JButton("Adjacent List");
            JButton backButton = new JButton("Back");
            JButton exitButton = new JButton("Exit");
            JCheckBox showMap = new JCheckBox("Show Map");

            manualTopPanel.setLayout(topLayout);
            topLayout.setAutoCreateGaps(true);
            topLayout.setAutoCreateContainerGaps(true);

            parallel = topLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);
            parallel2 = topLayout.createParallelGroup();
            sequential = topLayout.createSequentialGroup();

            topLayout.setHorizontalGroup(topLayout.createSequentialGroup()
                                                  .addGroup(parallel
                                                                    .addComponent(originLabel)
                                                                    .addComponent(destinationLabel))
                                                  .addGroup(parallel2
                                                                    .addComponent(firstOrigin)
                                                                    .addComponent(firstDestination))
            );

            topLayout.setVerticalGroup(sequential
                                               .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                  .addComponent(originLabel)
                                                                  .addComponent(firstOrigin))
                                               .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                  .addComponent(destinationLabel)
                                                                  .addComponent(firstDestination))

            );

            manualBottomPanel.setLayout(bottomLayout);
            bottomLayout.setAutoCreateGaps(true);
            bottomLayout.setAutoCreateContainerGaps(true);

            bottomLayout.setHorizontalGroup(bottomLayout.createSequentialGroup()
                                                        .addGroup(bottomLayout.createParallelGroup()
                                                                              .addGroup(bottomLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(
                                                                                                        startButton)
                                                                                                .addComponent(addButton)
                                                                                                .addComponent(
                                                                                                        deleteButton)
                                                                                                .addComponent(
                                                                                                        adjacentButton))
                                                                              .addComponent(backButton)
                                                                              .addComponent(exitButton)
                                                                              .addComponent(showMap)
                                                                              .addComponent(resultPath)
                                                                              .addComponent(resultDistance))

            );

            bottomLayout.linkSize(SwingConstants.HORIZONTAL, startButton, addButton, deleteButton, adjacentButton,
                                  backButton, exitButton);

            bottomLayout.setVerticalGroup(bottomLayout.createSequentialGroup()
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(startButton)
                                                                            .addComponent(addButton)
                                                                            .addComponent(deleteButton)
                                                                            .addComponent(adjacentButton))
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(backButton))
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(exitButton))
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(showMap))
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(resultPath))
                                                      .addGroup(bottomLayout.createParallelGroup(
                                                              GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(resultDistance))
            );

            JScrollPane scrollPane = new JScrollPane(manualTopPanel);
            AdjustmentListener scroller = new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    Adjustable adjustable = e.getAdjustable();
                    adjustable.setValue(adjustable.getMaximum());
                    scrollPane.getVerticalScrollBar().removeAdjustmentListener(this);
                }
            };
            scrollPane.getVerticalScrollBar().addAdjustmentListener(scroller);

            boxLayoutPanel.setLayout(new BoxLayout(boxLayoutPanel, BoxLayout.Y_AXIS));
            boxLayoutPanel.add(scrollPane);
            boxLayoutPanel.add(manualBottomPanel);

            manualFrame.add(boxLayoutPanel);
            manualFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            manualFrame.setBounds(495, 229, 506, 300);
            manualFrame.setVisible(true);

            startButton.addActionListener(e -> {

                Graph g = new Graph("graph_list/KOORDINAT PELABUHAN-SELAT v8.4.txt");
                double totalDistances = 0;
                List<String> nodesCollectionFix = new ArrayList<>();
                List<String> extractedNodes = new ArrayList<>();

                extractedNodes.add(firstOrigin.getText());
                extractedNodes.add(firstDestination.getText());

                if (destinations.size() != 0) {
                    for (JTextField textField : destinations) {
                        extractedNodes.add(textField.getText());
                    }
                }

                for (int i = 0; i < extractedNodes.size() - 1; i++) {
                    List<Node> listNodes = g.shortestPath(extractedNodes.get(i), extractedNodes.get(i+1));
                    totalDistances += Node.pathLength(listNodes);

                    for (int j = 0; j < listNodes.size(); j++) {
                        nodesCollectionFix.add(listNodes.get(j).id);
                    }

                }

                for (int i = 0; i < nodesCollectionFix.size() - 1; i++) {
                    if (nodesCollectionFix.get(i).equals(nodesCollectionFix.get(i + 1)))
                        nodesCollectionFix.remove(i+1);
                }

                System.out.println(nodesCollectionFix.toString());

                resultPath.setText("Path: " + nodesCollectionFix.toString());
                resultDistance.setText("Distance: " + totalDistances + " KM");
                //totalDistances = 0;


//
//
//
//
//
//
//
//

//
//                for (Node node : mainCalc) {
//                    nodesCollection.add(node.id);
//                }
//                System.out.println(nodesCollection.toString());
//
//                totalDistances += Node.pathLength(mainCalc);



            });



//            startButton.addActionListener(e -> {
//
//                Graph g = new Graph("graph_list/KOORDINAT PELABUHAN-SELAT v8.4.txt");
//                double totalDistances = 0;
//                List<String> nodesCollection = new ArrayList<>();
//                List<Node> mainCalc = g.shortestPath(firstOrigin.getText(), firstDestination.getText());
//
//                for (Node node : mainCalc) {
//                    nodesCollection.add(node.id);
//                }
//                System.out.println(nodesCollection.toString());
//
//                totalDistances += Node.pathLength(mainCalc);
//
//                if (destinations.size() != 0) {
//                    if (destinations.size() > 1) {
//                        List<Node> additionalCalc = null;
//
//                        for (int i = 0; i < destinations.size() - 1; i++) {
//                            additionalCalc =
//                                    g.shortestPath(destinations.get(i).getText(), destinations.get(i + 1).getText());
//                            for (Node node : additionalCalc) {
//                                nodesCollection.add(node.id);
//                            }
//                        }
//                        totalDistances += Node.pathLength(additionalCalc);
//
//                        resultPath.setText("Path: " + firstOrigin.getText());
//                        for (int i = 0; i < nodesCollection.size() - 1; i++) {
//                            if (!nodesCollection.get(i).equals(nodesCollection.get(i + 1))) {
//                                resultPath.setText(resultPath.getText() + ", " + nodesCollection.get(i+1));
//                            }
//                        }
//                        resultDistance.setText("Distance: " + totalDistances + " KM");
//                        totalDistances = 0;
//
//                    } else {
//
//                        List<Node> secondMainCalc =
//                                g.shortestPath(firstDestination.getText(), destinations.get(0).getText());
//
//                        for (Node node : secondMainCalc) {
//                            nodesCollection.add(node.id);
//                        }
//                        totalDistances += Node.pathLength(secondMainCalc);
//
//                        resultPath.setText("Path: " + firstOrigin.getText());
//                        for (int i = 0; i < nodesCollection.size() - 1; i++) {
//                            if (!nodesCollection.get(i).equals(nodesCollection.get(i + 1)))
//                                resultPath.setText(resultPath.getText() + ", " + nodesCollection.get(i + 1));
//                        }
//                        resultDistance.setText("Distance: " + totalDistances + " KM");
//                        totalDistances = 0;
//
//                    }
//
//                } else {
//                    resultPath.setText("Path: ");
//                    for (int i = 0; i < nodesCollection.size(); i++) {
//                        resultPath.setText(resultPath.getText() + nodesCollection.get(i) + ", ");
//                    }
//                    resultDistance.setText("Distance: " + totalDistances + " KM");
//                    totalDistances = 0;
//                }
//
//            });

            addButton.addActionListener(e -> {
                JLabel label = new JLabel("Destination " + (destinationCounter + 1), JLabel.RIGHT);
                JTextField field = new JTextField();
                label.setLabelFor(field);
                destinationCounter++;
                newComp.add(label);
                newComp.add(field);

                for (int i = componentCounter; i < newComp.size() - 1; i++) {
                    parallel.addComponent(newComp.get(i));
                    parallel2.addComponent(newComp.get(i + 1));
                    sequential.addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                 .addComponent(newComp.get(i)).addComponent(newComp.get(i + 1)));
                }

                componentCounter += 2;
                boxLayoutPanel.validate();
                destinations.add(field);

            });

            /*
            deleteButton.addActionListener(e -> {
                for (int i = componentCounter - 1; i > componentCounter - 2; i--) {
                    topLayout.removeLayoutComponent(newComp.get(i));
                    //newComp.remove(i-1);

                }
                componentCounter -= 2;
                boxLayoutPanel.revalidate();
            });
             */

            adjacentButton.addActionListener(e -> {
                ProgramAdjacent.main(0);
            });

            backButton.addActionListener(e -> {
                manualFrame.dispose();
                ProgramMenu.main();
            });

            manualFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    ProgramMenu.main();
                }
            });


        });
    }
}
