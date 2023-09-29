package step.learning.oop;
import com.google.gson.JsonObject;

import java.util.List;

@Serializable
public class Rifle extends Weapon implements NonClassified, RifledBarrel{

    private float caliber;
    public Rifle(String name, float caliber) {

        super.setName(name);
        this.setCaliber(caliber);

    }

    public float getCaliber() {
        return caliber;
    }

    public void setCaliber(float caliber) {
        this.caliber = caliber;
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
        return String.format("Rifle: '%s' (caliber: %.2f mm)", super.getName(), this.getCaliber());
    }
    @Override
    public String getLevel() {
        return " ";
    }
    @JsonParseCheck
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("caliber"));
    }

    @JsonFactory
    public static Rifle fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "caliber"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Rifle: Missing required field '%s'", field));
            }
        }
        return new Rifle(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat());
    }
}
