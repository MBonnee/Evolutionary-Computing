import java.util.Comparator;

public class Individual implements Comparable<Individual> {

  static int defaultGeneLength = 10;
  private double genes[];
  // Cache
  private Double fitness = 0.0;

  // Create a random individual
  // DO SOME DIFFERENT RANDOMIZATION AND EXPLORATION/EXPLOITATION INITS HERE
  public void generateIndividual() {
      double tempGens[] = new double[defaultGeneLength];
      for (int i = 0; i < defaultGeneLength; i++) {
        tempGens[i] = Math.random();
      }
      genes = tempGens;
  }

  /* Getters and setters */
  // Use this if you want to create individuals with different gene lengths
  public static void setDefaultGeneLength(int length) {
      defaultGeneLength = length;
  }
  
  public Double getGene(int index) {
      return genes[index];
  }

  public void setGene(int index, Double value) {
      genes[index] = value;
      fitness = 0.0;
  }

  /* Public methods */
  public int size() {
      return genes.length;
  }

  public Double getFitness() {
      if (fitness == 0.0) {
          fitness = (double) player39.evaluation_.evaluate(this.genes);
      }
      return fitness;
  }

  public int compareTo(Individual individual) {
    return Double.compare(this.getFitness(), individual.getFitness());
  }

  public static Comparator<Individual> FitnessComparator 
                          = new Comparator<Individual>() {

	    public int compare(Individual ind1, Individual ind2) {
	      //ascending order
	      return ind1.getFitness().compareTo(ind2.getFitness());
	    }

	};

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