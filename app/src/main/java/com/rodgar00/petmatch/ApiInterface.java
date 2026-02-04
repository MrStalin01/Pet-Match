package com.rodgar00.petmatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("breed/{breed}/images/random/{count}")
    Call<DogResponse> getDogsByBreed(
            @Path("breed") String breed,
            @Path("count") int count
    );
}
