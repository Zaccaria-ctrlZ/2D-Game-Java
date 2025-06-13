package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;
/*
After we created the top level frame of the game, aka the window,
we now create the actual panel where the game will be displayed through
the JPanel class.
*/
public class GamePanel extends JPanel implements Runnable { //Runnable allows us to use Thread.

    /*
    SCREEN SETTINGS. Here we define the settings and proprieties of every
    asset of the game as long as the map tile of the panel.
     */
    final int originalTileSize = 16; // 16x16 px is the size of one single tile or square on the screen
    final int scale = 3; /* Because of the higher resolution the screens have now we need to scale the originalTileSize
                          in order to let it be visible on bigger screen.
                          */

    public final int tileSize = originalTileSize * scale; // 48x48, this is the actual tile size that will be displayed.
    final int maxScreenCol = 16; // This decides the max displayed tiles in a single panel horizontally.
    final int maxScreenRow = 12; // Same as before but vertically.
    final int screenWidth = tileSize * maxScreenCol;  // The actual size in pixel horizontally.
    final int screenHeight = tileSize * maxScreenRow; // The actual size in pixel vertically.

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Create a new thread which allow as to execute some codes in parallel of other things.
                        // Thread is an object which is just initialized with a name, gameThread. Similar as
                        // we do with : String nome;
    Player player = new Player(this, keyH);

    // Set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;

    //Constructor for the GamePanel object.
    public GamePanel(){
        //Here we actually create the window (panel) with the attributes we created before.
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));// Set the size of this panel.
        this.setBackground(Color.black); //Background color of the panel.
        this.setDoubleBuffered(true); /* If set to true, all the drawing form this component
                                         will be done in an offscreen painting buffer.
                                         It improves game rendering performance.
                                      */
        this.addKeyListener(keyH); // The panel can recognize the keys as we have implemented in the KeyHandler class.
        this.setFocusable(true); // The GamePanel can be "focused" to receive key input.
    }

    public void startGameThread(){ //We call this method to start the Thread.
        gameThread = new Thread(this); // Instance of Thread. We pass this, GamePanel.
        gameThread.start(); // This method calls the run method below.
    }

    @Override
    public void run() { // Here is the game loop.

        double drawInterval = 1000000000 / FPS; // 0.016s
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){ // As long as the gameThread exist, it repeats the processes inside it.

            // 1 UPDATE : update information such as character position.
            update();

            // 2 DRAW : draw the screen with the updated information.
            repaint(); //This is how we call the paintComponent method.

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime /10000000;

                if( remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){ // Here goes everything that need to be updated while in the game loop such as player position.

        player.update();
    }

    public void paintComponent(Graphics g){ // Graphics is a class that has many functions to draw objects on the screen.

        super.paintComponent(g); //super = JPanel.

        Graphics2D g2 = (Graphics2D)g; //Graphics2d class extends the Graphics class to provide more sophisticated
                                        //control over geometry, coordinate transformations, color management, and text layout.
        player.draw(g2);
        g2.dispose();// Dispose of this graphics context and release any system resources that is using. Save some memories.
    }
}
