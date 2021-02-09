/**
* Name: Sydney Morrow
* Student Num: 300119030
* EmptyStackException.java taken from Lab1A notes 
* NOT implemented by Sydney Morrow, original author/s listed below
* @author Natasha Gelfand
* @author Roberto Tamassia
**/


/**
 * Runtime exception thrown when one tries to perform operation top or
 * pop on an empty stack.
 */

public class EmptyStackException extends RuntimeException {
  public EmptyStackException(String err) {
    super(err);
  }
}