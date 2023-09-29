package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class Shotgun extends Weapon implements Classified{
    private float reloadSpeed;

    public Shotgun(String name, float reloadSpeed){
        super.setName(name);
        this.setReloadSpeed(reloadSpeed);

    }
    @Override
    public String getLevel() {
        return "For civil";
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
        return String.format("Shotgun '%s' (reload speed %fs)", super.getName(), this.getReloadSpeed());
    }

    public float getReloadSpeed() {
        return reloadSpeed;
    }

    public void setReloadSpeed(float reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    @JsonParseCheck
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("reloadSpeed"));
    }

    @JsonFactory
    public static Shotgun fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "reloadSpeed"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Shotgun: Missing required field '%s'", field));
            }
        }
        return new Shotgun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat());
    }
}
