package wordnet;

public class OutcastTest {

    private static final String synsetsFilename = "synsets.txt";
    private static final String hypernymsFilename = "hypernyms.txt";

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(synsetsFilename, hypernymsFilename);
        Outcast outcast = new Outcast(wordnet);

        final int wordCount = 5;
        final int tests = 100;
        for (int i = 0; i < tests; ++i) {
            String[] words = new String[wordCount];
            for (int j = 0; j < words.length; ++j) {
                if (i == j && wordnet.distance(i, j) != 0) {
                    throw new AssertionError();
                }
                words[j] = wordnet.getNoun((int) (Math.random() * (double) wordnet.size()));
                System.out.print(words[j] + "; ");
            }
            System.out.println();
            System.out.println("Outcast: " + outcast.outcast(words));
        }

        String[] words = new String[5];
        words[0] = "dog";
        words[1] = "cat";
        words[2] = "bat";
        words[3] = "rat";
        words[4] = "banana";
        for (int j = 0; j < words.length; ++j) {
            System.out.print(words[j] + "; ");
        }
        System.out.println();
        System.out.println("Outcast: " + outcast.outcast(words));
    }
}
