package org.mv.hands;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {

    private String menuName;
    private int price;
    private int resId;

    public MenuItem(String menuName, int price,int resId) {
        this.menuName = menuName;
        this.price = price;
        this.resId = resId;
    }

    public MenuItem(Parcel src){
        menuName = src.readString();
        price = src.readInt();
        resId = src.readInt();
    }

    //getter , setter
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuName='" + menuName + '\'' +
                ", price=" + price +
                ", resId=" + resId +
                '}';
    }

    //creator 필드가 필요함
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        //simpleData 객체 생성
        public MenuItem createFromParcel(Parcel src){
            return new MenuItem(src);
        }

        //객체 배열 생성
        public MenuItem[] newArray(int size){
            return new MenuItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(menuName);
        parcel.writeInt(price);
        parcel.writeInt(resId);
    }
}
