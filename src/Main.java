import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

        Quiz test = createQuiz(frame, "src\\network+QuizContent");

        frame.setVisible(true);

        ArrayList<Question> questions;

        GridBagConstraints gbc = new GridBagConstraints();

        if (test != null)
            if (!test.getQuestions().isEmpty())
            {
                currentQuestion = test.getQuestions().getFirst();
                questions = test.getQuestions();


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
                    if (currentIndex > 0) {
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

                    for (Question question : questions) {

                        for (PromptAndAnswer answer : question.getUserAnswers()) {

                            if (!answer.getUserAnswer().equals(question.getPromptsAndAnswers().get(answer.getQuestionString()))) {

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
                    if (currentIndex < questions.size() - 1) {

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

    public static Quiz createQuiz(Frame frame, String filePath)
    {

        Quiz quiz = new Quiz(filePath);

        ArrayList<String> sections = new ArrayList<>(quiz.getQuestionMap().keySet());
        ArrayList<JCheckBox> checkBoxes;

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        checkboxPanel.add(Box.createVerticalGlue());

        checkBoxes = new ArrayList<>();
        for (String section : sections) {
            JCheckBox checkBox = new JCheckBox(section);
            checkBox.setFont(new Font(checkBox.getFont().getName(), checkBox.getFont().getStyle(), 20));
            checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            checkBoxes.add(checkBox);
            checkboxPanel.add(checkBox);
        }

        checkboxPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(checkboxPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // choose which sections to be included in quiz

        return null;

    }
}