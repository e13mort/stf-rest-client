package com.github.e13mort.stf.client;

import com.github.e13mort.stf.client.parameters.DevicesParams;
import com.github.e13mort.stf.client.parameters.JsonDeviceParametersReader;
import com.github.e13mort.stf.client.parameters.JsonDeviceParametersReader.JsonParamsReaderException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParamsDeviceConnectorTest {

    @DisplayName("valid params files parsing")
    @ParameterizedTest(name = "file: {0}")
    @ValueSource(strings = {
            "valid_min_api.json",
            "valid_max_api.json",
            "valid_api.json",
            "valid_count.json",
            "valid_abi.json",
            "valid_names.json",
            "valid_providers.json",
            "valid_serials.json"
    })
    void readParamsFromFile_validFiles_success(String testFileName) throws JsonParamsReaderException {
        readParams(testFileName);
    }

    @DisplayName("invalid params should throw exception")
    @ParameterizedTest(name = "param file: {0} -> exception: {1}")
    @MethodSource("invalidFiles")
    void readParamsFromFile_invalidFiles_jsonException(String fileName, Class<Throwable> exceptionClass) {
        Assertions.assertThrows(exceptionClass, () -> readParams(fileName));
    }

    @DisplayName("full valid connection params file should be parsed")
    @Test
    void readParamsFromFile_validFullFile_exactMatching() throws JsonParamsReaderException {
        final DevicesParams params = readParams("valid_full_params.json");
        assertEquals(10, params.getCount());
        assertEquals(10, params.getApiVersion());
        assertEquals(10, params.getMinApiVersion());
        assertEquals(10, params.getMaxApiVersion());
        assertEquals("arm", params.getAbi());
        assertNotNull(params.getSerialFilterDescription());
        assertNotNull(params.getNameFilterDescription());
        assertNotNull(params.getProviderFilterDescription());
    }

    @DisplayName("url of a valid params file url should be parsed")
    @Test
    void readParamsFromUrl_validFile_success() throws MalformedURLException, JsonParamsReaderException {
        final URL url = getFile("valid_full_params.json").toURL();
        readParams(url);
    }

    @DisplayName("url of invalid params file should throws an exception")
    @Test
    void readParamsFromUrl_invalidUrl_exception() throws MalformedURLException {
        final URL url = getFile("invalid").toURL();
        assertThrows(JsonParamsReaderException.class, () -> readParams(url));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> invalidFiles() {
        return Stream.of(
                Arguments.of("invalid_field_name.json", JsonParamsReaderException.class),
                Arguments.of("invalid_field_value_simple.json", JsonParamsReaderException.class),
                Arguments.of("invalid_field_value_custom.json", JsonParamsReaderException.class),
                Arguments.of("not_a_file", JsonParamsReaderException.class)
        );
    }

    private DevicesParams readParams(URL url) throws JsonParamsReaderException {
        final JsonDeviceParametersReader reader = new JsonDeviceParametersReader();
        return reader.read(url);
    }

    private DevicesParams readParams(String testFileName) throws JsonParamsReaderException {
        final JsonDeviceParametersReader reader = new JsonDeviceParametersReader();
        return reader.read(getFile(testFileName));
    }

    private File getFile(String testFileName) {
        final URL resource = getClass().getClassLoader().getResource("connection_params/" + testFileName);
        return new File(resource != null ? resource.getFile() : "not_existing_file");
    }

}