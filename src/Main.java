import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Main
{

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Quiz Creator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
        frame.add(panel);
        panel.setVisible(true);

        frame.setVisible(true);



    }

}
