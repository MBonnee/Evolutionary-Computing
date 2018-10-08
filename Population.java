import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Population {

    private ArrayList<Individual> individuals;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new ArrayList<Individual>();
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize; i++) {
                individuals.add(new Individual());
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
