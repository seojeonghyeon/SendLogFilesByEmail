package dto;

import java.awt.*;

public class TextFieldDto {
    private String textValue;
    private int locationX;
    private int locationY;
    private int sizeWidth;
    private int sizeHeight;
    private String textFont;
    private int textFontSize;
    private Color textFontColor;

    public TextFieldDto(String textValue){
        this.textValue = textValue;
    }
    public void setTextFieldDtoExceptTextValue(int locationX, int locationY, int sizeWidth, int sizeHeight, String textFont,
                        int textFontSize, Color textFontColor){
        this.locationX = locationX;
        this.locationY = locationY;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.textFont = textFont;
        this.textFontSize = textFontSize;
        this.textFontColor = textFontColor;
    }

    public TextFieldDto(String textValue,int locationX, int locationY, int sizeWidth, int sizeHeight, String textFont,
                        int textFontSize, Color textFontColor){
        this.textValue = textValue;
        this.locationX = locationX;
        this.locationY = locationY;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.textFont = textFont;
        this.textFontSize = textFontSize;
        this.textFontColor = textFontColor;
    }

    public String getTextValue(){
        return textValue;
    }
    public int getLocationX(){
        return locationX;
    }
    public int getLocationY(){
        return locationY;
    }
    public int getSizeWidth(){
        return sizeWidth;
    }
    public int getSizeHeight(){
        return sizeHeight;
    }
    public String getTextFont(){
        return textFont;
    }
    public int getTextFontSize(){
        return textFontSize;
    }
    public Color getTextFontColor(){
        return textFontColor;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(textValue+"  location(x/y):"+locationX+"/"+locationY
                +"  size(weight/height):"+sizeWidth+"/"+sizeHeight
                +"  textFont(name/size/color):"+textFont+"/"+textFontSize+"/"+textFontColor);
        return stringBuilder.toString();
    }
}
