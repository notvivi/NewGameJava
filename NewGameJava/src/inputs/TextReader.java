package inputs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextReader {
    public ArrayList<String> sentences = new ArrayList<>();

    public TextReader(){

    }

    public void read(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/res/story/text.txt"));
            while(bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                sentences.add(line);
            }

        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

}
