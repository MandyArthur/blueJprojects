import java.util.ArrayList;
import java.util.Iterator; 

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
   {
         Lot selectedLot = getLot(lotNumber);
         if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
                    
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
                  
            }
        }
    }
    
    /**
     * Details of successful bids and usold lots
     * at end of Auction
     */
    public void close(){
        
        System.out.println("Closing auction.");
        // Alert to end of auction 
              for(Lot lot : lots) { // for every lot in Lot
            System.out.print(lot.getNumber() + ": " + lot.getDescription()); // print their lot number and description 
            if ( lot.getHighestBid() == null ) { // if the highest bid is 0, print no bids
                System.out.println(" (No bids)");
            }
            else {
                Bid highestBid = lot.getHighestBid(); // local variable to store the value returned from calls to the getHighestBid method 
                System.out.println(" sold to " + highestBid.getBidder().getName() + " for " + highestBid.getValue());
            }
        }
    }
        /**
         * Iterator version of close method
         * 
         */
    public void closeIt(){
        
        Iterator <Lot> it = lots.iterator(); 
        while(it.hasNext()){
            Lot l = it.next(); 
           // System.out.print(l.getDetails()); Stuck here! 
        }
    }
        
        /**
         * Ex 4.49 Get Unsold Lots
         * 
         */
        public ArrayList<Lot> getUnsold(){
            
            ArrayList<Lot> unsold = new ArrayList<Lot>();
            for(Lot lot:lots){
                Bid bid = lot.getHighestBid(); 
                if (bid == null){
                    unsold.add(lot); 
            }
        }
        return unsold; 
      }

      
   
   /**
      * Return the lot with the given number. Return null
      * if a lot with this number does not exist.
      * @param lotNumber The number of the lot to return.
      */
     public Lot getLot(int lotNumber)
     {
         if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
             // The number seems to be reasonable.
             Lot selectedLot = lots.get(lotNumber - 1);
             // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                                   selectedLot.getNumber() +
                                   " was returned instead of " +
                                   lotNumber);
              
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                               " does not exist.");
               
            return null;
        }

    }
}

