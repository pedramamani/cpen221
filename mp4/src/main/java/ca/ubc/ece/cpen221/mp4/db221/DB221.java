package ca.ubc.ece.cpen221.mp4.db221;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.ca.ubc.ece.cpen221.mp4.*;

public class DB221 {

    private String dbName;
    private Map<String, List<List<String>>> tables  = new HashMap<>();
    private Map<String, List<String>> columns = new HashMap<>();


    /**
     * Constructor method for DB221
     * @param dbName name of database that only contains alphanumeric characters
     *               with a alphabetic character as the first character
     */
    public DB221(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Constructor method for DB221
     * @param dbName name of database that only contains alphanumeric characters
     *               with a alphabetic character as the first character
     * @param tablePaths list of valid file paths to valid JSON files
     */
    public DB221(String dbName, List<String> tablePaths) {
        this.dbName = dbName;
        JsonParser parser = new JsonParser();
        for(String tablePath : tablePaths){
            load(tablePath);
        }
    }

    /**
     * Given a command String, parse it and execute it
     * @param command to be executed
     * @return the appropriate String output
     */
    public String exec(String command) {
    }


    public void snapshot() {
        String timeStamp = new SimpleDateFormat("yyyyMMMdd-kkmm").format(new Date());
        String dirName = dbName + "-snapshot-" + timeStamp;
        new File(dirName).mkdirs();

        for(String k : tables.keySet()){
            store(k, dirName+"/");
        }
    }

    public static void main(String[] args) {
        // TODO
    }

    private void create(String tableName, List<String> columns){
        this.tables.put(tableName, new ArrayList<>());
        this.columns.put(tableName, columns); //REP EXPOSURE here?
    }

    private void load (String filePath){
        JsonParser parser = new JsonParser();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            JsonObject json = (JsonObject) parser.parse(fr);
            String tableName =  removeQuotesEnds(json.get("table_name").toString());

            JsonArray array = json.get("table").getAsJsonArray();
            List<String> columns = new ArrayList<>();
            List<List<String>> table = new ArrayList<>();

            for(int j =0; j < array.size(); j++){
                JsonObject object = (JsonObject) array.get(j);
                object.entrySet();
                table.add(new ArrayList<>());
                for(Map.Entry<String, JsonElement> k : object.entrySet()){
                    String columnName = k.getKey();
                    if(!columns.contains(columnName)) {
                        columns.add(columnName);
                    }
                    String value = removeQuotesEnds(k.getValue().toString());
                    table.get(j).add(columns.indexOf(columnName), value);
                }
            }
            this.tables.put(tableName, table);
            this.columns.put(tableName, columns);

        } catch (IOException ioe) {
        }
    }

    public void store(String tableName, String path){

        JsonObject obj = new JsonObject();
        obj.addProperty("status", "success");
        obj.addProperty("table_name", tableName);

        JsonArray array = new JsonArray();
        for(int i = 0; i < tables.get(tableName).size(); i++){
            JsonObject element = new JsonObject();
            for(int j = 0; j < columns.get(tableName).size(); j++){
               element.addProperty(columns.get(tableName).get(j), tables.get(tableName).get(i).get(j));
            }
            array.add(element);
        }
        obj.add("table", array);

        String finalName = path+tableName+".json";
        try (Writer writer = new FileWriter(finalName)) {
            Gson gson = new Gson();
            gson.toJson(obj, writer);
        }
        catch(IOException e){
                    }


    }

    private void insert(String tableName, List<List<String>> rows){
    for(int i =0; i < rows.size(); i++){
        if( !tables.get(tableName).contains( rows.get(i))){
            tables.get(tableName).add(rows.get(i)); //REP EXPOSURE?
        }
    }
    }

    public void print(String tableName){

        tableComparator comparator = new tableComparator();

        Collections.sort(tables.get(tableName), comparator);
        int numCol = columns.get(tableName).size();
        int[] colWidth = new int[numCol];

        for(int i = 0; i < columns.get(tableName).size(); i++ ){
            int max = 0;
            for(int j  = 0; j < tables.get(tableName).size(); j++){
                int size = tables.get(tableName).get(j).get(i).length();
                if (size > max){ max = size;}
            }
            colWidth[i] = max+5;
        }

        for(int i = 0; i < numCol; i++){
            StringBuilder builder = new StringBuilder();
            builder.append("%-");
            builder.append(colWidth[i]);
            builder.append("s");
            System.out.format(builder.toString(), columns.get(tableName).get(i));
        }
        System.out.print("\n \n");

        for (int j = 0; j < tables.get(tableName).size(); j ++){
            for(int i = 0; i < numCol; i++){
                StringBuilder builder = new StringBuilder();
                builder.append("%-");
                builder.append(colWidth[i]);
                builder.append("s");
                System.out.format(builder.toString(), tables.get(tableName).get(j).get(i));
            }
            System.out.print("\n");
        }
    }


    private void exit_quit(){
        //TODO
        //what do we do?
    }

    private class tableComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> l1, List<String> l2) {
            int comparison;
            for (int i = 0; i < l1.size(); i++) {
                comparison = l1.get(i).compareTo(l2.get(i));
                if (comparison != 0) {
                    return comparison;
                }
            }
            return 0;
        }
    }

    private String removeQuotesEnds(String tar){
        StringBuilder builder = new StringBuilder(tar);
        builder.deleteCharAt(tar.length()-1);
        builder.deleteCharAt(0);
        return builder.toString();
    }
}
