import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {

  private Individual[] individuals;
  private int populationSize;

  /*
   * Constructors
   */
  // Create a population
  public Population(int populationSize, boolean initialise) {
      this.populationSize = populationSize;
      individuals = new Individual[populationSize];
      // Initialise population
      if (initialise) {
          // Loop and create individuals
          for (int i = 0; i < populationSize; i++) {
              individuals[i] = new Individual();
          }
      }
  }

  /* Getters */
  public Individual getIndividual(int index) {
      return individuals[index];
  }

  public int getPopulationsSize(){
      return populationSize;
  }

  public Individual getFittestIndividual() {
      Individual fittest = individuals[0];
      // Loop through individuals to find fittest
      for (int i = 0; i < populationSize; i++) {
          if (fittest.getFitness() <= getIndividual(i).getFitness()) {
              fittest = getIndividual(i);
          }
      }
      return fittest;
  }

  
  // should be improved
  public void sortPopulationOnFitness(Individual[] individuals) {
    
  }

  @Override
  public String toString() {
    String output = "Individuals: \n";
    for (Individual ind : this.individuals) 
    { 
      output+= ind.toString() + "\n";
    }
    return output;
  }
}
