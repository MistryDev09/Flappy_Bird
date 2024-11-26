import javax.swing.*;

public class App {

    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        //frame.setVisible(true); //set visible
        frame.setSize(boardWidth, boardHeight); //set size of the frame
        frame.setLocationRelativeTo(null);  //set to center of screen
        frame.setResizable(false);  //not resizable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); //excludes the title bar in the dimensions
        frame.setVisible(true); //set visible

    }
}
