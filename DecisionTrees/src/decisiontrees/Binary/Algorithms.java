/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontrees.Binary;

import java.util.*;

/**
 *
 * @author Costin
 *       // decision: 
        // x < splitPoint ---> negative class a.k.a false
        // x >= splitPoint ---> positive class a.k.a true
        // 
        // now count the mismatches
 */
public class Algorithms {
    public static BinaryNode train(double[][] samplesPerColumn, 
            boolean[] classesPerColumn,
            Set<Integer> availableSamples){
        
        if(availableSamples.size() == 1) {
            return new ResultNode(classesPerColumn[availableSamples.toArray(new Integer[1])[0]]);
        }
        
        double bestSplitPoint = 0;
        int bestFeatureIndex = -1;
        int fewestMistakes = -1;
        for(int featureIndex = 0; featureIndex < samplesPerColumn.length; featureIndex++){
            Integer[] valuesIndexes = availableSamples.toArray(new Integer[availableSamples.size()]);
            double[] sortedValues = new double[valuesIndexes.length];
            int c = 0;
            for(int valueIndex: valuesIndexes){
                sortedValues[c++] = samplesPerColumn[featureIndex][valueIndex];
            }
            
            Arrays.sort(sortedValues);
            
            for(int j=0; j<sortedValues.length - 1; j++){
                double splitPoint = (sortedValues[j] + sortedValues[j+1]) / 2.0;
                int mistakes = mistakesFor(splitPoint, samplesPerColumn[featureIndex], availableSamples, classesPerColumn);
                if(mistakes < fewestMistakes){
                    fewestMistakes = mistakes;
                    bestFeatureIndex = featureIndex;
                    bestSplitPoint = splitPoint;
                }
            }
        }
        
        
        
        Set<Integer> leftSamplesIndexes = new HashSet<>();
        Set<Integer> rightSamplesIndexes = new HashSet<>();
        for(int j: availableSamples){
            if(samplesPerColumn[bestFeatureIndex][j] < bestSplitPoint){
                leftSamplesIndexes.add(j);
            } else {
                rightSamplesIndexes.add(j);
            }
        }
        
        
        
        return new SplitNode(bestSplitPoint, bestFeatureIndex, 
        train(samplesPerColumn, classesPerColumn,))
    }
    
    public static int mistakesFor(double splitPoint, double[] features, Set<Integer> availableIndexes,
            boolean[] classes) {

        int mistakes = 0;
        for(int j: availableIndexes){
            if( !(features[j] < splitPoint && classes[j] == false)){
                mistakes += 1;
            }
            if( !(features[j] >= splitPoint && classes[j] == true)){
                mistakes += 1;
            }
        }
        
        return mistakes;
    }
}
