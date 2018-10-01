import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {

  Individual[] individuals;

  /*
   * Constructors
   */
  // Create a population
  public Population(int populationSize, boolean initialise) {
      individuals = new Individual[populationSize];
      // Initialise population
      if (initialise) {
          // Loop and create individuals
          for (int i = 0; i < size(); i++) {
              Individual newIndividual = new Individual();
              newIndividual.generateIndividual();
              saveIndividual(i, newIndividual);
          }
      }
  }

  /* Getters */
  public Individual getIndividual(int index) {
      return individuals[index];
  }

  public Individual getFittest() {
      Individual fittest = individuals[0];
      // Loop through individuals to find fittest
      for (int i = 0; i < size(); i++) {
          if (fittest.getFitness() <= getIndividual(i).getFitness()) {
              fittest = getIndividual(i);
          }
      }
      return fittest;
  }

  
  // should be improved
  public void sortPopulationOnFitness(Individual[] individuals) {
    
  }


  /* Public methods */
  // Get population size
  public int size() {
      return individuals.length;
  }

  // Save individual
  public void saveIndividual(int index, Individual indiv) {
      individuals[index] = indiv;
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