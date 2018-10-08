import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Population {

    public ArrayList<Individual> individuals;
    public double fitness = 0.0;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, int islandId, boolean initialise) {
        individuals = new ArrayList<Individual>();
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize; i++) {
                individuals.add(new Individual(islandId));
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public int getPopulationsSize(){
        return individuals.size();
    }

    public double getAveragePopulationFitness() {
      double sum = 0.0;
      for (Individual ind : individuals) {
          sum += ind.getFitness();
      }
      fitness = sum / this.getPopulationsSize(); 
      return fitness; 
    }

    public Individual getFittestIndividual() {
        Individual fittest = individuals.get(0);
        // Loop through individuals to find fittest
        for (Individual individual: individuals) {
            if (fittest.getFitness() <= individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

  public void sortPopulation() {
    Collections.sort(individuals, new SortByFitness()); 
  }

  /* Public methods */
  // Get population size
  public int size() {
      return individuals.size();
  }

  // Save individual
  public void saveIndividual(int index, Individual indiv) {
      individuals.add(index,indiv);
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
