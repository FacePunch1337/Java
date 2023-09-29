package step.learning;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import step.learning.basics.BasicsDemo;
import step.learning.basics.Dice;
import step.learning.basics.Dictionary;
import step.learning.files.DirDemo;
import step.learning.files.FileIoDemo;
import step.learning.oop.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App
{
    public static void main2(String[] args) {
        //new BasicsDemo().run();
        //new BasicsDemo().multiplyMatrices();
        //new Dictionary().menuTable();
        //new DirDemo().run();
        //new DirDemo().listFilesInDirectory();
        //new FileIoDemo().run();
        //new DirDemo().dir(".");
        //new DirDemo().lines();

        /*Gun gun = new Gun();
        Rifle rifle = new Rifle();
        MachineGun machineGun = new MachineGun();
        gun.addCard("glock",1337228, "9.25.2023" , 4.20f);
        rifle.addCard("M4A1",2281337, "9.25.2023" , 700.00f);
        machineGun.addCard("M49-SAW",5632554, "9.25.2023" , 1000.00f);
        gun.getCard();
        rifle.getCard();
        machineGun.getCard();*/
        /*Armory armory = new Armory();
        armory.add(new Gun( "glock", 10));
        armory.add(new MachineGun( "M49 SAW", 8.5f));
        armory.add(new Rifle( "M24", 7.62f));
        armory.printAll();
        System.out.println("---------AUTOMATIC---------");
        armory.printAutomatic();
        System.out.println("---------NON AUTOMATIC---------");
        armory.printNonAutomatic();
        System.out.println("---------CLASSIFIED---------");
        armory.printClassified();
        System.out.println("---------NON-CLASSIFIED---------");
        armory.printNonClassified();*/
        /*-------------------------------------------------------------------------------*/
        /*String  jsonString = "{\"name\": \"Glock\", \"cartridge\":10}";
        Gson gson = new Gson();
        Gun gun = new Gson().fromJson(jsonString, Gun.class);
        System.out.println(gun.getCard());
        System.out.println(gson.toJson(gun));
        Gson gson2 = new GsonBuilder().setPrettyPrinting().serializeNulls().setDateFormat("yyyy-MM-dd").create();
        System.out.println(gson2.toJson(gun));*/




        // доступ до ресурсів, без-типова робота з JSON
        /*String resourceName = "glock.json" ;
        try( InputStreamReader reader =
                     new InputStreamReader(
                             Objects.requireNonNull(
                                     App.class
                                             .getClassLoader()
                                             .getResourceAsStream( resourceName ) ) ) )  {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            Weapon weapon = null;
            if(jsonObject.has("cartridge")){
                weapon = new Gun(jsonObject.get("name").getAsString(),jsonObject.get("cartridge").getAsInt());
            }
            else if(jsonObject.has("caliber")){
                weapon = new Rifle(jsonObject.get("name").getAsString(),jsonObject.get("caliber").getAsFloat());
            }
            else if(jsonObject.has("fireRate")){
                weapon = new MachineGun(jsonObject.get("name").getAsString(),jsonObject.get("fireRate").getAsFloat());
            }
            else{
                System.err.println("Weapone type unrecognized");
            }
            if(weapon != null){
                System.out.println(weapon.getCard());

            }


        }
        catch( IOException ex ) {
            System.err.println( "IO error: " + ex.getMessage() ) ;
        }
        catch( NullPointerException ignored ) {
            System.err.printf( "Resource '%s' not found %n", resourceName ) ;
        }*/

    }

    public static void main(String[] args) {

        Armory armory = new Armory();
        armory.add(new Gun( "glock", 10));
        armory.add(new MachineGun( "M49 SAW", 8.5f));
        armory.add(new Rifle( "M24", 7.62f));
        armory.add(new Shotgun( "Remington 870", 1.6f));
        armory.add(new SubmachineGun( "MP5", 200f));
        System.out.println("---------AUTOMATIC---------");
        armory.printAutomatic();
        System.out.println("---------RIFLED---------");
        armory.printRifledBarrel();
        System.out.println("---------USED---------");
        armory.printUsed();
        System.out.println("---------NON AUTOMATIC---------");
        armory.printNonAutomatic();
        System.out.println("---------CLASSIFIED---------");
        armory.printClassified();
        System.out.println("---------NON-CLASSIFIED---------");
        armory.printNonClassified();

        //armory.save();
        System.out.println("---------ALL---------");

       // armory.load();
        armory.printAll();
    }
}



