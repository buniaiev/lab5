package adapter;

/**
 * Select the desired Class
 */
public class JourneyAdapter implements Journey{
   AdvancedJourney advancedJourney;
   public JourneyAdapter(String typeJourney){
      if(typeJourney.equals("boat")){
         advancedJourney = new TravelingByBoat();
      } else if(typeJourney.equals("car")){
         advancedJourney = new TravelingByCar();
      }
   }
   @Override
   public void travel(String typeJourney, int pathLength) {
      advancedJourney.travelOn(pathLength);
   }
}
