package markovModel;

import java.io.*;

public class DataProcessor {

    public static String removePunctuation(String sentence){
       return removeShortcuts(sentence)
               .replaceAll("[,;:{}()*\"’“”]"," ")
               .replaceAll("( )( )*"," ")
               .replaceAll("(\\[.*\\])","");
    }

    private static String removeShortcuts(String sentence){
        return sentence.toLowerCase()
                .replaceAll("i'm","i am")
                .replaceAll("you're","you are")
                .replaceAll("we're","we are")
                .replaceAll("they're","they are")
                .replaceAll("i've","i have")
                .replaceAll("you've","you have")
                .replaceAll("we've","we have")
                .replaceAll("they've","they have")
                .replaceAll("isn't","is not")
                .replaceAll("aren't", "are not")
                .replaceAll("haven't","have not")
                .replaceAll("hasn't","has not")
                .replaceAll("wasn't","was not")
                .replaceAll("weren't","were not")
                .replaceAll("didn't","did not")
                .replaceAll("shouldn't","should not")
                .replaceAll("wouldn't","would not")
                .replaceAll("couldn't","could not")
                .replaceAll("can't","can not")
                .replaceAll("what's","what is")
                .replaceAll("who's","who is")
                .replaceAll("how's","how is")
                .replaceAll("where's","where is")
                .replaceAll("when's","when is")
                .replaceAll("that's","that is")
                .replaceAll("there's","there is")
                .replaceAll("here's","here is")
                .replaceAll("y'all","you all");

    }

    public static void generateDataFromFile(String filename){
        File file = new File(filename);
        double lineIndex = 0;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            double nrLines = countLines(filename);
            String line;
            while ((line = br.readLine()) != null) {
                lineIndex++;
                if(lineIndex % 10 < 8)
//                if(lineIndex < 80.00/100 * nrLines)
                    writeDataToFile(line,"generatedDataSet.txt");
                else {
                    writeDataToFile(line,"generatedTestData.txt");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeDataToFile(String data,String filename){
        try(FileWriter fw = new FileWriter("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\"+filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(data);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                return 0;
            }

            int count = 0;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
