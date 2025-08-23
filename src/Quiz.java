import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public String readFromFile(File file)
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

    // convert data structure into quiz object

}
