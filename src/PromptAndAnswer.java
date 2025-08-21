import javax.swing.*;

public class PromptAndAnswer
{

    private String questionString;
    private JTextField answerField;

    public String getQuestionString() { return questionString; }
    public String getUserAnswer() { return answerField.getText(); }

    public PromptAndAnswer(String prompt, JTextField answerField)
    {

               questionString = prompt;
               this.answerField = answerField;

    }

}
