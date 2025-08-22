import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static Question currentQuestion;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Question");
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JTextArea resultsField = new JTextArea();
        resultsField.setEditable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        GridBagConstraints gbc = new GridBagConstraints();

        ArrayList<Question> questions = new ArrayList<>();

        HashMap<String, String> testQuestionPromptsAndAnswers = new HashMap<>();

        testQuestionPromptsAndAnswers.put("NAS", "Network-attached Storage");
        testQuestionPromptsAndAnswers.put("SAN", "Storage Area Network");
        testQuestionPromptsAndAnswers.put("WAN", "Wide Area Network");
        testQuestionPromptsAndAnswers.put("IDS", "Intrusion Detection System");
        testQuestionPromptsAndAnswers.put("IPS", "Intrusion Prevention System");
        Question testAcronymOrFullTerm = new Question("Acronym or Full Term",
                "Network Component Acronyms",
                testQuestionPromptsAndAnswers);

        questions.add(testAcronymOrFullTerm);

        HashMap<String, String> testTermOrDefinitionContent = new HashMap<>();
        testTermOrDefinitionContent.put("Multi-Factor Authentication", "Test");
        Question testTermOrDefinition =
                new Question("Term or Definition", "Multi-Factor Authentication",
                        testTermOrDefinitionContent);
        questions.add(testTermOrDefinition);

        HashMap<String, String> networkAcronyms = new HashMap<>();
        networkAcronyms.put("WAN", "Wide Area Network");
        networkAcronyms.put("PAN", "Personal Area Network");
        networkAcronyms.put("MAN", "Metropolitan Area Network");
        networkAcronyms.put("CAN", "Campus Area Network");
        networkAcronyms.put("LAN", "Local Area Network");
        Question networkAcronymsQuestion = new Question("Acronym or Full Term", "Network Acronyms", networkAcronyms);
        questions.add(networkAcronymsQuestion);

        frame.add(testAcronymOrFullTerm.getGuiLayout());

        currentQuestion = questions.getFirst();

        JPanel pnlQuizControlPanel = new JPanel(new GridBagLayout());

        JButton btnPreviousQuestion = new JButton("<");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnPreviousQuestion, gbc);
        btnPreviousQuestion.addActionListener(e -> {
            int currentIndex = questions.indexOf(currentQuestion);
            System.out.println("Current index: " + currentIndex);
            if (currentIndex > 0)
            {
                currentIndex -= 1;
                frame.remove(currentQuestion.getGuiLayout());
                currentQuestion = questions.get(currentIndex);
                frame.add(currentQuestion.getGuiLayout());
                frame.validate();
                frame.repaint();
            }

        });

        JButton btnEnd = new JButton("End");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnEnd, gbc);
        btnEnd.addActionListener(e -> {

            StringBuilder resultsString = new StringBuilder();

            for (Question question : questions)
            {

                for (PromptAndAnswer answer : question.getUserAnswers())
                {

                    if (!answer.getUserAnswer().equals(question.getPromptsAndAnswers().get(answer.getQuestionString())))
                    {

                        resultsString
                                .append("Question: ")
                                .append(answer.getQuestionString())
                                .append("  ----------  ")
                                .append(" Your Answer: ")
                                .append(answer.getUserAnswer())
                                .append("  ----------   Correct Answer: ")
                                .append(question.getPromptsAndAnswers().get(answer.getQuestionString()))
                                .append("\n");

                    }

                }

                resultsString.append("\n");

            }

            JScrollPane scrollPane = new JScrollPane(resultsField);
            scrollPane.getVerticalScrollBar().setUnitIncrement(15);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            Font resultsFieldCurrentFont = resultsField.getFont();

            resultsField.setFont(new Font(resultsFieldCurrentFont.getName(),
                    resultsFieldCurrentFont.getStyle(), 24));
            resultsField.setText(resultsString.toString());
            resultsPanel.add(scrollPane, BorderLayout.CENTER);
            frame.remove(currentQuestion.getGuiLayout());
            frame.remove(pnlQuizControlPanel);
            frame.add(resultsPanel);
            frame.validate();
            frame.repaint();

        });

        JButton btnNextQuestion = new JButton(">");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnNextQuestion, gbc);
        btnNextQuestion.addActionListener(e -> {
            int currentIndex = questions.indexOf(currentQuestion);
            System.out.println("Current index: " + currentIndex);
            if (currentIndex < questions.size()-1)
            {

                currentIndex += 1;
                frame.remove(currentQuestion.getGuiLayout());
                currentQuestion = questions.get(currentIndex);
                frame.add(currentQuestion.getGuiLayout());
                frame.validate();
                frame.repaint();

            }
        });

        frame.add(pnlQuizControlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}