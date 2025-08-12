import javax.swing.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quiz Question");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        HashMap<String, String> testQuestionPromptsAndAnswers = new HashMap<>();

        testQuestionPromptsAndAnswers.put("NAS", "Network-attached Storage");
        testQuestionPromptsAndAnswers.put("SAN", "Storage Area Network");
        testQuestionPromptsAndAnswers.put("WAN", "Wide Area Network");
        testQuestionPromptsAndAnswers.put("IDS", "Intrusion Detection System");
        testQuestionPromptsAndAnswers.put("IPS", "Intrusion Prevention System");
        Question testTermOrFullTerm = new Question("Acronym or Full Term",
                "Network Component Acronyms",
                testQuestionPromptsAndAnswers);


        frame.add(testTermOrFullTerm.getGuiLayout());

        frame.setVisible(true);
    }
}