package jsvg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by einstein on 02/12/16.
 */
public class Style{
    public String content = "";

    public Style(String cssFilePath) throws IOException {
        Path path = Paths.get(cssFilePath);
        if (Files.exists(path)) {
            content = new String(Files.readAllBytes(path));
        } else
            throw new IOException("CSS file not found.");
    }

    public Style() {
    }


    @Override
    public String toString() {
        return String.format("<style type=\"text/css\" >\n" +
                "      <![CDATA[\n" +
                "\n" +
                "%s\n" +
                "\n" +
                "      ]]>\n" +
                "</style>", content);
    }
}
