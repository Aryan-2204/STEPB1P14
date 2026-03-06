class ParkingLot{

    String[] spots=new String[500];

    int hash(String plate){
        return Math.abs(plate.hashCode())%spots.length;
    }

    public void park(String plate){

        int index=hash(plate);

        while(spots[index]!=null)
            index=(index+1)%spots.length;

        spots[index]=plate;

        System.out.println("Vehicle "+plate+" parked at "+index);
    }

    public void exit(String plate){

        for(int i=0;i<spots.length;i++){

            if(plate.equals(spots[i])){
                spots[i]=null;
                System.out.println("Vehicle exited spot "+i);
                return;
            }
        }
    }
}