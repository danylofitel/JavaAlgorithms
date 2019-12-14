package wordnet;

public class WordNetTest {

    private static final String synsetsFilename = "synsets.txt";
    private static final String hypernymsFilename = "hypernyms.txt";

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(synsetsFilename, hypernymsFilename);
        for (int i = 0; i < 10; ++i) {
            int a = (int) (Math.random() * (double) wordnet.size());
            int b = (int) (Math.random() * (double) wordnet.size());
            System.out.println(wordnet.getNoun(a) + " - " + wordnet.getNoun(b) + " : " + wordnet.sap(a, b));
        }
    }
}
