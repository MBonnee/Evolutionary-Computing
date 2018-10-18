import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Individual {

  public int initialIsland = -1;
  static int defaultGeneLength = 10;
  private double genes[];
  // Cache
  public double fitness;

  // Create a random individual
  // DO SOME DIFFERENT RANDOMIZATION AND EXPLORATION/EXPLOITATION INITS HERE
  public Individual(int initialIsl) {
      genes = new double[defaultGeneLength];
      for (int i = 0; i < defaultGeneLength; i++) {
          genes[i] = (Math.random() * ((5 + 5) + 1)) + -5.0;
       }
      initialIsland = initialIsl;
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
      this.genes[index] = value;
      fitness = 0.0;
  }

  /* Public methods */
  public int size() {
      return genes.length;
  }

  public Double getFitness() {
      if (fitness == 0.0) {
          this.fitness = (double) player39.evaluation_.evaluate(this.genes);
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

  public boolean equals(Object obj){
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Individual)) return false;
    Individual o = (Individual) obj;
    return o.initialIsland == this.initialIsland &&
        o.defaultGeneLength == this.defaultGeneLength &&
        o.genes == this.genes &&
        o.fitness == this.fitness;
  }
 
}

class SortByFitness implements Comparator<Individual> 
{ 
    public int compare(Individual a, Individual b) 
    { 
        return -Double.compare(a.fitness, b.fitness); 
    } 
} 
