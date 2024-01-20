package com.aliasadi.androidmvp.data.fanz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PlayerDetails")
public class PlayerDetails implements Parcelable{

    // Dummy list of PlayerDetails
    public static List<PlayerDetails> getDummyList() {
        List<PlayerDetails> dummyList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            PlayerDetails player = new PlayerDetails();
            player.setId(i);
            player.setTitle("Title " + i);
            player.setDescription("Description " + i);
            player.setImage("dummy_image_url_" + i);
            dummyList.add(player);
        }

        return dummyList;
    }


    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    public PlayerDetails() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static final Creator<PlayerDetails> CREATOR = new Creator<PlayerDetails>() {
        @Override
        public PlayerDetails createFromParcel(Parcel in) {
            return new PlayerDetails(in);
        }

        @Override
        public PlayerDetails[] newArray(int size) {
            return new PlayerDetails[size];
        }
    };

    protected PlayerDetails(Parcel in) {
        description = in.readString();
        image = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(title);
    }

}
