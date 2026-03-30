public class Container {
    String predecessor;
    Double distance;
    public Container(String newPredecessor){
        predecessor = newPredecessor;
        distance = (Double.POSITIVE_INFINITY);
    }
    public Container(){
        this(null);
    }

}
