import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   ArrayList<String> fnames;
   ArrayList<List<Integer>> occurances;


    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    int full_key=0;//curent number of keys
    int keyCap=0;//DELETED s are İnclude how much kesy i use
    Node DELETED;

    final static float LOADFACT = 0.75f;
    public Node table[];


    public String s;

    static class Node {
        private String key;
        private LinkedList<Integer> value;

        Node(String  key, LinkedList<Integer> value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public LinkedList<Integer> getValue() {
            return value;
        }
        // complete this class according to the given structure in homework definition
    }

    public File_Map(){
        table = new Node[INITCAP];
        DELETED =new Node("DELETED",null);
        for (int i = 0; i < INITCAP; i++)
            table[i] = null;

    }
    public File_Map(String key,LinkedList<Integer> value){
        table = new Node[INITCAP];
        DELETED =new Node("DELETED",null);
        for (int i = 0; i < INITCAP; i++)
            table[i] = null;

        if((double )full_key/CURRCAP>LOADFACT){
            reHash();
        }

        int hash = hash((String) key) % CURRCAP;
        while (table[hash] != null && !isEq(table[hash].getKey(),(String) key) )/////
            hash = (hash + 1) % CURRCAP;
        table[hash] = new Node((String) key, (LinkedList<Integer>) value);//hata olabillir
        full_key+=1;
        keyCap+=1;

    }
   public File_Map(String s){
       this.s=s;
   }
    public void prints(){
       System.out.println(s);
    }



    @Override
    public int size() {
        return full_key;//fnames size
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fnames.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return occurances.contains(value);
    }


    public LinkedList<Integer> get(Object key) {
        int hash = (hash((String)key) % CURRCAP);//hata verebilir.
        while (table[hash] != null&& !isEq(table[hash].getKey(),(String) key))
            hash = (hash + 1) % CURRCAP;
        if (table[hash] == null){
            return new LinkedList<>();
        }
        else
            return table[hash].getValue();
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {

        if((double )full_key/CURRCAP>LOADFACT){
            reHash();
        }

        int hash = hash((String) key) % CURRCAP;
        while (table[hash] != null && !isEq(table[hash].getKey(),(String) key) )/////
            hash = (hash + 1) % CURRCAP;
        table[hash] = new Node((String) key, (LinkedList<Integer>) value);//hata olabillir
        full_key+=1;
        keyCap+=1;
        return value;
    }

    @Override
    public Object remove(Object key) {
        Object temp=new File_Map();
        int i ;
        for(i=0;i<table.length;++i){
            if(table[i]!=null&&table[i].getKey()==key){
                temp=table[i].value;
                table[i]=DELETED;
                keyCap--;
            }


        }
        return temp;//hata verebilir
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear(){

    }

    @Override
    public Set keySet(){
        Set<String> temp=new TreeSet<String>();
        int i;
        for(i=0;i<table.length;++i){
            if(table[i]!=null&&table[i]!=DELETED){
                temp.add(table[i].key);
            }
        }
        return  temp;
    }

    @Override
    public Collection values() {

        ArrayList<LinkedList<Integer>> f=new ArrayList<LinkedList<Integer>>(table.length);
        int i;
        for(i=0;i<table.length;++i){
            if(table[i]!=null){
                f.add(i,table[i].value);
            }
        }
        return  f;
    }

    @Override
    public Set<Entry> entrySet() {
        //
        return null;
    }

    private static int hash(String s){
        int i,hash=0;
        for (i=0;i<s.length();++i){
            hash+=s.charAt(i)+Math.pow(s.charAt(i),i);
        }
        return hash;

    }

    private void reHash(){


        int i;
        Node temp[] =new Node[table.length*2];

        System.out.println("----------------------------------------table lenght:"+table.length+" ");
        for(i=0;i<table.length;i++){
            if(table[i]!=null &&table[i]!=DELETED){
                temp[hash((String)table[i].key)%temp.length]=table[i];
            }
        }
        table=temp;
        CURRCAP=table.length;
    }
    public void printvalu(){
        int i;
        for(i=0;i<table.length;++i){
            if(table[i]!=null){
                System.out.println(table[i].key+""+table[i].value+" index:"+i);
            }
        }

    }

    public   static  void File_Map_test(){
        LinkedList<Integer> list=new LinkedList<>();
        list.add(5);
        list.add(10);

        LinkedList<Integer> list1=new LinkedList<>();
        list1.add(5);
        list1.add(100);

        File_Map fm=new File_Map();


        String s=new String("erkan");

        fm.put(s,list);
        System.out.println(fm.get("erkan"));






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

    @Override
    public String toString() {
        return "name of file";
    }
}
