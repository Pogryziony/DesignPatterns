import patterns.adapter.ClassTextFile;
import patterns.adapter.ObjectTextFile;
import patterns.component.Chapter;
import patterns.composite.File;
import patterns.composite.FileSystemClient;
import patterns.composite.Folder;
import patterns.decorator.*;
import patterns.factoryMethod.*;
import patterns.observer.CelsiusDisplay;
import patterns.observer.FahrenheitDisplay;
import patterns.observer.KelvinDisplay;
import patterns.observer.TemperatureSensor;
import patterns.simpleFactory.Tag;
import patterns.simpleFactory.TagFactory;
import patterns.factoryMethod.TagMethod;
import patterns.strategy.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//       runComposite();
//       runComponent();
//       runDecorator();
//       runClassAdapter();
//       runObjectAdapter();
//       runSimpleFactory();
//       runObserver();
         runStrategy();
    }

    public static void runDecorator() {
        // Wprowadzenie tekstu
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wprowadź tekst: ");
        String inputText = scanner.nextLine();

        // Wybór znaczników
        List<TextDecorator> decorators = new ArrayList<>();
        System.out.println("Wybierz znaczniki (wpisz numery oddzielone spacjami):");
        System.out.println("1. <p>");
        System.out.println("2. <strong>");
        System.out.println("3. <em>");
        System.out.println("4. <mark>");
        System.out.print("Wybierz: ");
        String input = scanner.nextLine();
        String[] choices = input.split(" ");
        for (String choice : choices) {
            switch (choice) {
                case "1" -> decorators.add(new ParagraphDecorator(null));
                case "2" -> decorators.add(new StrongDecorator(null));
                case "3" -> decorators.add(new EmphasisDecorator(null));
                case "4" -> decorators.add(new MarkDecorator(null));
                default -> System.out.println("Nieprawidłowy wybór: " + choice);
            }
        }

        // Tworzenie dekoratorów w odwrotnej kolejności (od ostatniego do pierwszego)
        TextComponent component = new PlainText(inputText);
        for (int i = decorators.size() - 1; i >= 0; i--) {
            decorators.get(i).wrappedComponent = component;
            component = decorators.get(i);
        }

        // Wyprowadzenie do strumienia wyjściowego "otagowanego" tekstu HTML
        System.out.print("Wynik: ");
        component.write();
        System.out.println();
    }

    public static void runComponent() {
        // Tworzenie struktury dokumentu
        Chapter book = new Chapter("Książka");
        Chapter chapter1 = new Chapter("Rozdział pierwszy");
        Chapter chapter2 = new Chapter("Rozdział drugi");
        Chapter subchapter1 = new Chapter("Pierwszy podrozdział");
        Chapter subchapter2 = new Chapter("Drugi podrozdział");

        book.addComponent(chapter1);
        book.addComponent(chapter2);

        chapter2.addComponent(subchapter1);
        chapter2.addComponent(subchapter2);

        // Generowanie spisu treści
        System.out.println("Spis treści:");
        book.printTableOfContents("");

        /*
        Oczekiwany wynik:

        Spis treści:
        Książka
          Rozdział pierwszy
          Rozdział drugi
            Pierwszy podrozdział
            Drugi podrozdział
        */
    }

    public static void runComposite() {
        Folder root = new Folder("C:");
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder music = new Folder("Music");
        File document1 = new File("doc1.txt");
        File document2 = new File("doc2.txt");
        File picture1 = new File("pic1.jpg");
        File picture2 = new File("pic2.jpg");
        File song1 = new File("song1.mp3");

        root.addComponent(documents);
        root.addComponent(pictures);
        root.addComponent(music);

        documents.setParent(root);
        pictures.setParent(root);
        music.setParent(root);

        documents.addComponent(document1);
        documents.addComponent(document2);

        pictures.addComponent(picture1);
        pictures.addComponent(picture2);

        music.addComponent(song1);

        // Uruchomienie klienta
        FileSystemClient client = new FileSystemClient(root);
        client.runCommand("dir");
        client.runCommand("cd Documents");
        client.runCommand("dir");
        client.runCommand("cd ..");
        client.runCommand("cd Pictures");
        client.runCommand("dir");
        client.runCommand("cd Music");
        client.runCommand("dir");
        client.runCommand("cd ..");
        client.runCommand("cd ..");
        client.runCommand("cd ..");
        client.runCommand("dir");
    }

    public static void runObjectAdapter() {
        String filePath = "example.txt";

        try {
            // Tworzenie obiektu ObjectTextFile
            ObjectTextFile textFile = new ObjectTextFile(filePath);

            // Zapis do pliku
            textFile.writeLine("Przykładowa zawartość pliku.");
            System.out.println("\u001B[32mZapisano do pliku.\u001B[0m");

            // Odczyt z pliku
            String line = textFile.readLine();
            System.out.println("Odczytano z pliku:");
            System.out.println(line);

            // Zamknięcie pliku
            textFile.close();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
        }
    }

    public static void runClassAdapter() {
        String filePath = "example.txt";

        try {
            // Zapis do pliku
            ClassTextFile.BufferedWriterAdapter writerAdapter = ClassTextFile.createBufferedWriter(filePath);
            writerAdapter.writeLine("Przykładowa zawartość pliku.");
            writerAdapter.close();
            System.out.println("\u001B[32mZapisano do pliku.\u001B[0m");

            // Odczyt z pliku
            ClassTextFile.BufferedReaderAdapter readerAdapter = ClassTextFile.createBufferedReader(filePath);
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = readerAdapter.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            readerAdapter.close();
            System.out.println("Odczytano z pliku:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas operacji na pliku: " + e.getMessage());
        }
    }

    public static void runSimpleFactory() {
        String text = getUserInput("Enter text: ");
        String tag = getUserInput("Select a tag (strong, p, em, mark): ");

        Tag selectedTag = TagFactory.createTag(tag);

        String output = selectedTag.getStartTag() + text + selectedTag.getEndTag();
        System.out.println("Output:");
        System.out.println(output);
    }

    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void runFactoryMethod() {
        String text = getUserInput("Enter text: ");
        String tag = getUserInput("Select a tag (strong, p, em, mark): ");

        TagFactoryMethod tagFactory = createTagFactory(tag);
        TagMethod selectedTag = tagFactory.createTag();

        String output = selectedTag.getStartTag() + text + selectedTag.getEndTag();
        System.out.println("Output:");
        System.out.println(output);
    }

    private static TagFactoryMethod createTagFactory(String tag) {
        return switch (tag.toLowerCase()) {
            case "strong" -> new StrongTagFactoryMethod();
            case "p" -> new ParagraphTagFactoryMethod();
            case "em" -> new EmphasisTagFactoryMethod();
            case "mark" -> new MarkTagFactoryMethod();
            default -> throw new IllegalArgumentException("Invalid tag: " + tag);
        };
    }

    public static void runObserver() {
        TemperatureSensor sensor = new TemperatureSensor();

        CelsiusDisplay celsiusDisplay = new CelsiusDisplay();
        KelvinDisplay kelvinDisplay = new KelvinDisplay();
        FahrenheitDisplay fahrenheitDisplay = new FahrenheitDisplay();

        sensor.addObserver(celsiusDisplay);
        sensor.addObserver(kelvinDisplay);
        sensor.addObserver(fahrenheitDisplay);

        // Ustawienie nowej temperatury
        sensor.setTemperature(25.5);
    }

    private static final int ARRAY_SIZE_SMALL = 50;
    private static final int ARRAY_SIZE_MEDIUM = 500;
    private static final int ARRAY_SIZE_LARGE = 5000;
    private static final int NUM_SORT_RUNS = 10;

    public static void runStrategy() {
        int[] arraySmall = generateRandomArray(ARRAY_SIZE_SMALL);
        int[] arrayMedium = generateRandomArray(ARRAY_SIZE_MEDIUM);
        int[] arrayLarge = generateRandomArray(ARRAY_SIZE_LARGE);

        SortStrategy bubbleSort = new BubbleSort();
        SortStrategy selectionSort = new SelectionSort();
        SortStrategy quickSort = new QuickSort();

        ArraySorter sorter = new ArraySorter(bubbleSort);
        measureAndPrintAverageSortingTime(sorter, arraySmall, "Bubble Sort (Small Array)");
        measureAndPrintAverageSortingTime(sorter, arrayMedium, "Bubble Sort (Medium Array)");
        measureAndPrintAverageSortingTime(sorter, arrayLarge, "Bubble Sort (Large Array)");

        sorter.setSortStrategy(selectionSort);
        measureAndPrintAverageSortingTime(sorter, arraySmall, "Selection Sort (Small Array)");
        measureAndPrintAverageSortingTime(sorter, arrayMedium, "Selection Sort (Medium Array)");
        measureAndPrintAverageSortingTime(sorter, arrayLarge, "Selection Sort (Large Array)");

        sorter.setSortStrategy(quickSort);
        measureAndPrintAverageSortingTime(sorter, arraySmall, "Quick Sort (Small Array)");
        measureAndPrintAverageSortingTime(sorter, arrayMedium, "Quick Sort (Medium Array)");
        measureAndPrintAverageSortingTime(sorter, arrayLarge, "Quick Sort (Large Array)");

        Arrays.sort(arraySmall);
        Arrays.sort(arrayMedium);
        Arrays.sort(arrayLarge);
        measureAndPrintAverageSortingTime(sorter, arraySmall, "Arrays.sort() (Small Array)");
        measureAndPrintAverageSortingTime(sorter, arrayMedium, "Arrays.sort() (Medium Array)");
        measureAndPrintAverageSortingTime(sorter, arrayLarge, "Arrays.sort() (Large Array)");
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    private static void measureAndPrintAverageSortingTime(ArraySorter sorter, int[] array, String methodName) {
        long totalTime = 0;
        for (int i = 0; i < NUM_SORT_RUNS; i++) {
            int[] copyArray = Arrays.copyOf(array, array.length);
            long startTime = System.nanoTime();
            sorter.sortArray(copyArray);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        double averageTime = (double) totalTime / NUM_SORT_RUNS;
        System.out.println(methodName + ": Average Sorting Time = " + averageTime + " ns");
    }
}
