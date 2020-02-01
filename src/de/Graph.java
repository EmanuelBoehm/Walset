package de;

import java.awt.*;
import java.util.*;

public class Graph {
    //with syntax <Knot1.ID, Knot2.ID>, distance of Knot1 and Knot2
    private LinkedList<Edge> distanceGraph;
    private ArrayList<Knot> l;
    private NormInter dis;
    public ArrayList<Color> maxCol;

    public Graph(NormInter norm) {
        //   graph = new HashMap<>();
        dis = norm;
    }

    public boolean add(ArrayList<Knot> l) {
        this.l = l;
        return true;
    }

    public boolean disgraph(NormInter d) {

        distanceGraph = new LinkedList<>();
        for (int n = 0; n < l.size() - 1; n++) {
            for (int i = n + 1; i < l.size(); i++) {
                Knot nn = l.get(n);
                Knot ii = l.get(i);
                if (d.dist(ii, nn) < 500) {
                    distanceGraph.add(new Edge(nn, ii, d.dist(ii, nn)));
                }
            }
        }
        distanceGraph.sort((edge, t1) -> {
            if(edge.weight.equals(t1.weight)) return 0;
            if(edge.weight > t1.weight) return 1;
            else return -1;
        });
        return true;
    }

    /*public double maxDis() {
        double max = 9;
        Iterator<Double> it = distanceGraph.values().iterator();

        int count = 1;
        while (it.hasNext()) {
            double dd = it.next();
            if (dd > max) {
                System.out.println(count++);
            }
        }
        return max;
    }*/

    public boolean merge(double mergeDistance) {
        Iterator<Edge> it = distanceGraph.iterator();
        Edge e;
        ArrayList <Edge> delList = new ArrayList<>();
        while (it.hasNext()){
            e = it.next();
            if(e.weight > mergeDistance) break;
            delList.add(e);
        }
        System.out.println("deleting " + delList.size() + " knots");
        for (Edge edge : delList) {
            deleteKnot(edge.source);
        }
        return true;
    }

    private Knot searchKnot(int id) {
        for (Knot kn : l) {
            if (kn.ID == id) {
                return kn;
            }
        }
        return null;
    }

    private boolean deleteKnot(Knot toDel) {
        //System.out.println("counter del; " + counter++);
        Iterator <Edge> it = distanceGraph.iterator();
        Edge e;
        while(it.hasNext()){
            e = it.next();
            if(e.source == toDel || e.destination == toDel){
                it.remove();
            }
        }
        return true;
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
            a *= dis.dist(l,kn)/(dis.dist(l,currMaxList.get(0))+.5);
        }
        kn.power *= a;
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
