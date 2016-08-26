package threads;

/**
 * @author Joachim E. Christensen
 */
public class Even {
    private int n = 0;
    synchronized public int next() {
        n++;
        n++;
        return n;
    }
    
}

