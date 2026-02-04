package com.rodgar00.petmatch;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DogResponse {

    @SerializedName("message")
    private List<String> imageUrls;

    @SerializedName("status")
    private String status;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public String getStatus() {
        return status;
    }
}
