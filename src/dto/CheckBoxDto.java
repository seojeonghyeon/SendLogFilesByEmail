package dto;

public class CheckBoxDto {
    private boolean isCheckBoxTrue;
    private int locationX;
    private int locationY;
    private int sizeWidth;
    private int sizeHeight;

    public CheckBoxDto(boolean isCheckBoxTrue, int locationX, int locationY, int sizeWidth, int sizeHeight){
        this.isCheckBoxTrue = isCheckBoxTrue;
        this.locationX = locationX;
        this.locationY = locationY;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
    }

    public boolean isCheckBoxTrue(){
        return isCheckBoxTrue;
    }
    public int getLocationX(){
        return locationX;
    }
    public int getLocationY(){return locationY;}
    public int getSizeWidth(){return sizeWidth;}
    public int getSizeHeight(){return sizeHeight;}

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(isCheckBoxTrue+"  location(x/y):"+locationX+"/"+locationY
                +"  size(weight/height):"+sizeWidth+"/"+sizeHeight);
        return stringBuilder.toString();
    }
}
