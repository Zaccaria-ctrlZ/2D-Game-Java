package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args){

        //This code create the window for the game by using the JFrame class.
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");


        /*
        After we created the top level frame of the game, aka the window,
        we now create the actual panel where the game will be displayed through
        the JPanel class.
        */
        GamePanel gamepanel = new GamePanel(); //Instance of the class GamePanel.
        window.add(gamepanel); // Actually add the panel we created before, with its proprieties, to the window.

        window.pack(); // This causes the window(JFrame) to be sized to fit the preferred size
                       // and layouts of its subcomponents, aka the GamePanel.

        /*
        setLocationRelativeTo allow us to choose where the window of the game
        will be located. Passing the value of "null" will display the window
        at the center of the screen.
        */
        window.setLocationRelativeTo(null);
        window.setVisible(true); //This makes visible the window.

        gamepanel.startGameThread(); // Start the thread we have created in gamePanel.

    }

}
