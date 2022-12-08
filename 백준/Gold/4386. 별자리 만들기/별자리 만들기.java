import java.util.*;
 
public class Main {
    
    static ArrayList<Node> startList = new ArrayList<>();
    static ArrayList<Edge> edgeList = new ArrayList<>(); 
    static int[] parent;
    static PriorityQueue<Edge> q = new PriorityQueue<>();
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        int n = scan.nextInt();
        for(int i = 0; i < n; i++) {
            double x = scan.nextDouble();
            double y = scan.nextDouble();
            startList.add(new Node(i, x, y));
        }
        
        //모든 간선 계산
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(startList.get(i).x - startList.get(j).x, 2) + Math.pow(startList.get(i).y - startList.get(j).y, 2));
                q.add(new Edge(i, j, dist));
            }
        }
        
        parent = new int[startList.size()];
        double result = kruskal();
        System.out.printf("%.2f", result);
    }
    
    public static double kruskal() {
        for(int i = 0; i < startList.size(); i++) {
            parent[i] = i;
        }
        
        double dist = 0;
        while(!q.isEmpty()) {
            Edge current = q.poll();
            
            int p1 = find(current.start);
            int p2 = find(current.end);
            
            if(p1 != p2) {
                union(p1, p2);
                dist += current.cost;
            }
        }
        return dist;
    }
    
    public static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    public static void union(int a, int b) {
        parent[a] = b;
    }
    
    public static class Node {
        int num;
        double x;
        double y;
        
        public Node(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    
    public static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double cost;
        
        public Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e) {
            if(this.cost < e.cost) return -1;
            return 1;
        }
    }
}
