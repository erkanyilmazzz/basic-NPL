import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File_Map f=new File_Map("deneme");
        NLP l=new NLP();
        Word_Map w=new Word_Map();

        LinkedList<Integer> list=new LinkedList<Integer>();



        Word_Map.Word_Map_test();//it works properly
        File_Map.File_Map_test();//it works properly
        NLP.NPL_Test();//an strange  this may hapen
    }
}
