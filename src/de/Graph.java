package de;

import java.awt.*;
import java.util.*;

public class Graph {
    private HashMap<ArrayList<Integer>, Double> distanceGraph;
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
        distanceGraph = new HashMap<>();
        for (int n = 0; n < l.size() - 1; n++) {
            for (int i = n + 1; i < l.size(); i++) {
                Knot nn = l.get(n);
                Knot ii = l.get(i);
                if (d.dist(ii, nn) < 500) {
                    ArrayList<Integer> al = new ArrayList<>();
                    al.add(nn.ID);
                    al.add(ii.ID);
                    distanceGraph.put(al, d.dist(ii, nn));
                }
            }
        }
        return true;
    }

    public double maxDis() {
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
    }

    public boolean merge() {
        ArrayList<Knot> toDelList = new ArrayList<>();
        for (Map.Entry<ArrayList<Integer>, Double> entry : distanceGraph.entrySet()) {

            if (entry.getValue() < 1){
                Knot kn = searchKnot(entry.getKey().get(0));
                Knot toDel = searchKnot(entry.getKey().get(1));
                if (kn != null && toDel != null) {
                    kn.power += toDel.power;
                    toDelList.add(toDel);
                }
            }
        }
        System.out.println("deleting " + toDelList.size() + " knots");
        for(int i = 0; i < toDelList.size(); i++){
            deleteKnot(toDelList.get(i));
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
        ArrayList<ArrayList<Integer>> al = new ArrayList<>();
        distanceGraph.keySet().forEach(x -> {
            if(x.contains(toDel.ID)){
                al.add(x);
            }
        });
        for (ArrayList<Integer> key: al){
            distanceGraph.remove(key);
        }
            return true;
    }
    public ArrayList<String> getMax(int k){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Knot> maxL = new ArrayList<>();
        ArrayList<Color> maxCol = new ArrayList<>();
        Iterator<Knot> it = l.iterator();
        while(it.hasNext()){
            Knot kn = it.next();
            if(maxL.size() < k) {
                maxL.add(kn);
            }else {
                weight(kn, maxL);
                if (maxL.get(0).power < kn.power) {
                    maxL.set(0, kn);
                    maxL.sort((knot, t1) -> knot.power > t1.power ? 1 : -1);
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
            a *= dis.dist(l,kn)/(dis.dist(l,currMaxList.get(0))+.4);
        }
        kn.power *= a;
    }
}
