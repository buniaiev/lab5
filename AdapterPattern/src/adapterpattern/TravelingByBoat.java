package adapter;

public class TravelingByBoat implements AdvancedJourney {
   @Override
   public void travelOn(int pathLength) {
      System.out.println("Journey to the boat " + pathLength + "κμ.");
   }
}
