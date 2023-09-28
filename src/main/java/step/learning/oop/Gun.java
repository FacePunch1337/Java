package step.learning.oop;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.*;

@Serializable
public class Gun extends Weapon implements Classified {

    private int cartridge;
    public Gun(String name, int cartridge) {

        super.setName(name);
        this.setCartridge(cartridge);

    }

    public int getCartridge() {
        return cartridge;
    }

    public void setCartridge(int cartridge) {
        this.cartridge = cartridge;
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
        return String.format("Gun '%s' (cartridge %d)", super.getName(), this.getCartridge());
    }


    @Override
    public String getLevel() {
        return "For civil";
    }

    @JsonParseCheck
    public static boolean isParseableFromJson(JsonObject jsonObject) {
       /* return Stream.concat(
                        Arrays.stream( Gun.class.getDeclaredFields() ),
                        Arrays.stream( Gun.class.getSuperclass().getDeclaredFields() ) )
                .filter( field-> field.isAnnotationPresent ( Required.class ) )
                .allMatch( field -> jsonObject.has(field.getName()));*/
        return (jsonObject.has("name") && jsonObject.has("cartridge"));
    }

    @JsonFactory
    public static Gun fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "cartridge"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Gun: Missing required field '%s'", field));
            }
        }
        return new Gun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsInt());
    }
}
