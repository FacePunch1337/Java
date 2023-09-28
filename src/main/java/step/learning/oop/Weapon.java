package step.learning.oop;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Weapon {
    private String name;
    private Integer num;
    private String date;
    private Float price;
    private List<Object> cardData;

    public Weapon() {
        cardData = new ArrayList<>();
    }

    public void setCardData(List<Object> cardData) {
        this.cardData = cardData;
    }

    public List<Object> getCardData() {
        return cardData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }



    public abstract void addCard(String name, Integer num, String date, Float price);

    public abstract String getCard();
}
