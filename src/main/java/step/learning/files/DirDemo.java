package step.learning.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DirDemo {

    public void run(){
        System.out.println("Dir demo");
        String path = "./";
        File dir = new File(path);
        if(dir.exists()){
            System.out.printf("Object %s does exist @ real path '%s' and it is '%s '%n'", path, dir.getAbsolutePath(),
                    dir.isFile() ? "file" : "directory");
        }
        else{
            System.out.printf("Object %s does exist%n", path);
        }

        String subPath = "./upload";
        File subDir = new File(subPath);
        if(subDir.isDirectory()){
            System.out.printf("Dir '%s' already exist%n", subPath);
        }
        else{
            if(subDir.exists()){
                System.out.printf("Object '%s' does exist, BUT NOT A DIR %n", subPath);
                System.out.printf("Delete object?");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.next();

                if("Y".equalsIgnoreCase(answer)){
                    if(subDir.delete()){
                        System.out.printf("Deleted", subPath);
                        if(subDir.mkdir()) {
                            System.out.printf("Created", subPath);
                        }
                    }
                }
                else{
                    return;
                }
            }
            System.out.printf("Dir '%s' does not exist, creating...%n", subPath);
            if(subDir.mkdir()){
                System.out.println("Done");
            }
            else{
                System.out.println("Creation error");
            }
        }
    }

    public void dir (String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            System.out.printf("%-10s %-25s %-10s %s%n", "Mode", "LastWriteTime", "Length", "Name");
            System.out.println("----          -------------     ------   ----");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

            for (File file : files) {
                String mode = file.isDirectory() ? "d-----" : "-a----";
                String lastWriteTime = dateFormat.format(new Date(file.lastModified()));
                String length = file.isDirectory() ? "" : String.valueOf(file.length());
                String name = file.getName();

                System.out.printf("%-10s %-25s %-10s %s%n", mode, lastWriteTime, length, name);
            }
        } else {
            System.out.println("Указанный путь не является директорией.");
        }
    }

    public void lines() {
        String filename = "lines.txt";
        String lineContent = "";
        int lineNumber = 0;
        int maxLength = 0;
        int currentLine = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                currentLine++;
                int lineLength = line.length();
                if (lineLength > maxLength) {
                    maxLength = lineLength;
                    lineContent = line;
                    lineNumber = currentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (maxLength > 0) {
            System.out.println("Самая длинная строка под номером " + lineNumber);
            System.out.println("Длина строки: " + maxLength);
            System.out.println("Контент строки: " + lineContent);
        } else {
            System.out.println("Файл пустой.");
        }
    }
}
