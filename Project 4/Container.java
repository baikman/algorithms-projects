public class Container {
    String predecessor;
    Integer distance;
    public Container(String newPredecessor){
        predecessor = newPredecessor;
        distance = (Integer) Math.infinity;
    }
}
