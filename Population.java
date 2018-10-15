import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


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
  
  public void addChilderen(ArrayList<Individual> childeren) {
	  for(Individual child: childeren) {
		  addIndividual(child);
	  }
  }
  
  public void addIndividual(Individual indiv) {
	  individuals.add(indiv);
  }

  // Save individual
  public void saveIndividual(int index, Individual indiv) {
      individuals.add(index,indiv);
  }
  
  public ArrayList<Individual> getTop(int numberOfBest){
	  ArrayList<Individual> bestIndividual = new ArrayList<Individual>(); 
	  sortPopulation();
	  for(int i = 0; i<numberOfBest+1; i++){
		  bestIndividual.add(individuals.get(i));
	  }
	  return bestIndividual;
  }
  
  public ArrayList<Individual> getBottom(int numberOfWorst){
	  ArrayList<Individual> worstIndividual = new ArrayList<Individual>(); 
	  sortPopulation();
	  for(int i = 0; i<numberOfWorst+1; i++){
		  worstIndividual.add(individuals.get(individuals.size()-i));
	  }
	  return worstIndividual;
  }
  
  public ArrayList<Individual> twoWayTournamentSelection(int numberOfParents){
	  ArrayList<Individual> parents = new ArrayList<Individual>();
	  
	  for(int i=0; i<numberOfParents+1;i++){
		  int randomNum = ThreadLocalRandom.current().nextInt(0, individuals.size() + 1);
		  Individual contestant1 = individuals.get(randomNum);
		  int randomNum2 = ThreadLocalRandom.current().nextInt(0, individuals.size() + 1);
		  Individual contestant2 = individuals.get(randomNum2);
		  if(contestant1.getFitness() > contestant2.getFitness()){
			  parents.add(contestant1);
		  }else{
			  parents.add(contestant2);
		  }
	  }	  
	 return parents;
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
