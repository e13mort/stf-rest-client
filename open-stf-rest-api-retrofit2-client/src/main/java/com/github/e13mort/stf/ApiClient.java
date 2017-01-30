package com.github.e13mort.stf;

import com.github.e13mort.stf.auth.ApiKeyAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();

        if (!baseUrl.endsWith("/"))
            baseUrl = baseUrl + "/";

        adapterBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okClient)

                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonCustomConverterFactory.create(gson));
    }

    <S> S createService(Class<S> serviceClass) {
        return adapterBuilder.build().create(serviceClass);
    }

    private static class GsonCustomConverterFactory extends Converter.Factory {
        private final Gson gson;
        private final GsonConverterFactory gsonConverterFactory;

        private GsonCustomConverterFactory(Gson gson) {
            if (gson == null) throw new NullPointerException("gson == null");
            this.gson = gson;
            this.gsonConverterFactory = GsonConverterFactory.create(gson);
        }

        static GsonCustomConverterFactory create(Gson gson) {
            return new GsonCustomConverterFactory(gson);
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type.equals(String.class))
                return new GsonResponseBodyConverterToString<Object>(gson, type);
            else
                return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
    }

    /**
     * This wrapper is to take care of this case:
     * when the deserialization fails due to JsonParseException and the
     * expected type is String, then just return the body string.
     */
    private static class GsonResponseBodyConverterToString<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final Type type;

        GsonResponseBodyConverterToString(Gson gson, Type type) {
            this.gson = gson;
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String returned = value.string();
            try {
                return gson.fromJson(returned, type);
            } catch (JsonParseException e) {
                //noinspection unchecked
                return (T) returned;
            }
        }
    }
}

