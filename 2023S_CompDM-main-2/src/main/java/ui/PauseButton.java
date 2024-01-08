package src.main.java.ui;

import src.main.java.Controller.GameController;

import javax.swing.*;
import java.awt.*;


public class PauseButton extends JButton {
    //private JButton button;

    public PauseButton() {
        //button = new JButton("Pause");
        setText("Pause");
        addActionListener(e -> {

            pause();

        });

    }

    public void pause() {

        JFrame pauseFrame = new JFrame();
        pauseFrame.setVisible(true);
        pauseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pauseFrame.setSize(500, 200);

        JButton saveQuitButton = new JButton("Save and Quit");
        saveQuitButton.addActionListener(e -> {
            pauseFrame.getContentPane().removeAll();
            JButton saveTextButton = new JButton("Save and Quit to a File");
            saveTextButton.addActionListener(save -> {
                GameController.saveQuit("TextFile");
                System.exit(0);
            });
            JButton saveMongoButton = new JButton("Save and Quit to Mongo");
            saveMongoButton.addActionListener(save -> {
                GameController.saveQuit("Mongo");
                System.exit(0);
            });


            pauseFrame.add(saveTextButton, BorderLayout.NORTH);
            pauseFrame.add(saveMongoButton, BorderLayout.SOUTH);

            pauseFrame.repaint();
            pauseFrame.revalidate();

        });

//        JButton loadGameButton = new JButton("Load existing game");
//        loadGameButton.addActionListener(e -> {
//
//            GameController.loadPrevGame("TextFile");
//            pauseFrame.dispose();
//
//        });

        JButton resumeGameButton = new JButton("Resume game");
        resumeGameButton.addActionListener(e -> {
            pauseFrame.dispose();
        });

        //pauseFrame.add(loadGameButton, BorderLayout.SOUTH);
        pauseFrame.add(saveQuitButton, BorderLayout.NORTH);
        pauseFrame.add(resumeGameButton);

    }

}


