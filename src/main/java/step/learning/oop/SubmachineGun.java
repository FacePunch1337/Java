package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class SubmachineGun extends Weapon implements Automatic, Classified, RifledBarrel, Used{
    private float fireRange;

    public SubmachineGun(String name, float fireRange){
        super.setName(name);
        this.setFireRange(fireRange);

    }

    public float getFireRange() {
        return fireRange;
    }

    public void setFireRange(float fireRange) {
        this.fireRange = fireRange;
    }
    @Override
    public String getLevel() {
        return "For military";
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
        return String.format("Submachine gun '%s' (fireRange %fm)", super.getName(), this.getFireRange());
    }

    @Override
    public String getYears() {
        return "8.25.2023";
    }


    @JsonParseCheck
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("fireRange"));
    }

    @JsonFactory
    public static SubmachineGun fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "fireRange"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Submachine gun: Missing required field '%s'", field));
            }
        }
        return new SubmachineGun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat());
    }



}
