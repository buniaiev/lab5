package adapter;

public class TravelingByCar implements AdvancedJourney {
   @Override
   public void travelOn(int pathLength) {
      System.out.println("Journey by car " + pathLength + "κμ.");
   }
}

