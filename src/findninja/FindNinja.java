/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findninja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 * @author Doug
 */
public class FindNinja extends JFrame {

    int l = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter horizontal size"));
    int h = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter vertical size"));
    /*
    JLabel label0 = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    */
    JLabel[] labels = new JLabel[h*l];
    JLabel[] choiceLabel = new JLabel[h*l];
    ImageIcon ninja = new ImageIcon("Ryu150.gif");
    //ImageIcon ninja = new ImageIcon("ninja150.png");
    JButton newButton = new JButton("New Game");
    
    JLabel counterLabel = new JLabel("Guesses: 0");
    
    Random myRandom = new Random();
    int ninjaLocation = myRandom.nextInt(h * l);
    int counter = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //create frame
        new FindNinja().setVisible(true);
        
    }
    /**
     * 
     */
    public FindNinja() {
        //frame constructor
        setTitle("Find the Ninja before he kills you.");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;
        
        /*
        choiceLabel[0] = label0;
        choiceLabel[1] = label1;
        choiceLabel[2] = label2;
        choiceLabel[3] = label3;
        choiceLabel[4] = label4;
        choiceLabel[5] = label5;
        choiceLabel[6] = label6;
        choiceLabel[7] = label7;
        choiceLabel[8] = label8;
        */
        
        for (int i = 0; i < (h * l); i++) {
            choiceLabel[i] = new JLabel();
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < l; x++) {
                gridConstraints = new GridBagConstraints();
                choiceLabel[(l * y) + x].setPreferredSize(new Dimension(ninja.getIconWidth(),ninja.getIconHeight()));
                choiceLabel[(l * y) + x].setOpaque(true);
                choiceLabel[(l * y) + x].setBackground(Color.BLACK);
                gridConstraints.gridx = x;
                gridConstraints.gridy = y;
                gridConstraints.insets = new Insets(5,5,5,5);
                getContentPane().add(choiceLabel[(l * y) + x],gridConstraints);
                choiceLabel[(l * y) + x].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        labelMouseClicked(e);
                    }


                });

                //TODO

                pack();
            }
        }
        gridConstraints = new GridBagConstraints();
        newButton.setPreferredSize(new Dimension(150, 30));
        gridConstraints.gridx = ( l / 2 );
        gridConstraints.gridy = h;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(newButton, gridConstraints);
        newButton.setEnabled(false);
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = ( l / 2 ) + 1;
        gridConstraints.gridy = h;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(counterLabel, gridConstraints);
        
        /*
        newButton.addActionListener(new ActionListener() { //Why is this broken? Using whack mouseadapter until fixed.
            public void actionPerformed(ActionEvent e) {
                newGameButtonClicked(e);
            }
        });
        */
        
        newButton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (newButton.isEnabled()) {
                            newGameButtonClicked(e);
                        }
                    }
                });
        pack();
       
    }

    
    private void labelMouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
        if (counter >= 0) {
            Component clickedComponent = e.getComponent();
            int choice;
            for (choice = 0; choice < (h * l) -1 ; choice++) {
                if (clickedComponent == choiceLabel[choice]) {

                    break;
                }
            }

            if (choiceLabel[choice].getBackground() == (Color.GRAY)) {
                counterLabel.setText("You guessed that");
            } else {
                choiceLabel[choice].setBackground(Color.GRAY);
                counter++;
                counterLabel.setText("Guesses: " + counter);
            }
            if (choice == ninjaLocation) {
                choiceLabel[choice].setIcon(ninja);
                newButton.setEnabled(true);
                counterLabel.setText("Total Guesses: " + counter);
                counter = -1;
            }
        }
    }

    private void newGameButtonClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        for (int i = 0;i < (h*l) ; i++) {
            choiceLabel[i].setIcon(null);
            choiceLabel[i].setBackground(Color.BLACK);
        }
        ninjaLocation = myRandom.nextInt(h*l);
        newButton.setEnabled(false);
        counter = 0;
        
        counterLabel.setText("Guesses: " + counter);
        
    }
    
    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

}
