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
    private int MIG_POP = 10;
    private boolean useBenchmark = false;
    private boolean useLadder = false;

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
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        // GRIDSEARCHABLE PARAMS
        numIslands = Integer.parseInt(System.getProperty("NumIslands"));
        popSize= Integer.parseInt(System.getProperty("PopSize"));
        MIG_POP = Integer.parseInt(System.getProperty("MIG_POP"));
        useBenchmark = Boolean.parseBoolean(props.getProperty("Benchmark"));
        useLadder = Boolean.parseBoolean(props.getProperty("UseLadder")); 
 
        if (! isMultimodal && ! hasStructure && !isSeparable) {
          // BentCigarFunction
          evaluations_limit_ = 150;
        }
        if (isMultimodal && ! hasStructure && !isSeparable) {
          // Katsuura Functio
          evaluations_limit_ = 100000;
        }
        if (isMultimodal && hasStructure && !isSeparable) {
          // Schaffers Function
          evaluations_limit_ = 10000;
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
        // Run your algorithm here
        int evals = 0;
        // init islands with populations
        //System.out.println("NUM_ISL: " + numIslands);
        //System.out.println("POP_SIZE: " + popSize);
        //System.out.println("BENCHMARK: " + useBenchmark);


        ArrayList<Island> islands = initIslands(numIslands, popSize);
        while(evals<evaluations_limit_){
           System.out.println("EVAL: " + evals);
         
          if (MIG_POP != 0 && evals % MIG_POP == 0 && evals > 20) {
            for(Island island: islands){
              //System.out.println("DIV_ISLAND_" + (island.island_id) + ": " + island.getDiversity(numIslands));
            }
            

            if (useBenchmark && MIG_POP>0) {
              Algorithm.benchmarkMigration(islands);
            } else {
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
              list.sort(Map.Entry.comparingByValue());
              Collections.reverse(list);

              Map<Island, Double> rankedIslands = new LinkedHashMap<Island, Double>();
              for (Map.Entry<Island, Double> entry : list) {
                  rankedIslands.put(entry.getKey(), entry.getValue());
              }

              ArrayList<Island> rankedIslandsList = new ArrayList<Island>();
              rankedIslandsList.addAll(rankedIslands.keySet());
              if (useLadder && MIG_POP>0) {
                Algorithm.eliteLadderMigration(rankedIslandsList);
              } else if (MIG_POP>0) {
                Algorithm.eliteDistributedMigration(rankedIslandsList);
              }
            }
            
            for(Island island: islands){
              //System.out.println("AVG_ISLAND_" + island.island_id + ": " + island.getPopulation().getAveragePopulationFitness());
              // MONTE CARLO
              if (island.getPopulation().getAveragePopulationFitness() > 7) {
                System.out.println("FIN_EVAL:"+evals);
                return;
              }
              //System.out.println("FIT_ISLAND_" + island.island_id + ": " + island.getPopulation().getFittestIndividual().getFitness());

            }
         }

          //
          // evolve locally on islands
          for (Island island : islands) {

            if (island.getPopulation().getAveragePopulationFitness() > 7 && MIG_POP == 0) {
              System.out.println("FIN_EVAL:"+evals);
              return;
            }
            
            Population islPopulation = island.getPopulation();
            // ArrayList<Individual> islParents = islPopulation.twoWayTournamentSelection(popSize-2);
            ArrayList<Individual> islParents = islPopulation.kWayTournamentSelection(4,popSize-2); 
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
