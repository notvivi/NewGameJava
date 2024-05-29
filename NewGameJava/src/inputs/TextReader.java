package inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that is used for reading files.
 */
public class TextReader {
    private ArrayList<String> sentences = new ArrayList<>();
    private ArrayList<String> credits = new ArrayList<>();
    private ArrayList<String> futurePlans = new ArrayList<>();
    private final String STORY = "./src/res/text_files/story.txt";
    private final String CREDITS = "./src/res/text_files/credits.txt";
    private final String FUTURE_PLANS = "./src/res/text_files/future_plans.txt";

    /**
     * Class constructor.
     */
    public TextReader() {

    }

    /**
     * Method that reads files.
     * @param fileName
     */
    public void read(String fileName){
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while(bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if(fileName.equals("./src/res/text_files/story.txt")){
                    sentences.add(line);
                } else if (fileName.equals("./src/res/text_files/credits.txt")) {
                    credits.add(line);
                } else if (fileName.equals("./src/res/text_files/future_plans.txt")) {
                    futurePlans.add(line);
                }


            }

        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    /**
     * Method that returns arraylist.
     * @return
     */
    public ArrayList<String> getSentences() {
        return sentences;
    }

    /**
     * Method that returns arraylist.
     * @return
     */
    public ArrayList<String> getCredits() {
        return credits;
    }

    /**
     * Method that returns story string.
     * @return
     */
    public String getSTORY() {
        return STORY;
    }

    /**
     * Method that returns credits string.
     * @return
     */
    public String getCREDITS() {
        return CREDITS;
    }

    /**
     * Method that returns arraylist.
     * @return
     */
    public ArrayList<String> getFuturePlans() {
        return futurePlans;
    }

    /**
     * Method that returns future plans string.
     * @return
     */
    public String getFUTURE_PLANS() {
        return FUTURE_PLANS;
    }
}
