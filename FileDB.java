package hotel_project;

import java.util.*;
import java.io.*;

public class FileDB {
    private String DBFolder = "D:\\HotelDB\\";
    private String FileName;
    
    
    public FileDB(String FileName)
    {
        this.FileName=FileName;
    }
    
    public boolean appendFile(String text) throws Exception
    {
        try(FileWriter DB = new FileWriter(this.DBFolder + this.FileName + ".txt" , true))
        {
        DB.append(text).append("\n");
        DB.close();
        return true;
        }
    }
    
    public ArrayList<String> readFile() throws Exception
    {
        File myFile = new File(this.DBFolder + this.FileName + ".txt");
        ArrayList<String> Data = new ArrayList<>();
        try (Scanner readFile = new Scanner(myFile)) {
            while (readFile.hasNextLine()) {
                String data = readFile.nextLine();
                Data.add(data);
            }
        }
        return Data;
    }
    
    
    public boolean updateFile(String oldText , String newText) throws Exception
    {
        File myFile = new File(this.DBFolder + this.FileName + ".txt");
        File tmpFile = new File(this.DBFolder + "tmp" + ".txt");
        try (Scanner fileInput = new Scanner(myFile); PrintWriter fileOutput = new PrintWriter(tmpFile)) {
            while (fileInput.hasNext()) {
                String original = fileInput.nextLine();
                if (original.equals(oldText)) {
                    if (newText.isEmpty()) continue;
                    fileOutput.println(newText);
                } else {
                    fileOutput.println(original);
                }
            }
        }
        return myFile.delete() && tmpFile.renameTo(myFile);
    }
    
    public String encode(ArrayList<String>word)
    {
        String s = "";
        for(String str : word)
        {
            s += str + " ";
        }
        return s;
    }
    
    public ArrayList<String> decode(String s) {
        ArrayList<String> result = new ArrayList<>();
        Scanner st = new Scanner(s);
        while(st.hasNext())
        {
            result.add(st.next());
        }
        return result;
    }
}
