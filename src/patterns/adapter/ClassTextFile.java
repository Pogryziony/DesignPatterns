package patterns.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClassTextFile {
    public ClassTextFile(String filePath) {
    }

    public static class BufferedReaderAdapter {
        private BufferedReader reader;

        public BufferedReaderAdapter(String filePath) throws IOException {
            reader = new BufferedReader(new FileReader(filePath));
        }

        public String readLine() throws IOException {
            return reader.readLine();
        }

        public void close() throws IOException {
            reader.close();
        }
    }

    public static class BufferedWriterAdapter {
        private BufferedWriter writer;

        public BufferedWriterAdapter(String filePath) throws IOException {
            writer = new BufferedWriter(new FileWriter(filePath));
        }

        public void writeLine(String line) throws IOException {
            writer.write(line);
            writer.newLine();
        }

        public void close() throws IOException {
            writer.close();
        }
    }

    public static BufferedReaderAdapter createBufferedReader(String filePath) throws IOException {
        return new BufferedReaderAdapter(filePath);
    }

    public static BufferedWriterAdapter createBufferedWriter(String filePath) throws IOException {
        return new BufferedWriterAdapter(filePath);
    }
}
