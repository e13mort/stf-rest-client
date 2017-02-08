package com.github.e13mort.stf.model;

import java.util.ArrayList;
import java.util.List;

public class AccessTokensResponse {

    private List<String> tokens = new ArrayList<String>();

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AccessTokensResponse {\n");

        sb.append("    tokens: ").append(toIndentedString(tokens)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
