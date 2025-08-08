import javax.swing.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Quiz Creator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));

        JLabel lblTopHalf = new JLabel("Test");

        Font currentFont = lblTopHalf.getFont();

        lblTopHalf.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 32));
        lblTopHalf.setHorizontalAlignment(SwingConstants.CENTER);
        lblTopHalf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(lblTopHalf);

        frame.add(panel);
        frame.setVisible(true);

    }

}
