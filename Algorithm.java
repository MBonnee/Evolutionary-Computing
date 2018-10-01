import java.util.Collections;

public class Algorithm {
  // here we do all the mutation, crossover, selection methods etc.
  // Mutate an individual
  private static void mutate(Individual indiv) {
    // Loop through genes
    for (int i = 0; i < indiv.size(); i++) {
        if (Math.random() <= mutationRate) {
            // Create random gene
            double gene = (double) Math.random();
            indiv.setGene(i, gene);
        }
    }
  }
  // Evolve a population
  public static Population evolvePopulation(Population pop) {
    Population newPopulation = new Population(pop.size(), false);

    // Keep our best individual
    if (elitism) {
        newPopulation.saveIndividual(0, pop.getFittest());
    }

    // Crossover population
    int elitismOffset;
    if (elitism) {
        elitismOffset = 1;
    } else {
        elitismOffset = 0;
    }
    // Loop over the population size and create new individuals with
    // crossover
    for (int i = elitismOffset; i < pop.size(); i++) {
        Individual indiv1 = tournamentSelection(pop);
        Individual indiv2 = tournamentSelection(pop);
        Individual newIndiv = crossover(indiv1, indiv2);
        newPopulation.saveIndividual(i, newIndiv);
    }

    // Mutate population
    for (int i = elitismOffset; i < newPopulation.size(); i++) {
        mutate(newPopulation.getIndividual(i));
    }

    return newPopulation;
  }


}