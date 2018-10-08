import java.util.Collections;

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

    public void addToPopulation(){
    
    }

    // Get x amount of fittest individuals of the Island. place to fittest
    // island.
}
