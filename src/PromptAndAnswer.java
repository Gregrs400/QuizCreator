import javax.swing.*;

public class PromptAndAnswer
{

    private String questionString;
    private JTextField answerField;

    public String getQuestionString() { return questionString; }
    public JTextField getUserAnswer() { return answerField; }

    public PromptAndAnswer(String prompt, JTextField answerField)
    {

               questionString = prompt;
               this.answerField = answerField;

    }

}
