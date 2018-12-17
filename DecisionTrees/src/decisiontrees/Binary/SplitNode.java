/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontrees.Binary;

/**
 *
 * @author Costin
 */
public class SplitNode implements BinaryNode {
    
    private final double splitValue; 
    private final int featureIndex;
    private final BinaryNode left;
    private final BinaryNode right;
    
    public SplitNode(double splitValue, int index, BinaryNode left, BinaryNode right) {
        this.splitValue = splitValue;
        this.featureIndex = index; 
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSampleOfPositiveClass(double[] sample) {
        if(sample[this.featureIndex] < splitValue){
            return this.left.isSampleOfPositiveClass(sample);
        }
        return this.right.isSampleOfPositiveClass(sample);
    }
    
}
