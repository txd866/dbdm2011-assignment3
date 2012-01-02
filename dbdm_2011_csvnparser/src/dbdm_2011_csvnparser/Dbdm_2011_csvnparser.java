/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbdm_2011_csvnparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sandbox
 */
public class Dbdm_2011_csvnparser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String inputCSVPath = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] columns = null;

        //Check input for the file...
        try {
            System.out.println("enter csv file...");
            inputCSVPath = br.readLine();
        } catch (Exception e) {
            System.out.println("Sorry...couldn't load csv file...are you sure it exitsts?");
        }


        // Loading the file and filling op the list 
        String line;
        ArrayList arrayListContainer = null;
        try {
            FileInputStream inputfilestream = new FileInputStream(inputCSVPath);
            BufferedReader myInput = new BufferedReader(new InputStreamReader(inputfilestream));

            // Reading the first line that contains the columns and gives us 
            // a clue about how many arraylists have to be build...

            String firstLine = myInput.readLine();
            columns = firstLine.split(",");
            arrayListContainer = new ArrayList(); // arraylist that is containing arraylist with the data
            for (int i = 0; i < columns.length; i++) {
                ArrayList array = new ArrayList();
                arrayListContainer.add(array);
            }

            System.out.println("now the data...");

            while ((line = myInput.readLine()) != null) {
                String[] rowpiece = line.split(",");

                for (int j = 0; j < rowpiece.length; j++) {
                    ((ArrayList) arrayListContainer.get(j)).add(rowpiece[j]);
                }
                //System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //now asking for the columns that we want the median of...
        while (true) {
            try {
                System.out.println("on which column do you want to get the median?");

                for(int i =0;i<columns.length;i++){
                    System.out.print(columns[i] + " / ");
                }
                
                String inputcolumn = br.readLine();
                
                for(int j=0;j<columns.length;j++){
                    if(columns[j].equals(inputcolumn)){
                        ArrayList tmp = (ArrayList) arrayListContainer.get(j);
                        int size = tmp.size();
                        Collections.sort(tmp);
                        System.out.println("complete size of the array:" + size);
                        int row;
                        
                        if(size % 2 != 1){
                            // the size if perfectly dividable...

                            System.out.println("The amount of data is diviable in 2 ... a little computation is done..");

                            int first = new Integer(tmp.get((size/2)).toString());
                            int second = new Integer(tmp.get((size/2)-1).toString());
                            System.out.println("first" + first);
                            System.out.println("second" + second);
                            System.out.println("MEDIAN: " + (2+(0.5*(first-second))));
                        }else{
                            // odd number! (((n + 1)/2)th item)
                            row = ((size + 1)/2);
                            System.out.println("getting row number: " + row );
                            System.out.println("MEDIAN: " + tmp.get(row));
                        }
                        
                        
                        
                        
                    }
                }
                
            } catch (Exception e) {
                System.out.println("Sorry...couldn't load csv file...are you sure it exitsts?");
            }
        }
    }
}

