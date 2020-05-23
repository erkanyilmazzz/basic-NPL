import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NLP
{
    public Word_Map wmap;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */


    public void readDataset(String dir) {
        wmap=new Word_Map();
        ArrayList<String> s=new ArrayList<>();

        File f=new File(dir);
        try {
            Scanner scan =new Scanner(f);
            scan.useDelimiter("[^A-Za-z-1-9]+");
            int i=0;
            while (scan.hasNext()){
                s.add(i,scan.next());
                ++i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i,j;

        File_Map fm=new File_Map();
        LinkedList<Integer> l=new LinkedList<>();

        int counter=0;
        for(i=0;i<s.size();++i){
             fm=new File_Map();
             l=new LinkedList<>();
            l.add(i);
            fm.put(dir,l);
            for(j=i;j<s.size();++j){
                if(isEq(s.get(i),s.get(j))){
                    l.add(j);
                }
            }
            int t;
            for (t=0;t<s.size();++t)
                wmap.put(s.get(t),fm);
        }

        wmap.printvalu();
        System.out.println(wmap.get("International").get(dir));



    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word){

        return null;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName)
    {
        return 0f;
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap() {
        wmap.printvalu();
    }

    public static void NPL_Test(){
        NLP nlp=new NLP();
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000026");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000048");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000165");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000167");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000178");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000194");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000202");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000283");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000402");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000458");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000503");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000527");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000026");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000605");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000639");
        nlp.readDataset("C:\\Users\\erkan\\IdeaProjects\\BasicNLP\\dataset\\0000641");

    }

    private static boolean isEq(String a,String b){
        if(a.length()==b.length()){
            int i;
            for(i=0;i<a.length();++i){
                if(a.charAt(i)!=b.charAt(i)){
                    return false;
                }
            }
            return true;
        }else
            return false;

    }


}
