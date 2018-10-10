import java.util.Collections;
import java.util.*;

public class Algorithm {
	int decimals = 1;
	
	public Individual uniformCrossOver(Individual indiv1, Individual indiv2, int mergeRate) {
		Individual child = new Individual(indiv1.initialIsland);
		for(int i = 0; i < child.size(); i++) {
			if(Math.random() > mergeRate) {
				child.setGene(i, indiv1.getGene(i));
			}else {
				child.setGene(i, indiv2.getGene(i));
			}
		}
		return child;
	}
	
	public Individual nPointCrossOver(Individual indiv1, Individual indiv2, int num) {
		Individual child = new Individual(indiv1.initialIsland);
		LinkedList<Integer> places = new LinkedList<Integer>();
		for(int i = 0; i < num; i++) {
			int newPlace = (int) (Math.random()*(indiv1.size()-0.5));
			if(places.contains(newPlace) || newPlace == 0) {
				i--;
				continue;
			}else {
				places.add(newPlace);
			}
		}
		Collections.sort(places);
		System.out.println(places);

        switch (num) {
            case 1:  onePointCrossOver(indiv1, indiv2, places, child);
                     break;
            case 2:  twoPointCrossOver(indiv1, indiv2, places, child);
                     break;
            case 3:  threePointCrossOver(indiv1, indiv2, places, child);
                     break;
            case 4:  fourPointCrossOver(indiv1, indiv2, places, child);
                     break;
            default: randomPointCrossOver(indiv1, indiv2, places, child);
                     break;
        }
		return child;
	}

	
	public void onePointCrossOver(Individual indiv1, Individual indiv2, LinkedList<Integer> places, Individual child) {
		for(int i = 0; i < child.size(); i++) {
			if(i >= places.get(0)) {
				child.setGene(i, indiv2.getGene(i));
			}else {
				child.setGene(i, indiv1.getGene(i));
			}
		}
	}
	
	public void twoPointCrossOver(Individual indiv1, Individual indiv2, LinkedList<Integer> places, Individual child) {
		for(int i = 0; i < child.size(); i++) {
			if(i >= places.get(0) && i < places.get(1)) {
				child.setGene(i, indiv2.getGene(i));
			}else {
				child.setGene(i, indiv1.getGene(i));
			}
		}
	}
	
	public void threePointCrossOver(Individual indiv1, Individual indiv2, LinkedList<Integer> places, Individual child) {
		for(int i = 0; i < child.size(); i++) {
			if((i >= places.get(0) && i < places.get(1)) || i >= places.get(2)) {
				child.setGene(i, indiv2.getGene(i));
			}else {
				child.setGene(i, indiv1.getGene(i));
			}
		}
	}
	
	public void fourPointCrossOver(Individual indiv1, Individual indiv2, LinkedList<Integer> places, Individual child) {
		for(int i = 0; i < child.size(); i++) {
			if((i >= places.get(0) && i < places.get(1)) || (i >= places.get(2) && i < places.get(3))) {
				child.setGene(i, indiv2.getGene(i));
			}else {
				child.setGene(i, indiv1.getGene(i));
			}
		}
	}
	
//	public Individual childOnePointCrossover(Individual indiv1, Individual indiv2, LinkedList<Integer> places) {
//		double mergePoint = Math.round(Math.random()*10*10)/10;
//		Individual child = new Individual(indiv1.initialIsland);
//		for(int i = 0; i < child.size(); i++) {
//			if(i >= mergePoint) {
//				child.setGene(i, indiv1.getGene(i));
//			}else {
//				child.setGene(i, indiv2.getGene(i));
//			}
//		}
//		return child;
//	}
//	
//	public Individual childTwoPointCrossover(Individual indiv1, Individual indiv2) {
//		double mergePoint1 = Math.round(Math.random()*5*10)/10;
//		double mergePoint2 = Math.round(Math.random()*5*10)/10 + 5;
//		System.out.println("Merge points: " + mergePoint1 + " - " + mergePoint2);
//		Individual child = new Individual(indiv1.initialIsland);
//		for(int i = 0; i < child.size(); i++) {
//			if(i >= mergePoint1 && i < mergePoint2) {
//				child.setGene(i, indiv1.getGene(i));
//			}else {
//				child.setGene(i, indiv2.getGene(i));
//			}
//		}
//		return child;
//	}
//	
//	public Individual childThreePointCrossover(Individual indiv1, Individual indiv2) {
//		double mergePoint1 = Math.round(Math.random()*3*10)/10;
//		double mergePoint2 = Math.round(Math.random()*3*10)/10 + 3;
//		double mergePoint3 = Math.round(Math.random()*4*10)/10 + 6;
//		System.out.println("Merge points: " + mergePoint1 + " - " + mergePoint2 + " - " + mergePoint3);
//		Individual child = new Individual(indiv1.initialIsland);
//		for(int i = 0; i < child.size(); i++) {
//			if((i >= mergePoint1 && i < mergePoint2) || i >= mergePoint3) {
//				child.setGene(i, indiv1.getGene(i));
//			}else {
//				child.setGene(i, indiv2.getGene(i));
//			}
//		}
//		return child;
//	}
//	
//	public Individual childFourPointCrossover(Individual indiv1, Individual indiv2) {
//		double mergePoint1 = Math.round(Math.random()*3*10)/10;
//		double mergePoint2 = Math.round(Math.random()*2*10)/10 + 3;
//		double mergePoint3 = Math.round(Math.random()*2*10)/10 + 5;
//		double mergePoint4 = Math.round(Math.random()*3*10)/10 + 7;
//		System.out.println("Merge points: " + mergePoint1 + " - " + mergePoint2 + " - " + mergePoint3 + " - " + mergePoint4);
//		Individual child = new Individual(indiv1.initialIsland);
//		for(int i = 0; i < child.size(); i++) {
//			if((i >= mergePoint1 && i < mergePoint2) || (i >= mergePoint3 && i < mergePoint4)) {
//				child.setGene(i, indiv1.getGene(i));
//			}else {
//				child.setGene(i, indiv2.getGene(i));
//			}
//		}
//		return child;
//	}
	
	public void randomPointCrossOver(Individual indiv1, Individual indiv2, LinkedList<Integer> places, Individual child) {
		double random = Math.random();
		if(random <= 0.25) {
			onePointCrossOver(indiv1, indiv2, places, child);
		}else if(random <= 0.5) {
			twoPointCrossOver(indiv1, indiv2, places, child);
		}else if(random <= 0.75) {
			threePointCrossOver(indiv1, indiv2, places, child);
		}else {
			fourPointCrossOver(indiv1, indiv2, places, child);
		}
	}
	
	public void mutateRandom(Individual indiv, double mutationProbability) {
		double mutationProb = mutationProbability;
		for(int i = 0; i < indiv.size(); i++) {
			if(Math.random() < mutationProb) {
				indiv.setGene(i, Math.round((Math.random()*10 - 5)*Math.pow(10, decimals))/(Math.pow(10, decimals)));
			}
		}
	}
	
	public void mutateMoveRight(Individual indiv, int times) {
		for(int j = 0; j < times; j++) {
			double temp = indiv.getGene(indiv.size()-1);
			for(int i = indiv.size()-1; i > 0; i--) {
				indiv.setGene(i, indiv.getGene(i-1));
			}
			indiv.setGene(0, temp);
		}
	}
	
	public void mutateMoveLeft(Individual indiv, int times) {
		for(int j = 0; j < times; j++) {
			double temp = indiv.getGene(0);
			for(int i = 0; i < indiv.size()-1; i++) {
				indiv.setGene(i, indiv.getGene(i+1));
			}
			indiv.setGene(indiv.size()-1, temp);
		}
	}
	
	public void swap(Individual indiv) {
		this.inversion(indiv, 2);
	}
	
	public void scramble(Individual indiv, int num) {
		LinkedList<Integer> places = new LinkedList<Integer>();
		LinkedList<Double> numbers = new LinkedList<Double>();
		for(int i = 0; i < num; i++) {
			int newPlace = (int) (Math.random()*(indiv.size()-0.5));
			if(places.contains(newPlace)) {
				i--;
				continue;
			}else {
				double gene = indiv.getGene(newPlace);
				places.add(newPlace);
				numbers.add(gene);
			}
		}
		Collections.shuffle(numbers);
		for(int i = 0; i < num; i++) {
			indiv.setGene(places.get(i), numbers.get(i));
		}
	}
	
	public void inversion(Individual indiv, int num) {
		LinkedList<Integer> places = new LinkedList<Integer>();
		LinkedList<Double> numbers = new LinkedList<Double>();
		for(int i = 0; i < num; i++) {
			int newPlace = (int) (Math.random()*(indiv.size()-0.5));
			if(places.contains(newPlace)) {
				i--;
				continue;
			}else {
				double gene = indiv.getGene(newPlace);
				places.add(newPlace);
				numbers.add(gene);
			}
		}
		Collections.reverse(numbers);
		for(int i = 0; i < num; i++) {
			indiv.setGene(places.get(i), numbers.get(i));
		}
	}
	
    // here we do all the mutation, crossover, selection methods etc.
    // Mutate an individual
    /*
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
    }*/
  
  
}
