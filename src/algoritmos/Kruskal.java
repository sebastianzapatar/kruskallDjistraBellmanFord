package algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    static class Edge{
        int u,v,w;
        Edge(int u,int v,int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }
    }
    private int V;
    private List<Edge> edges;
    public Kruskal(int V){
        this.V=V;
        edges=new ArrayList<>();
    }
    public void addEdge(int u,int v,int w){
        edges.add(new Edge(u,v,w));
    }
    static class DSU{//Para encontrar ciclos y mirar que todo este conectado
        //disjoint set union DSU
        int[] parent;
        int[] rank;//El menor costo para que este contectado el grafo
        DSU(int n){
            parent=new int[n];
            rank=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;//Cada nodo esta conectado con el mismo
                rank[i]=0; //La distancia entre el nodo y el mismo es 0
            }
        }
        int find(int x){
            if(parent[x]!=x) {
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }
        boolean union(int a,int b){
            int ra=find(a);
            int rb=find(b);
            if(ra==rb) return false;
            if(rank[ra]<rank[rb]){
                parent[rb]=ra;
            }
            else if(rank[ra]>rank[rb]){
                parent[ra]=rb;
            }
            else{
                rank[rb]=ra;
                rank[ra]++;
            }
            return true;
        }
    }
    public List<Edge> kruskal(){
        edges.sort(Comparator.comparingInt(e->e.w));
        DSU dsu=new DSU(V);
        List<Edge> res=new ArrayList<>();
        for(Edge e:edges){
            if(dsu.union(e.u,e.v)){
                res.add(e);
                if(res.size()==V-1){
                    break;
                }
            }

        }
        return res;
    }
    public void printMST(List<Edge> edges){
        int total=0;
        for(Edge e:edges){
            total+=e.w;
        }
        System.out.println("MST: "+total);
    }

    public static void main(String[] args) {
        Kruskal kruskal=new Kruskal(5);
        kruskal.addEdge(0,1,2);
        kruskal.addEdge(0,3,6);
        kruskal.addEdge(1,2,3);
        kruskal.addEdge(1,3,8);
        kruskal.addEdge(1,4,5);
        kruskal.addEdge(2,4,7);
        kruskal.addEdge(3,4,9);
        List<Edge> edges=kruskal.kruskal();
        kruskal.printMST(edges);
    }
}
