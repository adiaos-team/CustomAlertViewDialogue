package stream.customalert;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemInfo implements Parcelable {
    public ItemInfo(){}
    public ItemInfo( String name,String value){
        this.name = name;
        this.value = value;
    }
    String name;
    String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
    }
    ItemInfo(Parcel in){
        name=in.readString();
        value=in.readString();
    }

    public static final Creator<ItemInfo> CREATOR = new Creator<ItemInfo>() {
        @Override
        public ItemInfo createFromParcel(Parcel in) {
            return new ItemInfo(in);
        }

        @Override
        public ItemInfo[] newArray(int size) {
            return new ItemInfo[size];
        }
    };
}