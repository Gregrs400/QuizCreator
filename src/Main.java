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
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Quiz test = createQuiz(frame, "src\\network+QuizContent");

    }

    public static Quiz createQuiz(Frame frame, String filePath)
    {

        Quiz quiz = new Quiz(filePath);

        LinkedHashMap<String, ArrayList<Question>> questionMap = quiz.getQuestionMap();

        ArrayList<String> sections = new ArrayList<>(questionMap.keySet());
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

        // Scrollable checkbox panel
        JScrollPane scrollPane = new JScrollPane(checkboxPanel);

        // Outer wrapper with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add Start Quiz button at bottom
        JButton startButton = new JButton("Start Quiz");
        startButton.addActionListener(e -> {

            // Collect selected sections
            quiz.getQuestions().clear();
            for (JCheckBox box : checkBoxes) {
                if (box.isSelected()) {
                    quiz.getQuestions().addAll(questionMap.get(box.getText()));
                }
            }

            ArrayList<Question> questions = quiz.getQuestions();

            if (!quiz.getQuestions().isEmpty()) {

                // Remove the selection panel
                frame.remove(mainPanel);

                // Show the first question
                currentQuestion = quiz.getQuestions().get(0);
                frame.add(currentQuestion.getGuiLayout(), BorderLayout.CENTER);

                // Create quiz control panel
                JPanel pnlQuizControlPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                JButton btnPreviousQuestion = new JButton("<");
                gbc.gridx = 0; gbc.gridy = 0; gbc.ipadx = 20; gbc.ipady = 20;
                pnlQuizControlPanel.add(btnPreviousQuestion, gbc);
                btnPreviousQuestion.addActionListener(ev -> {
                    int currentIndex = quiz.getQuestions().indexOf(currentQuestion);
                    if (currentIndex > 0) {
                        frame.remove(currentQuestion.getGuiLayout());
                        currentIndex -= 1;
                        currentQuestion = quiz.getQuestions().get(currentIndex);
                        frame.add(currentQuestion.getGuiLayout(), BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                JButton btnEnd = new JButton("End");
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                pnlQuizControlPanel.add(btnEnd, gbc);
                btnEnd.addActionListener(f -> {

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

                    JTextArea resultsField = new JTextArea();
                    JPanel resultsPanel = new JPanel(new BorderLayout());
                    resultsField.setEditable(false);
                    JScrollPane resultsScrollPane = new JScrollPane(resultsField);
                    resultsScrollPane.getVerticalScrollBar().setUnitIncrement(15);
                    resultsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    resultsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    Font resultsFieldCurrentFont = resultsField.getFont();

                    resultsField.setFont(new Font(resultsFieldCurrentFont.getName(),
                            resultsFieldCurrentFont.getStyle(), 24));
                    resultsField.setText(resultsString.toString());
                    resultsPanel.add(resultsScrollPane, BorderLayout.CENTER);
                    frame.remove(currentQuestion.getGuiLayout());
                    frame.remove(pnlQuizControlPanel);
                    frame.add(resultsPanel);
                    frame.validate();
                    frame.repaint();

                });


                JButton btnNextQuestion = new JButton(">");
                gbc.gridx = 2; gbc.gridy = 0;
                pnlQuizControlPanel.add(btnNextQuestion, gbc);
                btnNextQuestion.addActionListener(ev -> {
                    int currentIndex = quiz.getQuestions().indexOf(currentQuestion);
                    if (currentIndex < quiz.getQuestions().size() - 1) {
                        frame.remove(currentQuestion.getGuiLayout());
                        currentIndex += 1;
                        currentQuestion = quiz.getQuestions().get(currentIndex);
                        frame.add(currentQuestion.getGuiLayout(), BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                // Add quiz control panel to the bottom of the frame
                frame.add(pnlQuizControlPanel, BorderLayout.SOUTH);

                // Refresh frame
                frame.revalidate();
                frame.repaint();
            }
        });

        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setFont(new Font(startButton.getFont().getName(), Font.BOLD, 20));

        // Wrap the button in a panel to center it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);

        // Add the button panel to the bottom of the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add outer panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        return quiz;

    }
}