import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Question
{

    private final JPanel guiLayout = new JPanel(new GridBagLayout());
    HashMap<String, String> promptsAndAnswers;
    HashMap<String, JTextField> userAnswers = new HashMap<>();
    GridBagConstraints gbc = new GridBagConstraints();

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
        // bottomPanel.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));

        if (questionType.equals("Acronym or Full Term"))
        {

            // Scrollable content panel for acronyms
            JPanel acronymListPanel = new JPanel(new GridBagLayout());
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
        else if (questionType.equals("Term or Definition"))
        {

            JTextField txtBottomHalf = new JTextField();

            bottomPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.fill = GridBagConstraints.BOTH;
            gbc2.weightx = 1.0;
            gbc2.weighty = 1.0;

            bottomPanel.add(txtBottomHalf, gbc2);

            txtBottomHalf.setEditable(true);
        }

        JPanel pnlQuizControlPanel = new JPanel(new GridBagLayout());

        JButton btnPreviousQuestion = new JButton("<");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnPreviousQuestion, gbc);

        JButton btnEnd = new JButton("End");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnEnd, gbc);

        JButton btnNextQuestion = new JButton(">");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnNextQuestion, gbc);

        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.3;
        guiLayout.add(topPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;             // ensure horizontal stretching
        gbc.weighty = 2.0;             // keep your vertical weight
        gbc.fill = GridBagConstraints.BOTH; // stretch in both directions
        guiLayout.add(bottomPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        guiLayout.add(pnlQuizControlPanel, gbc);

    }

    public JPanel getGuiLayout() { return guiLayout; }
}
