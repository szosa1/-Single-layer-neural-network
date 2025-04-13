import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Language lang = new Language();
        Path rootDir = Paths.get("Teksty");

        lang.readFiles(rootDir);

        double[][] inputData = new double[lang.texts.size()][26];
        int[] expectedOutputs = new int[lang.texts.size()];


        for (int i = 0; i < lang.texts.size(); i++) {
            String text = lang.texts.get(i)[0];
            inputData[i] = lang.getFrequency(text);
            expectedOutputs[i] = lang.labels.get(i);
            System.out.println(Arrays.toString(inputData[i]));

        }

        int numLanguages = 3;
        Perceptron[] perceptrons = new Perceptron[numLanguages];
        for (int i = 0; i < numLanguages; i++) {
            perceptrons[i] = new Perceptron(new double[26], 0.5, 0.5);
        }

        for (int i = 0; i < inputData.length; i++) {
            for (int j = 0; j < numLanguages; j++) {
                int expected = (expectedOutputs[i] == j) ? 1 : 0;
                perceptrons[j].learn(inputData[i], expected);
            }
        }

        //String testText = "polski tekst w jezyku polskim po polsku bo tak i nie wiem co tu jeszcze mozna by napisac ale pewnie cos";
        //String testText = "english text in english language Astronomy is a natural science that studies celestial objects and the phenomena that occur in the cosmos.Since Poland's entry into the European Union in 2004, major financing has been made available by";
        String testText = "An den Universitäten wurde die Astronomie um etwa 1800 zu einer eigenen Studienrichtung";

        double[] testFrequency = lang.getFrequency(testText);

        double[] activations = new double[numLanguages];

        int epochs = 500;

        for (int k = 0; k < epochs; k++) {
            for (int i = 0; i < inputData.length; i++) {
                for (int j = 0; j < numLanguages; j++) {
                    int expected = (expectedOutputs[i] == j) ? 1 : 0;
                    perceptrons[j].learn(inputData[i], expected);
                }
            }
        }


        for (int i = 0; i < numLanguages; i++) {
            activations[i] = perceptrons[i].computeActivation(testFrequency);
        }


        String[] languageNames = {"Polski", "Angielski", "Niemiecki"};
        System.out.println("Aktywacje perceptronów:");

        for (int i = 0; i < numLanguages; i++) {
            System.out.printf("%s: %.4f\n", languageNames[i], activations[i]);
        }


    }

}