package org.sniffhu.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @auth snifferhu
 * @date 2018/4/24 20:50
 */
public class Pipeline {
    private Queue<Valve> valveList = new ConcurrentLinkedQueue<>();

    public Queue<Valve> getValveList() {
        return valveList;
    }

    public Pipeline setValveList(Queue<Valve> valveList) {
        this.valveList = valveList;
        return this;
    }

    public Pipeline addValve(Valve valve) {
        if (this.valveList == null){
            valveList = new ConcurrentLinkedQueue<>();
        }
        this.valveList.add(valve);
        return this;
    }
}
