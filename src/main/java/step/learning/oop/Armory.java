package step.learning.oop;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Armory {
    private final List<Weapon> weapons;

    public Armory() {
        weapons = new ArrayList<>();
    }

    private List<Weapon> getSerializableWeapons(){
        List<Weapon> result = new LinkedList<>();
        for(Weapon weapon : weapons){
            if(weapon.getClass().isAnnotationPresent(Serializable.class)){
                result.add(weapon);
            }
        }
        return result;
    }

    private List<Class<?>> findSerializableClass() {
        List<Class<?>> weaponClasses = new ArrayList<>();
        String armoryName = Armory.class.getName(); // step.learning.oop.Armory
//        System.out.println(armoryName);
        String packageName = armoryName.substring(0, armoryName.lastIndexOf('.') + 1); // step.learning.oop.
//        System.out.println(packageName);
        // заменяем точки на файловые слеши
        String packagePath = packageName.replace('.', '/');
        URL resourceUrl = Armory.class.getClassLoader().getResource(packagePath);
        if (resourceUrl == null) {
            throw new RuntimeException(String.format("Package '%s' not found", packageName));
        }
//        System.out.println(packagePath);
        String resourcePath = resourceUrl.getPath(); // /C:/Users/dank/IdeaProjects/AppJavaStart/target/classes/step/learning/oop/
        try {
            resourcePath = URLDecoder.decode(resourcePath, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            System.err.println("Unsupported encoding");
        }
//        System.out.println(resourcePath);

        File packageDir = new File(resourcePath);
        File[] files = packageDir.listFiles();
        if (files == null) {
            throw new RuntimeException(String.format("Package '%s' is empty", packageName));
        }
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            } else if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.endsWith(".class")) {
                    String className = packageName + fileName.substring(0, fileName.lastIndexOf('.'));
                    try {
                        Class<?> classType = Class.forName(className);
                        if (classType.isAnnotationPresent(Serializable.class) && Weapon.class.isAssignableFrom(classType)) {
                            weaponClasses.add(classType);
                        }
                    } catch (ClassNotFoundException ignored) {
                        System.err.printf("Class '%s' not accessible %n", className);
                    }
                }
            }
        }
        return weaponClasses;
    }
    public void save() {
        String path = URLDecoder.decode(this.getClass().getClassLoader().getResource("./").getPath());
        System.out.println(path);
        // Відкриваємо файл (див. вправи "файли") і серіалізуємо у нього колекцію
        try (FileWriter writer = new FileWriter(path + "armory.json")) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()  // додавання пробілів та розривів
                    .serializeNulls()     // включення полів із null
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            writer.write(gson.toJson(this.getSerializableWeapons()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void add(Weapon weapon) {
        weapons.add(weapon);
    }

    public void remove(Weapon weapon) {
        weapons.remove(weapon);
    }


    public void printAll() {
        for (Weapon weapon : weapons) {
            System.out.println(weapon.getCard());
        }
    }

    public void printAutomatic() {
        System.out.println("Automatic:");
        for (Weapon weapon : weapons) {
            if (isAutomatic(weapon)) {
                System.out.println(weapon.getCard());
            }
        }
    }

    public void printNonAutomatic() {
        System.out.println("Non-Automatic:");
        for (Weapon weapon : weapons) {
            if (!isAutomatic(weapon)) {
                System.out.println(weapon.getCard());
            }
        }
    }

    public boolean isAutomatic(Weapon weapon) {
        return weapon instanceof Automatic;
    }

    public void printClassified() {

        for (Weapon weapon : weapons) {
            if (isClassified(weapon)) {
                Classified weaponAsClassified = (Classified) weapon;
                System.out.println(weaponAsClassified.getLevel() + " " + weapon.getCard());
            }
        }
    }

    public void printNonClassified() {

        for (Weapon weapon : weapons) {
            if (isNonClassified(weapon)) {
                NonClassified weaponAsNonClassified = (NonClassified) weapon;
                System.out.println(weaponAsNonClassified.getLevel() + " " + weapon.getCard());
            }
        }
    }

    public boolean isClassified(Weapon weapon) {
        return (weapon instanceof Classified);
    }

    public boolean isNonClassified(Weapon weapon) {
        return (weapon instanceof NonClassified);
    }

    public void load() throws RuntimeException {
        String resourceName = "armory.json";
        // Class<?>[] weaponClasses = {Gun.class, MachineGun.class, Rifle.class};
        // Задача - выбрать все классы, которые являются Weapon, но такой, что сериализуется
        List<Class<?>> weaponClasses = findSerializableClass();

        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                        this.getClass()
                                .getClassLoader()
                                .getResourceAsStream(resourceName)))) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                Weapon weapon = null;
                for (Class<?> weaponClass : weaponClasses) {
                    Method isParseableFromJson = null; // = weaponClass.getDeclaredMethod("isParseableFromJson", JsonObject.class);\
                    Method fromJson = null;
                    for (Method method : weaponClass.getDeclaredMethods()) {

                        if (method.isAnnotationPresent(JsonParseCheck.class)) {
                            if (isParseableFromJson != null) {
                                throw new RuntimeException(String.format("Multiple methods with @%s annotation in %s class", JsonParseCheck.class.getName(), weaponClass.getName()));
                            }
                            isParseableFromJson = method;
                        }
                        if(method.isAnnotationPresent(JsonFactory.class)){
                            if (fromJson != null) {
                                throw new RuntimeException(String.format("Multiple methods with @%s annotation in %s class", JsonFactory.class.getName(), weaponClass.getName()));
                            }
                            fromJson = method;
                        }
                    }
                    if (isParseableFromJson == null || fromJson == null) {
                        continue;
                    }
                    isParseableFromJson.setAccessible(true);
                    boolean res = (boolean) isParseableFromJson.invoke(null, jsonObject);
                    if (res) {

                        fromJson.setAccessible(true);
                        weapon = (Weapon) fromJson.invoke(null, jsonObject);
                    }

                }
                    if (weapon != null) {
                        this.weapons.add(weapon);

                    } else {
                        System.err.println("Weapon type unrecognized - skipped");
                    }

            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException("Reflection error: " + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("IO error: " + ex.getMessage());
        } catch (NullPointerException ignored) {
            throw new RuntimeException(String.format("Resource '%s' not found %n", resourceName));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Json parse error: " + ex.getMessage());
        }
    }
}
