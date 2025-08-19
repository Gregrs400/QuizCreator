import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Question");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        ArrayList<Question> questions = new ArrayList<>();

        HashMap<String, String> testQuestionPromptsAndAnswers = new HashMap<>();

        testQuestionPromptsAndAnswers.put("NAS", "Network-attached Storage");
        testQuestionPromptsAndAnswers.put("SAN", "Storage Area Network");
        testQuestionPromptsAndAnswers.put("WAN", "Wide Area Network");
        testQuestionPromptsAndAnswers.put("IDS", "Intrusion Detection System");
        testQuestionPromptsAndAnswers.put("IPS", "Intrusion Prevention System");
        Question testAcronymOrFullTerm = new Question("Acronym or Full Term",
                "Network Component Acronyms",
                testQuestionPromptsAndAnswers, questions);

        questions.add(testAcronymOrFullTerm);

        HashMap<String, String> testTermOrDefinitionContent = new HashMap<>();
        testTermOrDefinitionContent.put("Multi-Factor Authentication", "Test");
        Question testTermOrDefinition =
                new Question("Term or Definition", "Multi-Factor Authentication",
                        testTermOrDefinitionContent, questions);
        questions.add(testTermOrDefinition);

        frame.add(testAcronymOrFullTerm.getGuiLayout());

        frame.setVisible(true);
    }
}