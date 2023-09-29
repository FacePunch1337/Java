package step.learning.oop;
import com.google.gson.JsonObject;

import java.util.List;

@Serializable
public class MachineGun extends Weapon implements Automatic, Classified, RifledBarrel {

    private float fireRate;
    public MachineGun(String name, float fireRate) {

        super.setName(name);
        this.setFireRate(fireRate);

    }


    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }
    @Override
    public void addCard(String name, Integer num, String date, Float price) {
        getCardData().add(name);
        getCardData().add(num);
        getCardData().add(date);
        getCardData().add(price);

    }

    @Override
    public String getCard() {
        return String.format("MachineGun: '%s' (fireRate %.1f bps)", super.getName(), this.getFireRate());
    }


    @Override
    public String getLevel() {
        return "For military";
    }



    @JsonParseCheck
    public static boolean isJsonParseable(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("fireRate"));
    }

    @JsonFactory
    public static MachineGun fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "fireRate"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("MachineGun: Missing required field '%s'", field));
            }
        }
        return new MachineGun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat());
    }
}
