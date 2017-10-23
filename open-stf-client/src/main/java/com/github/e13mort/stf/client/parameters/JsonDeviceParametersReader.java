package com.github.e13mort.stf.client.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.e13mort.stf.adapter.filters.StringsFilterDescription;
import com.github.e13mort.stf.adapter.filters.StringsFilterParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("unused")
public class JsonDeviceParametersReader {

    public DevicesParams read(URL url) throws JsonParamsReaderException {
        final ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(url, JsonDevicesParams.class);
        } catch (IOException e) {
            throw new JsonParamsReaderException(e);
        }
    }

    public DevicesParams read(File file) throws JsonParamsReaderException {
        final ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(file, JsonDevicesParams.class);
        } catch (IOException e) {
            throw new JsonParamsReaderException(e);
        }
    }

    private ObjectMapper getObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(StringsFilterDescription.class, new StringFilterDescriptionDeserializer());
        module.addDeserializer(String.class, new StrictStringDeserializer());
        mapper.registerModule(module);
        return mapper;
    }

    public static class JsonParamsReaderException extends Exception {

        JsonParamsReaderException(Exception cause) {
            super(cause);
        }
    }

    private static class JsonDevicesParams implements DevicesParams {

        @JsonProperty("names")
        private StringsFilterDescription nameFilterDescription;
        @JsonProperty
        private String abi;
        @JsonProperty
        private int api;
        @JsonProperty
        private int count;
        @JsonProperty
        private int minApi;
        @JsonProperty
        private int maxApi;
        @JsonProperty("providers")
        private StringsFilterDescription providerFilterDescription;
        @JsonProperty("serials")
        private StringsFilterDescription serialFilterDescription;

        @Override
        public boolean isAllDevices() {
            //there's no sense to be able to connect to unavailable devices
            return false;
        }

        @Override
        public String getAbi() {
            return abi;
        }

        @Override
        public int getApiVersion() {
            return api;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public StringsFilterDescription getNameFilterDescription() {
            return nameFilterDescription;
        }

        @Override
        public int getMinApiVersion() {
            return minApi;
        }

        @Override
        public int getMaxApiVersion() {
            return maxApi;
        }

        @Override
        public StringsFilterDescription getProviderFilterDescription() {
            return providerFilterDescription;
        }

        @Override
        public StringsFilterDescription getSerialFilterDescription() {
            return serialFilterDescription;
        }
    }

    private static class StringFilterDescriptionDeserializer extends JsonDeserializer<StringsFilterDescription> {

        private StringsFilterParser parser = new StringsFilterParser();

        @Override
        public StringsFilterDescription deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            final String value = p.getCodec().readValue(p, String.class);
            return parser.parse(value);
        }
    }

    private static class StrictStringDeserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
                return p.getText();
            }
            throw ctxt.mappingException("Invalid token type: " + p.getCurrentToken());
        }
    }

}
