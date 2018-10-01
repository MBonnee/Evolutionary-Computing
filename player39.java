import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class player39 implements ContestSubmission {
    Random rnd_;
    // changed this to public static to be able to reference it in Individual.java
    public static ContestEvaluation evaluation_;
    private int evaluations_limit_;

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

        int numIslands = Integer.parseInt(props.getProperty("numIslands"));
  
        // Do sth with property values, e.g. specify relevant settings of your algorithm

        // find different representation => how does it affect performance / diversity

        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }

    public void run() {
        // Run your algorithm here
        int evals = 0;
        // init islands


        // init population on islands


        // calculate fitness
        while(evals<evaluations_limit_){
            // Select parents

            // Apply crossover / mutation operators
            double child[] = {1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};

            Individual ind = new Individual();
            ind.generateIndividual();
            
            Double fit = ind.getFitness();
            System.out.println(ind.toString());
            System.out.println(fit);
            System.out.println("Hoi, het werkt!");


            // Check fitness of unknown fuction
            Double fitness = (double) evaluation_.evaluate(child);
            System.out.println(fitness);
            evals++;
            // Select survivors
        }
    }
}
