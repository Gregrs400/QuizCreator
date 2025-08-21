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
                testQuestionPromptsAndAnswers, questions, frame);

        questions.add(testAcronymOrFullTerm);

        HashMap<String, String> testTermOrDefinitionContent = new HashMap<>();
        testTermOrDefinitionContent.put("Multi-Factor Authentication", "Test");
        Question testTermOrDefinition =
                new Question("Term or Definition", "Multi-Factor Authentication",
                        testTermOrDefinitionContent, questions, frame);
        questions.add(testTermOrDefinition);

        frame.add(testAcronymOrFullTerm.getGuiLayout());

        currentQuestion = questions.getFirst();

        JPanel pnlQuizControlPanel = new JPanel(new GridBagLayout());

        JButton btnPreviousQuestion = new JButton("<");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        pnlQuizControlPanel.add(btnPreviousQuestion, gbc);
        btnPreviousQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

            }
        });

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
        btnNextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentIndex = questions.indexOf(currentQuestion);
                System.out.println("Current index: " + currentIndex);
                if (currentIndex < questions.size())
                {

                    currentIndex += 1;
                    frame.remove(currentQuestion.getGuiLayout());
                    currentQuestion = questions.get(currentIndex);
                    frame.add(currentQuestion.getGuiLayout());
                    frame.validate();
                    frame.repaint();

                }
            }
        });

        frame.add(pnlQuizControlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}