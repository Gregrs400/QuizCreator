import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Quiz
{

    private final ArrayList<Question> questions = new ArrayList<>();

    // open file

    public File createOrAccessFile(String filePathAndName) {

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

    // parse string into data structure

    // convert data structure into quiz object

}
