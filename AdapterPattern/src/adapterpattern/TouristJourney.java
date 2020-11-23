packageadapter;

public 	class	 TouristJourney	 implements	 Journey {
   JourneyAdapter	 journeyAdapter;
   @Override
   public	 void	 travel(String	typeJourney,	 int	 pathLength)	 {
      if(!typeJourney.equals("car")&&!typeJourney.equals("boat")){	
        System.out.println("("+typeJourney+") This type of travel is	notavailable.");
      }	 else{
        journeyAdapter	 =	 new	 JourneyAdapter(typeJourney);
        journeyAdapter.travel(typeJourney,	 pathLength);
      }
   }
}