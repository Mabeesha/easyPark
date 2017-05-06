import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity{

    public static void main(String[] args) throws IOException, InterruptedException{

        String path = "D:\\MyStuff\\Hackathon17\\CarSlotStatus.txt";

        FileInputStream serviceAccount = new FileInputStream(
                "D:\\MyStuff\\Hackathon17\\CarPark\\hack-17-car-park-firebase-adminsdk-dkq3j-a0097a56cc.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://hack-17-car-park.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference slot = ref.child("Slots");

        while (true){

            fileReader reader = new fileReader(path);

            ArrayList<CarSlot> slots = reader.convertLines();

            slot.setValue(slots);

            Thread.sleep(1000);

           /* slot.addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    CarSlot _slot = dataSnapshot.getValue(CarSlot.class);
                    System.out.print(_slot);
                    System.out.println(" | Slot NUmber | " + _slot.getSlotNumber());
                    System.out.println("===========================");
                }

                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });*/
        }

    }

}
