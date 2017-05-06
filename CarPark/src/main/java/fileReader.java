import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader {

    String path;

    public fileReader(String path){
        this.path = path;
    }

    public ArrayList<Floor> convertLines()throws FileNotFoundException {

        ArrayList<Floor> convertedLines = new ArrayList<Floor>();

        BufferedReader reader = new BufferedReader(new FileReader(path));

        try {
            String currentLine;
            String floors[];

            while ((currentLine = reader.readLine()) != null){
                floors = currentLine.split(" ");
                System.out.println("Floor #" + floors[0]);
                convertedLines.add(new Floor(1, Integer.valueOf(floors[0]), 10));
                System.out.println("Floor #" + floors[1]);
                convertedLines.add(new Floor(2, Integer.valueOf(floors[1]), 10));
                System.out.println("==========================");
            }

           // System.out.println("==========================");

        }catch (IOException e){
            e.printStackTrace();
        }

        return convertedLines;
    }
}
