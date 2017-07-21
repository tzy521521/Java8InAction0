package chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tzy on 2017/7/20.
 */
public class ExecuteAround {
    public static void main(String[] args) throws IOException{
        String string1=processFile((BufferedReader br)->br.readLine(), "data.txt");
        System.out.println(string1);

        String string2=processFile((BufferedReader br)->br.readLine()+br.readLine(), "./data.txt");
        System.out.println(string2);
    }
    public static String processFile(BufferedReaderProcessor p,String string) throws IOException{
        try(BufferedReader br=new BufferedReader(new FileReader(string))){
            return p.process(br);
        }
    }
}
