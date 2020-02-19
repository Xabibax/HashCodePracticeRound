
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Main {

    // Pair<Nom, Folder location>
    public static List<Pair<String, String>> fileLocations = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        long startTimeMillis = System.currentTimeMillis();
	// write your code here
        intiatilseFilesLocation();
        for (Pair<String, String> fileLocation : fileLocations) {
            InputDataSet inputDataSet = getInputDataSet(
                    fileLocation.getValue() + "\\" + fileLocation.getKey() + ".in"
            );
            System.out.println(System.lineSeparator() + fileLocation.getKey() +
                    System.lineSeparator() + "InputDataSet : " +
                    System.lineSeparator() + inputDataSet.toDataSet());

            OutputDataSet outputDataSet = new OutputDataSet(inputDataSet.getParticipants(), new HashMap<>());
            outputDataSet.setUnsettledPizzas(inputDataSet.getMapPizza());
            outputDataSet.addUnsettledPizzas();

            System.out.println(System.lineSeparator() +
                    "Score : \t\t" + outputDataSet.getScore() + System.lineSeparator() +
                    "Score_max : \t" + inputDataSet.getParticipants() + System.lineSeparator() +
                    "Difference : \t" + (inputDataSet.getParticipants() - outputDataSet.getScore())
            );

            System.out.println(System.lineSeparator() + "OutputDataSet :" +
                    System.lineSeparator() + outputDataSet.toDataSet()
            );
            writeOutputDataSet(
                    outputDataSet.toDataSet(),
                    fileLocation.getValue() + "\\" + fileLocation.getKey() + ".out"
            );
        }
        System.out.println(System.lineSeparator() + "Execution in : " + (System.currentTimeMillis() - startTimeMillis) + " milliseconds.");
    }

    private static void intiatilseFilesLocation() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(System.getProperty("user.dir") + "\\files.json");

        //Read JSON file
        Object obj = jsonParser.parse(reader);

        JSONArray filesArray = (JSONArray) obj;

        //Iterate over files
        filesArray.forEach(file -> fileLocations.add(new Pair<>((String) ((JSONObject) file).get("name"),
                (String) ((JSONObject) file).get("location")
        )));
    }

    private static InputDataSet getInputDataSet(String filePath) throws Exception {
        InputDataSet result = new InputDataSet();

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String firstLine = myReader.nextLine();
        String secondLine = myReader.nextLine();
        myReader.close();

        result.setParticipants(Integer.parseInt(firstLine.split(" ")[0]));
        result.setNbPizzaType(Integer.parseInt(firstLine.split(" ")[1]));

        String[] pizzaTypes = secondLine.split(" ");
        Map<Integer, Pizza> mapPizza = new HashMap<>();
        for (int i = 0; i < pizzaTypes.length ; i++) {
            mapPizza.put(i, new Pizza(i, Integer.parseInt(pizzaTypes[i])));
        }
        result.setMapPizza(mapPizza);

        if (!result.toDataSet().equals(firstLine + System.lineSeparator() + secondLine))
            throw new Exception("Erreur InputDataSet creatyed different from the actual file");

        return result;
    }

    private static void writeOutputDataSet(String outputDataSet, String outputPath) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(outputPath));
            os.write(outputDataSet.getBytes(), 0, outputDataSet.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
