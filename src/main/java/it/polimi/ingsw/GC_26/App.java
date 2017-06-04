package it.polimi.ingsw.GC_26;

import it.polimi.ingsw.GC_26_observerAndObservableLogic.MainObserver;
import it.polimi.ingsw.GC_26_observerAndObservableLogic.MainServerView;

/**
 * Hello world!
 *
 */
public class App 

{
    public static void main( String[] args )
   
    {
        System.out.println( "Hello World!" );
        MainObserver view= new MainObserver();
        view.start();
        
        
    }
}
