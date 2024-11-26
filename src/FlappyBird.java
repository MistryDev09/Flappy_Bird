import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener {

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

    //game logic
    Bird bird;
    int velocityY = -6;
    int gravity = 1;

    Timer gameloop;

    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        //setBackground(Color.BLUE);

        //load images
        backgroundImage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //bird
        bird = new Bird(birdImage);

        //game timer
        gameloop = new Timer(1000/60,this);//Timer(milliseconds, this flappybird class)
        gameloop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        System.out.println("draw");
        //background
        g.drawImage(backgroundImage,0,0,boardWidth, boardHeight, null);
        //Bird
        g.drawImage(birdImage,bird.x,bird.y,bird.width,bird.height, null);
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0); //0 is the very top of the screen

    }

}