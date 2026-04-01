public class Container {
    String key;
    Integer distance;
    public Container(String newPredecessor, Integer newDistance){
        key = newPredecessor;
        distance = newDistance;
    }
    public Container(String newPredecessor){
        this(newPredecessor, 0);
    }
    public Container(){
        this(null);
    }

}
