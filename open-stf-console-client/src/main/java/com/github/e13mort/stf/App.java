package com.github.e13mort.stf;

import com.github.e13mort.stf.client.ConsoleClient;

public class App {

    public static void main(String... args) {
        ConsoleClient client = new ConsoleClient();
        client.run(args);
    }
}
