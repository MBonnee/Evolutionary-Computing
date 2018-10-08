public class Individual {

    static int defaultGeneLength = 10;
    private double genes[];
    private double fitness;

    // Create a random individual
    // DO SOME DIFFERENT RANDOMIZATION AND EXPLORATION/EXPLOITATION INITS HERE
    public Individual() {
        genes = new double[defaultGeneLength];
        for (int i = 0; i < defaultGeneLength; i++) {
            genes[i] = Math.random();
        }
        fitness = 0.0;
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public Double getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, double value) {
        genes[index] = value;
        fitness = 0.0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public double getFitness() {
        if (fitness == 0.0) {
            fitness = (double) player39.evaluation_.evaluate(this.genes);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "{";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i)+",";
        }
        geneString += "}";
        return geneString;
    }
}
