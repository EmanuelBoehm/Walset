package de;

import java.awt.*;
import java.util.*;

public class Graph {

    private LinkedList<Edge> distanceGraph;
    private ArrayList<Knot> l;
    private NormInter dis;
    public ArrayList<Color> maxCol;

    public Graph(NormInter norm) {
        //   graph = new HashMap<>();
        dis = norm;
    }

    public void add(ArrayList<Knot> l) {
        this.l = l;
    }

    public void disgraph(NormInter d) {

        distanceGraph = new LinkedList<>();
        for (int n = 0; n < l.size() - 1; n++) {
            for (int i = n + 1; i < l.size(); i++) {
                Knot nn = l.get(n);
                Knot ii = l.get(i);
                distanceGraph.add(new Edge(nn, ii, d.dist(ii, nn)));

            }
        }
        distanceGraph.sort((edge, t1) -> {
            if(edge.weight.equals(t1.weight)) return 0;
            if(edge.weight > t1.weight) return 1;
            else return -1;
        });

    }

    public boolean merge(double mergeDistance) {
        Iterator<Edge> it = distanceGraph.iterator();
        Edge e;
        ArrayList <Edge> delList = new ArrayList<>();
        while (it.hasNext()){
            e = it.next();
            if(e.weight > mergeDistance) break;
            delList.add(e);
        }

        /*System.out.println("existing knots " + l.size());
        System.out.println("existing edges " + distanceGraph.size());
        System.out.println("deleting " + delList.size() + " edges");*/
        int count = 0;
        for (Edge edge : delList) {
            deleteKnot(edge.destination);
            //if(count++%1000 == 0) System.out.println("deleted " + count + " knots");
        }
        return true;
    }

    private void deleteKnot(Knot toDel) {
        //System.out.println("counter del; " + counter++);
        Iterator <Edge> it = distanceGraph.iterator();
        Edge e;

        while(it.hasNext()){
            e = it.next();
            if(e.source == toDel){
                e.destination.power += e.source.power;
                it.remove();
            }
            if(e.destination == toDel){
                e.source.power += e.destination.power;
                it.remove();
            }
        }
    }

    public ArrayList<String> getMax(int k){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Knot> maxL = new ArrayList<>();
        ArrayList<Color> maxCol = new ArrayList<>();
        for (Knot kn : l) {
            if (maxL.size() < k) {
                maxL.add(kn);
            } else {
                weight(kn, maxL);
                if (maxL.get(0).power < kn.power) {
                    maxL.set(0, kn);
                    maxL.sort(Comparator.comparingInt(knot -> knot.power));
                }
            }
        }
        maxL.forEach(x -> maxCol.add(x.knot));
        maxL.forEach(x -> res.add(x.getRGB()));
        this.maxCol = maxCol;
        return res;
    }

    //increase power of kn if distance from current Colors is large
    private void weight(Knot kn, ArrayList<Knot> currMaxList){
        double a = 1.0;
        for(Knot l: currMaxList){
            double strength = 0.1;
            a *= dis.dist(l,kn);

        }
        kn.power += 2*a;
    }
    static class Edge{
        Knot source;
        Knot destination;
        Double weight;

        public Edge(Knot source, Knot destination, Double weight){
            this.destination = destination;
            this.source = source;
            this.weight = weight;
        }
    }
}
