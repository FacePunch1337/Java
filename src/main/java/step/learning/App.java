package step.learning;
import step.learning.basics.BasicsDemo;
import step.learning.basics.Dice;
import step.learning.basics.Dictionary;
import step.learning.files.DirDemo;
import step.learning.files.FileIoDemo;


public class App
{
    public static void main(String[] args) {
        //new BasicsDemo().run();
        //new BasicsDemo().multiplyMatrices();
        //new Dictionary().menuTable();
        //new DirDemo().run();
        //new DirDemo().listFilesInDirectory();
        //new FileIoDemo().run();
        new DirDemo().dir(".");
        new DirDemo().lines();

    }

}



