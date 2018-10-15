import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;
import java.util.Properties;
import java.util.ArrayList;

public class player39 implements ContestSubmission {
    Random rnd_;
    // changed this to public static to be able to reference it in Individual.java
    public static ContestEvaluation evaluation_;
    private int evaluations_limit_;
    private int numIslands = 1;
    private int popSize = 1;

    public player39() {
        rnd_ = new Random();
    }

    public void setSeed(long seed) {
        // Set seed of algortihms random process
        rnd_.setSeed(seed);
    }

    public void setEvaluation(ContestEvaluation evaluation) {
        // Set evaluation problem used in the run
        evaluation_ = evaluation;
        // Get evaluation properties
        Properties props = evaluation.getProperties();
        System.out.println("All properties");
        System.out.println(props);
        
        // Get evaluation limit
        // !!!!!
        evaluations_limit_ = 1; //Integer.parseInt(props.getProperty("Evaluations"));
        // Property keys depend on specific evaluation
        // E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        numIslands = Integer.parseInt(System.getProperty("NumIslands"));
        popSize= Integer.parseInt(System.getProperty("PopSize"));

        // Do sth with property values, e.g. specify relevant settings of your algorithm

        // find different representation => how does it affect performance / diversity

        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }


    private ArrayList<Island> initIslands(int num, int pop_size) {
      ArrayList<Island> islands = new ArrayList<Island>();
      for (int i = 0; i < num; i++) {
        Island island = new Island(pop_size, i+1);
        islands.add(island);
      } 
      return islands;
    }

    ///
    /// http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
    ///
    
    public void run() {
	System.out.println(evaluations_limit_);
        // Run your algorithm here
        int evals = 0;
        // init islands with populations
        ArrayList<Island> islands = initIslands(numIslands, popSize);

        for (Island island : islands) {
            System.out.println("ISLAND FITTEST INDIVIDUAL:");
            //System.out.println(island.population.getFittestIndividual().toString());
            System.out.println(island.population.getFittestIndividual().getFitness());
            //System.out.println(island.population.individuals.get(0).getFitness());
            //System.out.println(island.population.individuals.get(1).getFitness());
            System.out.println("Average");
            System.out.println(island.population.getAveragePopulationFitness());
            //island.population.sortPopulation();
            //System.out.println(island.population.individuals.get(0).getFitness());
            //System.out.println(island.population.individuals.get(1).getFitness());
            //System.out.println(island.population.individuals.get(1).initialIsland);
            System.out.println("----");

        }

        // init population on islands
        // calculate fitness
        Population population = islands.get(0).getPopulation();
	
	System.out.println(population.individuals.get(0).getFitness());	

        while(evals<2000){

            if (evals % 3 == 0) {
              // do a migration round
            } else {
              // evolve locally
            }
	//System.out.println("--nieuwe eval--");
		//System.out.println(" OUDE FITNESS" );
		population.sortPopulation();
		//population.getFitnesses();
	//System.out.println(population.getFitnesses());
		//System.out.println(population.getAveragePopulationFitness());
            // Select parents
		ArrayList<Individual> parents = population.twoWayTournamentSelection(3);
            // Apply crossover / mutation operators
		Algorithm alg = new Algorithm();
		ArrayList<Individual> childeren = alg.reproduction(parents);
        	population.addChilderen(childeren);	
            // Check fitness of unknown fuction
		population.selectSurvivors();
            	evals++;
            // Select survivors
		//System.out.println(" NIEUWE FITNESS" );
population.sortPopulation();
		//population.getFitnesses();
		System.out.println(population.getAveragePopulationFitness());
            // migrate
        }
	System.out.println(" NIEUWE FITNESSes" );
	population.getFitnesses();
    }
    
    public static void main(String[] args) {
    	System.out.println("Start Windows");
      //player39.run();
    }
}
