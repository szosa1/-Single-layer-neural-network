import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Language {

    ArrayList<String[]> texts = new ArrayList<>();
    public ArrayList<Integer> labels = new ArrayList<>();


    public Language() {
    }

    public void readFiles(Path rootDir) {

        try {
            Files.walkFileTree(rootDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    StringBuilder string = new StringBuilder();


                    List<String> lines = Files.readAllLines(file);
                    for (String line : lines) {
                        string.append(line);
                    }
                    //System.out.println(string);
                    texts.add(new String[]{string.toString()});


                    String parentFolder = file.getParent().getFileName().toString().toLowerCase();
                    int label = switch (parentFolder) {
                        case "pl" -> 0;
                        case "eng" -> 1;
                        case "de" -> 2;
                        default -> -1;
                    };
                    labels.add(label);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getFrequency(string.toString());
    }

    public double[] getFrequency(String text) {
        int letters[] = new int[26];
        double frequency[] = new double[26];
        int lettersCount = 0;
        text = text.toLowerCase().replaceAll("[^a-z]", "");

        for(int i = 0; i < text.length(); i++) {
            letters[text.charAt(i) - 'a']++;
            lettersCount++;
        }

        for(int i = 0; i < 26; i++) {
            frequency[i] = ((double) letters[i] / lettersCount);
        }

        return frequency;
    }

    public void getWords(){

    }
}
