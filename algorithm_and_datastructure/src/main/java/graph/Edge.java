package graph;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class Edge<V> {
    private V begin;
    private V end;
    private double weight;

    public Edge(V begin,V end,double weight){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public Edge(V begin,V end){
        this(begin,end,0);
    }

    public V getBegin() {
        return begin;
    }

    public V getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        int i = date.get(weekFields.weekOfYear());
        System.out.println(i);
    }
}
