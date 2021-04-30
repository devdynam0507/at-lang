package kr.atlang.parser;

import java.util.LinkedList;
import java.util.Queue;

public class Label {

    private int label;
    private Queue<Integer> labelQueue;

    public Label() {
        this.label = 0;
        this.labelQueue = new LinkedList<>();
    }

    public void newPoint() {
        labelQueue.add(label);
        ++label;
    }

    public int dequeue() {
        return labelQueue.remove();
    }

}
