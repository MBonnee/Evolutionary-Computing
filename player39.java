import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;
import java.util.Properties;
import java.util.ArrayList;
import java.util.*;

public class player39 implements ContestSubmission {
    Random rnd_;
    // changed this to public static to be able to reference it in Individual.java
    public static ContestEvaluation evaluation_;
    private int evaluations_limit_;
    private int numIslands = 3;
    private int popSize = 10;
    private boolean useBenchmark = false;

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
        // E.g. double param = Doble.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        numIslands = Integer.parseInt(System.getProperty("NumIslands"));
        popSize= Integer.parseInt(System.getProperty("PopSize"));
        useBenchmark = Boolean.parseBoolean(props.getProperty("Benchmark")); 


        if (! isMultimodal && ! hasStructure && !isSeparable) {
          // BentCigarFunction
          System.out.println("We are using BentCigarFunction");
        }
        if (isMultimodal && ! hasStructure && !isSeparable) {
          // Katsuura Functio
          numIslands = 5;
          popSize = 5;
          System.out.println("We are using Katsuura Function");
        }
        if (isMultimodal && hasStructure && !isSeparable) {
          // Schaffers Function
          System.out.println("We are using Schaffers Function");
        }
    }


    private ArrayList<Island> initIslands(int num, int pop_size) {
        System.out.println("num: " + num);
        System.out.println("pop_size: " + pop_size);
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
        int MIG_POP = 5;
        // init islands with populations
        System.out.println("NUM_ISL: " + numIslands);
        System.out.println("POP_SIZE: " + popSize);
        System.out.println("BENCHMARK: " + useBenchmark);


        ArrayList<Island> islands = initIslands(numIslands, popSize);
        while(evals<1000){
          if (evals % MIG_POP == 0 && evals > 20) {
            // sort populations on islands rank them, prepare migration pools
            Map<Integer, Population> popMap = new HashMap<Integer, Population>();
            Map<Island, Double> avgMap = new HashMap<Island, Double>();
            for (Island island : islands) {
              Population migPopulation = island.getPopulation();
              migPopulation.sortPopulation();
              popMap.put(island.island_id, migPopulation);
              avgMap.put(island, migPopulation.getAveragePopulationFitness());
            }

            List<Map.Entry<Island, Double>> list = new ArrayList<>(avgMap.entrySet());
            Map<Island, Double> unrankedIslands = new LinkedHashMap<Island, Double>();
            for (Map.Entry<Island, Double> entry : list) {
                unrankedIslands.put(entry.getKey(), entry.getValue());
            }
            ArrayList<Island> unrankedIslandsList = new ArrayList<Island>();
            unrankedIslandsList.addAll(unrankedIslands.keySet());

            list.sort(Map.Entry.comparingByValue());
            Collections.reverse(list);

            Map<Island, Double> rankedIslands = new LinkedHashMap<Island, Double>();
            for (Map.Entry<Island, Double> entry : list) {
                rankedIslands.put(entry.getKey(), entry.getValue());
            }

            ArrayList<Island> rankedIslandsList = new ArrayList<Island>();
            rankedIslandsList.addAll(rankedIslands.keySet());
            if (!useBenchmark) {
              Algorithm.benchmarkMigration(rankedIslandsList);
            } else {
              Algorithm.eliteLadderMigration(rankedIslandsList);
            }
            
            // System.out.println("Before migration: " + unrankedIslandsList.get(1).getPopulation().getIndividual(1));
            // System.out.println("After migration: " + unrankedIslandsList.get(1).getPopulation().getIndividual(1));
            System.out.println("EVAL: " + evals);
            for(int i = 0; i < unrankedIslandsList.size(); i++){
              System.out.println("DIV_ISLAND_" + (i+1) + ": " + unrankedIslandsList.get(i).getDiversity(numIslands));
              System.out.println("AVG_ISLAND_" + (i+1) + ": " + unrankedIslandsList.get(i).getPopulation().getAveragePopulationFitness());
              System.out.println("FIT_ISLAND_" + (i+1) + ": " + unrankedIslandsList.get(i).getPopulation().getFittestIndividual().getFitness());
            }
         }
          //
          // evolve locally on islands
          for (Island island : islands) {
            Population islPopulation = island.getPopulation();
            ArrayList<Individual> islParents = islPopulation.twoWayTournamentSelection(popSize-2);
            // System.out.println(islParents);
            island.evolvePopulation(islParents);
            islPopulation.selectSurvivors();
 
          }
        
        // TODO: DO LOGGING HERE
        evals++;
      } 
    }
    
    public static void main(String[] args) {
    	System.out.println("Start Windows");
      //player39.run();
    }
}
