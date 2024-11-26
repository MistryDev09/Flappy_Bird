import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    //Images
    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    //Bird
    int birdx = boardWidth/8;
    int birdy = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    @Override
    public void actionPerformed(ActionEvent e) {
        move();//every frame it will update the values of bird in move()
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -12;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    class Bird{
        int x = birdx;
        int y = birdy;
        int width = birdWidth;
        int height = birdHeight;
        Image image;

        Bird(Image image){
            this.image = image;
        }
    }

    //Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image image;
        boolean passed = false;

        Pipe(Image image){
            this.image = image;
        }
    }

    //game logic
    Bird bird;
    int velocityX =-4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random rand = new Random();


    Timer gameloop;
    Timer placePipesTimer;

    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        //setBackground(Color.BLUE);
        setFocusable(true); //makes sure that this class takes in the key events
        addKeyListener(this); //checks the three key - pressed ect functions

        //load images
        backgroundImage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //bird
        bird = new Bird(birdImage);
        pipes = new ArrayList<Pipe>();

        //place pipes timer will call the placePipes() function every 1500ms
        placePipesTimer = new Timer(1500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameloop = new Timer(1000/60,this);//Timer(milliseconds, this flappybird class)
        gameloop.start();
    }

    public void placePipes(){
        //(0-1)* pipeHeight/2 -> 256  random number between (0-256)
        //128
        //0 - 128 - (0-256) --> pipeHeight/4 -> pipeHeight

        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()*pipeHeight/2);
        int openingSpace = boardHeight/4;
        Pipe topPipe = new Pipe(topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImage);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        // 0 + current
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        //background
        g.drawImage(backgroundImage,0,0,boardWidth, boardHeight, null);
        //Bird
        g.drawImage(birdImage,bird.x,bird.y,bird.width,bird.height, null);

        //pipes
        for(int i =0; i<pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0); //0 is the very top of the screen

        //pipes
        for(int i =0; i<pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }

    }


}