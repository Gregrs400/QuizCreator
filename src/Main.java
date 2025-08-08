import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Question");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 0, 0));

        // ---------- TOP HALF ----------
        JLabel questionLabel = new JLabel("Expand the following acronyms:");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 28));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(questionLabel, BorderLayout.CENTER);

        // ---------- BOTTOM HALF ----------
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));

        // Scrollable content panel for acronyms
        JPanel acronymListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        String[] acronyms = {
                "ACR", "CPU", "GPU", "RAM", "ROM", "LAN", "WAN", "HTTP", "HTTPS",
                "SQL", "XML", "JSON", "OOP", "API", "IDE", "UI", "UX", "DNS", "IP", "MAC"
        };

        for (int i = 0; i < acronyms.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel acronymLabel = new JLabel(acronyms[i]);
            Font currentFont = acronymLabel.getFont();
            acronymLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 18));
            acronymLabel.setHorizontalAlignment(SwingConstants.CENTER);
            acronymListPanel.add(acronymLabel, gbc);

            gbc.gridx = 1;
            JTextField field = new JTextField(20);
            acronymListPanel.add(field, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(acronymListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // ---------- Assemble ----------
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}