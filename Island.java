import java.util.Collections;
import java.util.ArrayList;

public class Island {
    public Population population;
    private double migrationRate;
    private double islandFitness;

    public Island(int populationSize, int id) {
        population = new Population(populationSize, id, true);
        migrationRate = 0.0;
        islandFitness = 0.0;
    }

    public double getIslandFitness() {
        return islandFitness;
    }

    public Population getPopulation() {
        return population;
    }

    public void migration(Population population2){
        ArrayList<Individual> top5_2 = population2.getTop(5);
        ArrayList<Individual> top5 = population.getTop(5);

        for(Individual indiv: top5){
            population.removeIndividual(indiv);
        }
        for(Individual indiv: top5_2){
            population2.removeIndividual(indiv);
        }
        for(Individual indiv: top5){
            population2.addIndividual(indiv);
        }
        for(Individual indiv: top5_2){
            population.addIndividual(indiv);
        }

    }


    // Get x amount of fittest individuals of the Island. place to fittest
    // island.
}
