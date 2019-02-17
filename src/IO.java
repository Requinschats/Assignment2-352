import java.io.*;
import java.util.Arrays;

 public class IO {
    public static String[] getExpression(String path, int numberOfExpressions){
        String fileContent [] = new String[numberOfExpressions];
        try {
            File myDir = new File("src");
            File myFile = new File(myDir, path);
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            String line;
            int lineCount =0;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                fileContent[lineCount] += line;
                lineCount++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return fileContent;
    }

    public static void printResult(String outputFileName, String content) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(outputFileName, true));
            writer.println("Expression: "+ content);
        } catch (Exception e) {
            System.out.print(Arrays.toString(e.getStackTrace()));
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
