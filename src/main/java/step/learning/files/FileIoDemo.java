package step.learning.files;

import java.io.*;

public class FileIoDemo {

    public void run(){
        String filename ="test.txt";
        try(OutputStream writer = new FileOutputStream(filename, false)){
            writer.write("Hello".getBytes());
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter(filename, true)){
            writer.write("\r\rNew Line" );
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
