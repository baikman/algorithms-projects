public class Container {
    String key;
    Double distance;
    public Container(String newPredecessor, double newDistance){
        key = newPredecessor;
        distance = newDistance;
    }
    public Container(String newPredecessor){
        this(newPredecessor, Double.POSITIVE_INFINITY);
    }
    public Container(){
        this(null);
    }

}
