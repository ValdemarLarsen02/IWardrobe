package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    private String path = "data/items.csv";

    public void saveData(List<Integer> valuables) {
        try (FileWriter fw = new FileWriter(path)) {
            for (Integer number : valuables) {
                fw.write(number + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int numberToRemove) {
        try {
            List<Integer> valuables = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    int number = Integer.parseInt(line.trim());
                    if (number != numberToRemove) {
                        valuables.add(number);
                    }
                }
            }

            try (FileWriter fw = new FileWriter(path)) {
                for (Integer number : valuables) {
                    fw.write(number + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
