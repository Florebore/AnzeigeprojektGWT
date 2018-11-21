


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author peterkirchhoff
 */
public class TxtToJSON {

    public void createJSON() {

        TxtToJSON txt = new TxtToJSON();
        try{
        txt.readfile();}
        catch(IOException e){System.out.println("Etwas ist schiefgelaufen.");}

        }



    public void readfile() throws IOException {
        String pathname = "/Users/peterkirchhoff/Dropbox/Studien/Informatik/6SEP/SEPNeu/Projektunterlagen/PeterArbeit/hello1.txt";
        String lineArray [] = new String[0];
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
               
                fileContents.append(scanner.nextLine() + System.lineSeparator());
                lineArray = fileContents.toString().split(";");

            }
            /*for (int i = 0; i<80;i++){

                System.out.println(lineArray[i]);
            }*/

            Gson gson=new GsonBuilder().create();
            String x =gson.toJson(lineArray);

            System.out.print(x);
            










        }
    }
}
