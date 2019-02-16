import java.io.*;
import java.util.Arrays;

 public class IO {
    public static String getExpression(String path){
        String fileContent = "";

        try {
            File myDir = new File("src");
            File myFile = new File(myDir, path);
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                fileContent += line;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return fileContent;
    }

    public static void printResult(String outputFileName) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(outputFileName));
        } catch (Exception e) {
            System.out.print(Arrays.toString(e.getStackTrace()));
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
