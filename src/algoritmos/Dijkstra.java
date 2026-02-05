package algoritmos;
import java.util.*;
public class Dijkstra {
    static class Edge{
        int target,weight;

        Edge(int target,int weight){
            this.target=target;
            this.weight=weight;
        }
    }
    private int V;
    private List<List<Edge>> adj;
    public Dijkstra(int V){
        this.V=V;
        adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());

        }
    }
    public void addEdge(int u, int v,int w){
        adj.get(u).add(new Edge(v,w));
    }
    public int[] dijkstra(int start){
        int[] dist=new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>(
                Comparator.comparingInt(a->a[1])
        );
        pq.add(new int[]{start,0});
        while(!pq.isEmpty()){
            int[] curr=pq.poll();
            int u=curr[0];
            int d=curr[1];
            if(d>dist[u]) continue;
            for(Edge e:adj.get(u)){
                if (dist[u]+e.weight<dist[e.target]) {
                    dist[e.target]=dist[u]+e.weight;
                    pq.add(new int[]{e.target,dist[e.target]});
                }
            }


        }
        return dist;

    }
    public static void main(String[] args) {
        Dijkstra dij=new Dijkstra(5);
        dij.addEdge(0,1,10);
        dij.addEdge(0,2,3);
        dij.addEdge(1,2,4);
        dij.addEdge(1,3,2);
        dij.addEdge(2,3,4);
        dij.addEdge(3,4,2);
        dij.addEdge(4,3,2);
        dij.addEdge(4,2,2);
        dij.addEdge(4,1,2);
        dij.addEdge(2,1,4);

        System.out.println(Arrays.toString(dij.dijkstra(0)));

    }
}
