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
public class ResultNode implements BinaryNode {
    
    private final boolean result;
    public ResultNode(boolean result){
        this.result = result;
    }

    @Override
    public boolean isSampleOfPositiveClass(double[] sample) {
        return this.result;
    }
    
}
