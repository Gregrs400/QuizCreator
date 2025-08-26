import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Quiz
{

    private ArrayList<Question> questions = new ArrayList<>();

    // open file

    private File createOrAccessFile(String filePathAndName) {

        try {

            File file = new File(filePathAndName);

            if (file.createNewFile()) {

                System.out.println("file created");

            } else {

                System.out.println("file already created");

            }

            return file;

        } catch (IOException e) {

            System.out.println("IOException occurred");
            e.printStackTrace();

            return null;

        }

    }

    // file to string

    private String readFromFile(File file)
    {

        StringBuilder fileString = new StringBuilder();

        try
        {

            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine())
            {

                fileString.append(fileReader.nextLine());
                fileString.append(System.getProperty("line.separator"));

            }

            return fileString.toString();

        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();

        }

        return null;

    }

    // parse string into data structure

    private LinkedHashMap<String, ArrayList<Question>> quizFromString(String quizString)
    {

        LinkedHashMap<String, ArrayList<Question>> quiz = new LinkedHashMap<>();

        String currentSection = "";
        quizString = quizString.replaceAll("[\\r\\n]+", "");
        String[] quizArrSections = quizString.split("#");
        ArrayList<String> quizArrListSections = new ArrayList<>();
        ArrayList<String> quizArrQuestions = new ArrayList<>();
        ArrayList<String> quizArrQuestionParts = new ArrayList<>();

        for (String quizSection : quizArrSections)
        {

            if (!quizSection.isBlank())
                quizArrListSections.add(quizSection);

        }

        for (String section : quizArrListSections)
        {

            quizArrQuestions.addAll(Arrays.asList(section.split("~")));

        }

        for (String quizQuestion : quizArrQuestions)
        {

            quizArrQuestionParts.addAll(Arrays.asList(quizQuestion.split("\\|")));

            if (quizArrQuestionParts.size() == 1)
            {
                currentSection = quizArrQuestionParts.getFirst().trim();
                quiz.put(currentSection, new ArrayList<>());
                quizArrQuestionParts.clear();
            }
            else
            {

                String currentQuestionType = quizArrQuestionParts.getFirst().trim();
                String currentQuestionTitle = quizArrQuestionParts.get(1).trim();
                String currentQuestionContent = quizArrQuestionParts.get(2).trim();
                HashMap<String,String> currentQuestionPromptsAndAnswers = new HashMap<>();
                if (currentQuestionContent.split(";").length > 1)
                {

                    String[] arrCurrentQuestionContent = currentQuestionContent.split(";");

                    int questionIndex = 0;
                    String currentQuestionPrompt = "";
                    String currentQuestionAnswer;

                    for (String s : arrCurrentQuestionContent)
                    {

                        if (questionIndex == 0) {
                            currentQuestionPrompt = s.split(",")[0].trim();
                            questionIndex++;
                            continue;
                        }

                        if (questionIndex == 1) {
                            currentQuestionAnswer = s.split(",")[1].trim();
                            currentQuestionPromptsAndAnswers.put(currentQuestionPrompt, currentQuestionAnswer);
                            questionIndex = 0;
                        }


                    }

                }
                else
                    currentQuestionPromptsAndAnswers.put(currentQuestionTitle, currentQuestionContent);

                Question question = new Question(currentQuestionType, currentQuestionTitle,
                        currentQuestionPromptsAndAnswers);

                quiz.get(currentSection).add(question);

            }
            quizArrQuestionParts.clear();
        }

        System.out.println();

        return quiz;

    }

    // convert data structure into quiz object

    public Quiz createQuiz(String filePath)
    {

        File quizContentFile = createOrAccessFile(filePath);
        String quizContentString = readFromFile(quizContentFile);
        LinkedHashMap<String, ArrayList<Question>> quizQuestions = quizFromString(quizContentString);
        // choose which sections to be included in quiz
        // questions = chooseQuizContent(quizQuestions);
        return null;

    }

    public ArrayList<Question> chooseQuizContent(LinkedHashMap<String, ArrayList<Question>> questions)
    {

        return null;

    }

}
