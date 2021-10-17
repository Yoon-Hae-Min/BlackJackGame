import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Record {
    Scanner sc = new Scanner(System.in);
    LinkedList<String> recordData = new LinkedList<>();
    final int ShowRecordNum=10;

    String EnterName(){
        System.out.print("Please enter your name: ");
        return sc.nextLine();
    }

    void SetRecordLocation(int betCost){
        String name=EnterName();
        String saveData=name+" "+betCost;
        int curser=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/record.txt"));
            
            while(true) {
                String line=br.readLine();
                if (line==null) break;
                StringTokenizer st = new StringTokenizer(line);
                st.nextToken();
                if(betCost<Integer.parseInt(st.nextToken())){
                    curser++;
                }
                recordData.add(line);
            }
            br.close();
        }catch(Exception FileNotFoundException){}
        recordData.add(curser,saveData);
    }

    void SaveScoreRecord(int betCost){
        FileWriter fw;
        SetRecordLocation(betCost);
        try {
            File file=new File("data/record.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            fw =new FileWriter(file);
            for(int i=0;i<ShowRecordNum;i++){
                if(i<recordData.size()){
                    fw.write(recordData.get(i)+"\n");
                }
                else break;
            }
            System.out.println("Record Succeed");
            fw.close();
        } catch (Exception IOException) {
            System.out.println("IOEXception Error");
        }
    }

    static void GetScoreRecord(){
        int i=1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/record.txt"));
            while(true) {
                String line = br.readLine();
                if (line==null) break;
                System.out.print(i+". "+line+" points"+"\n");
                i++;
            }
            br.close();
        } catch (Exception FileNotFoundException) {
            System.out.println("Not Record");
        }
    }
}
