import java.util.Collections;
import java.util.ArrayList;

public class Island {
    public Population population;
    private double migrationRate;
    private double islandFitness;
    private double islandMutationRate;
    private double islandSplitPerc;
    private int islandCrossOverNr;
    public int island_id;

    public Island(int populationSize, int id) {
        population = new Population(populationSize, id, true);
        island_id = id;
        migrationRate = 0.0;
        islandFitness = 0.0;
        islandMutationRate = 0.2;//per gen % kans op mutation naar nieuwe random value
        islandSplitPerc = 0.5;// split voor hoeveel mutation en hoeveel crossover
        islandCrossOverNr = 1;// 0 voor random, 1,2,3,4 voor n point crossover
    }

    public double getIslandFitness() {
        return islandFitness;
    }

    public Population getPopulation() {
        return population;
    }
    
    private void updateEvolveRate() {
    	double popFitness = population.getAveragePopulationFitness();
    	double topFitness = population.getFittestIndividual().fitness;
    	if(popFitness < 4.0) {
    		islandMutationRate = 0.5;
        	islandSplitPerc = 0;
        	islandCrossOverNr = 0;
    	}else if(popFitness < 7.0) {
    		islandMutationRate = 0.3;
        	islandSplitPerc = 0.3;
        	islandCrossOverNr = 0;
    	}else {
    		islandMutationRate = 0.1;
        	islandSplitPerc = 0.6;
        	islandCrossOverNr = 0;
    	}
    	
    	if(topFitness < 1.0) {
    		islandMutationRate += 0.2;
    	}else if(topFitness > 5.0) {
    		islandMutationRate -= 0.05;
        	islandSplitPerc += 0.1;
    	}
    }
    
    public void evolvePopulation(ArrayList<Individual> parents) {
    	updateEvolveRate();
    	Algorithm alg = new Algorithm();
	    ArrayList<Individual> children = alg.reproduction(parents, islandSplitPerc, islandCrossOverNr, islandMutationRate);
       population.addChilderen(children);	
    }

    // Get x amount of fittest individuals of the Island. place to fittest
    // island.
}
