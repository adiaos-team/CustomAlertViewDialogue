package stream.customalert;

import android.os.Parcel;
import android.os.Parcelable;

public class DialogItemInfo implements Parcelable {
    public DialogItemInfo(){}
    public DialogItemInfo(String name, String value){
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
    DialogItemInfo(Parcel in){
        name=in.readString();
        value=in.readString();
    }

    public static final Creator<DialogItemInfo> CREATOR = new Creator<DialogItemInfo>() {
        @Override
        public DialogItemInfo createFromParcel(Parcel in) {
            return new DialogItemInfo(in);
        }

        @Override
        public DialogItemInfo[] newArray(int size) {
            return new DialogItemInfo[size];
        }
    };
}