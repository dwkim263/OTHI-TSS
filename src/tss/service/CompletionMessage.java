/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.service;

/**
 *
 * @author Steve
 */
public class CompletionMessage extends Message {
    
    public CompletionMessage(){
        
    }
    
    public CompletionMessage(String taskName){
        setMessage(taskName);
    }
    
    @Override
    public void setMessage(String taskName) {
        super.setMessage(taskName + " is successfully completed!");
    }    
}
