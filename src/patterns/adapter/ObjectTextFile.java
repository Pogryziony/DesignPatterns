package patterns.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ObjectTextFile {
    private BufferedReader reader;
    private BufferedWriter writer;

    public ObjectTextFile(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void writeLine(String line) throws IOException {
        if (writer == null) {
            writer = new BufferedWriter(new FileWriter(reader.toString()));
        }
        writer.write(line);
        writer.newLine();
    }

    public void close() throws IOException {
        reader.close();
        if (writer != null) {
            writer.close();
        }
    }
}
