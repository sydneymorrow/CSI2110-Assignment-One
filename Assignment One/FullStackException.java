/**
* Name: Sydney Morrow
* Student Num: 300119030
* FullStackException.java taken from Lab1A notes 
* NOT implemented by Sydney Morrow, original author/s listed below
* @author Natasha Gelfand
* @author Roberto Tamassia
**/

/**
 * Runtime exception thrown when one tries to perform operation push
 * on a full stack.
 */

public class FullStackException extends RuntimeException {
  public FullStackException(String err) {
    super(err);
  }
}
