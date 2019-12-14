package wordnet;

public class Outcast {

    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        int[] dist = new int[nouns.length];
        int max = -1;
        int maxIndex = -1;

        for (int i = 0; i < dist.length; ++i) {
            int indexI = wordnet.nounIndex(nouns[i]);
            for (int j = 0; j < dist.length; ++j) {
                int indexJ = wordnet.nounIndex(nouns[j]);
                dist[i] += wordnet.distance(indexI, indexJ);
            }
            if (dist[i] > max) {
                max = dist[i];
                maxIndex = i;
            }
        }

        return nouns[maxIndex >= 0 ? maxIndex : 0];
    }
}
