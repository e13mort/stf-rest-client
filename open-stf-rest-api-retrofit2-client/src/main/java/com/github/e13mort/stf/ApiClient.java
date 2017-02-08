package com.github.e13mort.stf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.e13mort.stf.auth.ApiKeyAuth;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@SuppressWarnings("WeakerAccess")
public class ApiClient {

    private Retrofit.Builder adapterBuilder;

    public ApiClient(String baseUrl, String apiKey) {
        final ApiKeyAuth authorization = new ApiKeyAuth("header", "Authorization");
        authorization.setApiKey("Bearer " + apiKey);
        final OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(authorization)
                .build();
        createDefaultAdapter(baseUrl, okClient);
    }

    private void createDefaultAdapter(String baseUrl, OkHttpClient okClient) {

        if (!baseUrl.endsWith("/"))
            baseUrl = baseUrl + "/";

        adapterBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(createObjectMapper()));
    }

    public <S> S createService(Class<S> serviceClass) {
        return adapterBuilder.build().create(serviceClass);
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

}

