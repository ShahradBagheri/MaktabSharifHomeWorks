package secondQuestion.util;

import java.io.FileInputStream;
import java.io.IOException;

public class MyFileReader {

    public StringBuilder readFile(String fileName){
        try(FileInputStream fileInputStream = new FileInputStream(fileName)){
            StringBuilder output = null;
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead);
                output.append(data);
            }
            return output;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
