import java.util.*;


public class Word_Map implements Map, Iterable {

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    int full_key=0;//curent number of keys
    int keyCap=0;//DELETED s are İnclude how much kesy i use
    Node DELETED;
    private Set<String> entrySet;

    final static float LOADFACT = 0.75f;
    private Node table[];

    public Word_Map() {
        table = new Node[INITCAP];
        DELETED =new Node("DELETED",null);
        for (int i = 0; i < INITCAP; i++)
            table[i] = null;
    }

    @Override
    public Iterator iterator() {//i will hadle
        return null;
    }

    static class Node {
        private String key;
        private File_Map value;

        Node(String  key, File_Map value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public File_Map getValue() {
            return value;
        }
        // complete this class according to the given structure in homework definition
    }

    @Override
    public int size() {
        return keyCap;
    }

    @Override
    public boolean isEmpty() {

        return keyCap==0;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        int i ;
        for(i=0;i<table.length;++i){
            if(table[i].getKey()==key)
                return true;

        }
    return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        int i ;
        for(i=0;i<table.length;++i){
            if(table[i].getValue()==value)
                return true;

        }
        return false;
    }

    public File_Map get(Object key) {
        int hash = (hash((String)key) % CURRCAP);//hata verebilir.
        while (table[hash] != null && !isEq(table[hash].getKey(),(String) key))
            hash = (hash + 1) % CURRCAP;
        if (table[hash] == null){
            System.out.println("there is not annyfalue thatr you want in this file");
            return new File_Map();
        }
        else
            return table[hash].getValue();
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
            if((double )full_key/CURRCAP>LOADFACT){
                reHash();
            }
            int hash = hash((String) key) % CURRCAP;
            while (table[hash] != null && !isEq(table[hash].getKey(),(String) key))
                hash = (hash + 1) % CURRCAP;
            table[hash] = new Node((String) key, (File_Map) value);//hata olabillir
            full_key+=1;
            keyCap+=1;
            return value;

    }

    @Override
    /*You do not need to implement remove function
    * */
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
        //empty
    }

    @Override
    public void clear() {
        table=new Node[INITCAP];
        keyCap=0;
        full_key=0;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
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
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {

        ArrayList<File_Map> f=new ArrayList<File_Map>(table.length);
        int i;
        for(i=0;i<table.length;++i){
            if(table[i]!=null){
                f.add(i,table[i].value);
            }
        }
        return  f;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }


    private void reHash(){
       keyCap=0;

       int i;
       Node temp[] =new Node[table.length*2];

       for(i=0;i<table.length;i++){
          if(table[i]!=null &&table[i]!=DELETED){
              temp[hash(table[i].key)%temp.length]=table[i];
              keyCap+=1;
          }
       }
       table=temp;
       full_key=keyCap;

       CURRCAP*=2;

    }
    private static int hash(String s){
        int i,hash=0;
        for (i=0;i<s.length();++i){
            hash+=s.charAt(i)+Math.pow(s.charAt(i),i);
        }
        return hash;
    }
    public static void Word_Map_test(){

        File_Map f=new File_Map("deneme");
        NLP l=new NLP();
        Word_Map w=new Word_Map();



        int i;
        for(i=0;i<100;++i){
            String s=new String("dosya"+i);
            System.out.println(s);
            if(i!=50)
                w.put(s,f);
            else
                w.put(s,new File_Map("asd"));
        }




        w.put("a",new File_Map("a"));
        w.put("a1",new File_Map("a1"));
        w.put("a2",new File_Map("a2"));
        w.put("a3",new File_Map("a3"));
        w.put("a4",new File_Map("a4"));
        w.put("a5",new File_Map("a5"));
        w.put("a6",new File_Map("a6"));
        w.put("a7",new File_Map("a7"));
        w.put("a8",new File_Map("a8"));
        w.put("a9",new File_Map("a9"));
        w.put("a10",new File_Map("a10"));
        w.put("b",new File_Map("b"));
        w.put("b1",new File_Map("b1"));
        w.put("b2",new File_Map("b2"));
        w.put("b3",new File_Map("b3"));
        w.put("b4",new File_Map("b4"));
        w.put("b5",new File_Map("b5"));
        w.put("b6",new File_Map("b6"));
        w.put("b7",new File_Map("b7"));
        w.put("b8",new File_Map("b8"));
        w.put("b8",new File_Map("b9"));
        w.put("b2",new File_Map("b2"));
        w.put("b3",new File_Map("b3"));
        w.put("b4",new File_Map("b4"));
        w.put("b5",new File_Map("b5"));
        w.put("b6",new File_Map("b6"));
        w.put("b7",new File_Map("b7"));
        w.put("b8",new File_Map("b8"));
        w.put("b8",new File_Map("b9"));
        w.put("a",new File_Map("a"));
        w.put("a1d",new File_Map("a1"));
        w.put("a2d",new File_Map("a2"));
        w.put("a3d",new File_Map("a3"));
        w.put("a4d",new File_Map("a4"));
        w.put("a5d",new File_Map("a5"));
        w.put("a6d",new File_Map("a6"));
        w.put("a7d",new File_Map("a7"));
        w.put("a8d",new File_Map("a8"));
        w.put("a9d",new File_Map("a9"));
        w.put("a1d0",new File_Map("a10"));
        w.put("bd",new File_Map("b"));
        w.put("bd1",new File_Map("b1"));
        w.put("bd2",new File_Map("b2"));
        w.put("bd3",new File_Map("b3"));
        w.put("bd4",new File_Map("b4"));
        w.put("bd5",new File_Map("b5"));
        w.put("bd6",new File_Map("b6"));
        w.put("bd7",new File_Map("b7"));
        w.put("bd8",new File_Map("b8"));
        w.put("bs8",new File_Map("b9"));
        w.put("bd2",new File_Map("b2"));
        w.put("bs3",new File_Map("b3"));
        w.put("bs4",new File_Map("b4"));
        w.put("bs5",new File_Map("b5"));
        w.put("bs6",new File_Map("b6"));
        w.put("b7da",new File_Map("b7"));
        w.put("bsa8",new File_Map("b8"));
        w.put("ba8",new File_Map("b9"));
        w.put("aa7",new File_Map("a7"));
        w.put("aasd8",new File_Map("a8"));
        w.put("aasd9",new File_Map("a9"));
        w.put("a1d0",new File_Map("a10"));
        w.put("bd",new File_Map("b"));
        w.put("b1d",new File_Map("b1"));
        w.put("b2d",new File_Map("b2"));
        w.put("b3f",new File_Map("b3"));
        w.put("b4f",new File_Map("b4"));
        w.put("b5f",new File_Map("b5"));
        w.put("b6f",new File_Map("b6"));
        w.put("b7f",new File_Map("b7"));
        w.put("b8f",new File_Map("b8"));
        w.put("b8a",new File_Map("b9"));
        w.put("b2f",new File_Map("b2"));
        w.put("b3a",new File_Map("b3"));
        w.put("b4a",new File_Map("b4"));
        w.put("b5a",new File_Map("b5"));
        w.put("b6a",new File_Map("de"));
        w.put("b7a",new File_Map("b7"));
        w.put("b89",new File_Map("b8"));
        w.put("b8as",new File_Map("b9"));

        w.get("b4").prints();
        w.remove("b4");
        w.get("b5").prints();
        w.get("b6a").prints();

System.out.println(w.size());

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
    public void printvalu(){
        int i;
        for(i=0;i<table.length;++i){
            if(table[i]!=null){
                System.out.println(table[i].key+"   "+table[i].value+" index:"+i);
            }
        }

    }


}
