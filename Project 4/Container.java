public class Container {
    String predecessor;
    Double distance;
    public Container(String newPredecessor, double newDistance){
        predecessor = newPredecessor;
        distance = newDistance;
    }
    public Container(String newPredecessor){
        this(newPredecessor, Double.POSITIVE_INFINITY);
    }
    public Container(){
        this(null);
    }

}
