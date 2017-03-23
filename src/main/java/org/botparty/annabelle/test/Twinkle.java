package org.botparty.annabelle.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.botparty.annabelle.domain.Script;

import java.io.File;
import java.io.IOException;

public class Twinkle {

    public static Script create() {

        String fileName = "TwinkleTwinkle.json";
        ClassLoader classLoader = Twinkle.class.getClassLoader();
        File scriptFile = new File(classLoader.getResource(fileName).getFile());

        ObjectMapper mapper = new ObjectMapper();
        Script script = null;
        try {
            script = mapper.readValue(scriptFile, Script.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return script;
    }
}
