package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileModifier {

    String path;
    ArrayList<String> list;

    // This is constructor, which mean when the time instantiate, this function will be executed automatically
    // So the outcome of this particular class is to convert All Information in path into ArrayList<String>
    // Let say there are 5 lines in a text file, the output will become [ 'line1', 'line2', 'line3'...]
    public TextFileModifier (String path){
        FileReader fr;
        path = "src/data/" + path + ".txt";
        this.path = path;

        {
            try {

                fr = new FileReader(this.path);
                BufferedReader br = new BufferedReader(fr);

                try {
                    list = new ArrayList<>();
                    String line;
                    while((line = br.readLine()) != null) {
                        list.add(line);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                try {
                    while(br.ready()){
                        System.out.println(br.readLine());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<String[]> toArrayListofStringArray(){

        ArrayList<String[]> twoDimensionArrayList = new ArrayList<>();
        String[] splitList;

        for(String s : list){
            splitList = s.split(",");
            twoDimensionArrayList.add(splitList);
        }
        return twoDimensionArrayList;
    }

    public List<String> toListString(){
        return this.list;
    }

    // Modify the one line base on ID,
    // The ID must be unique
    // Need to pass exactly number of parameter base on text file "column"
    public void updateRecord(String id, String[] content){
        String path = this.path;
        String[] newcontent = {id + "," + String.join(",",content)};
        // If the affectedline is just one record
        ArrayList<String[]> line = new ArrayList<>();

        for (String[] s : this.toArrayListofStringArray()){

            if(s[0].equals(id)){
                line.add(newcontent);
            }else{
                line.add(s);
            }
        }

        try{
            BufferedWriter bm = new BufferedWriter(new FileWriter(path));
            for(String[] s : line){
                bm.write(String.join(",", s));
                bm.newLine();
            }
            bm.close();

        }catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createRecord(String[] content) {
        String path = this.path;
        int id = getId(path);

        String[] newContent = new String[content.length + 1];
        newContent[0] = String.valueOf(id);
        System.arraycopy(content, 0, newContent, 1, content.length);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            // Join the array into a single string separated by commas
            bw.write(String.join(",", newContent));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getId(String path) {
        int id = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitList = line.trim().split(",");
                if (Integer.parseInt(splitList[0]) > id) {
                    id = Integer.parseInt(splitList[0]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Increment the ID for the new record
        id++;
        return id;
    }

    public void deleteRecord(String id) {
        List<String[]> lines = toArrayListofStringArray();
        List<String[]> updatedLines = new ArrayList<>();

        // Find and remove the record with the specified ID
        for (String[] s : lines) {
            if (!s[0].equals(id)) {
                updatedLines.add(s);
            }
        }

        // Write the updated list back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path))) {
            for (String[] line : updatedLines) {
                bw.write(String.join(",", line));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void get2DList(){

        for (String[] StringArray : toArrayListofStringArray()){
            for(String s : StringArray){
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
