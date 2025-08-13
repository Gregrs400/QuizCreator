import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Question
{

    private final JPanel guiLayout = new JPanel(new GridLayout(2, 1, 0, 0));
    HashMap<String, String> promptsAndAnswers;
    HashMap<String, JTextField> userAnswers = new HashMap<>();

    public Question(String questionType, String title, HashMap<String, String> promptsAndAnswers)
    {

        this.promptsAndAnswers = promptsAndAnswers;

        // ---------- TOP HALF ----------
        JLabel questionLabel = new JLabel(title);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 28));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(questionLabel, BorderLayout.CENTER);

        // ---------- BOTTOM HALF ----------
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));

        if (questionType.equals("Acronym or Full Term"))
        {

            // Scrollable content panel for acronyms
            JPanel acronymListPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            String[] prompts = promptsAndAnswers.keySet().toArray(new String[0]);

            for (int i = 0; i < prompts.length; i++)
            {

                gbc.gridx = 0;
                gbc.gridy = i;
                JLabel acronymLabel = new JLabel(prompts[i]);
                Font currentFont = acronymLabel.getFont();
                acronymLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 18));
                acronymLabel.setHorizontalAlignment(SwingConstants.CENTER);
                acronymListPanel.add(acronymLabel, gbc);

                gbc.gridx = 1;
                JTextField field = new JTextField(20);
                userAnswers.put(prompts[i], field);
                acronymListPanel.add(field, gbc);

            }

            JScrollPane scrollPane = new JScrollPane(acronymListPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(15);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            bottomPanel.add(scrollPane, BorderLayout.CENTER);

        }

        guiLayout.add(topPanel);
        guiLayout.add(bottomPanel);

    }

    public JPanel getGuiLayout() { return guiLayout; }
}
