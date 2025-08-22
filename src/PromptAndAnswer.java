import javax.swing.*;
import javax.swing.text.JTextComponent;

public class PromptAndAnswer
{

    private String questionString;
    private JTextComponent answerField;

    public String getQuestionString() { return questionString; }
    public String getUserAnswer() { return answerField.getText(); }

    public PromptAndAnswer(String prompt, JTextComponent answerField)
    {

               questionString = prompt;
               this.answerField = answerField;

    }

}
