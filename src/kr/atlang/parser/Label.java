package kr.atlang.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Label {

    private int label;
    private Queue<Integer> labelQueue;
    private Stack<String> labelSourceStack;

    public Label() {
        this.label = 0;
        this.labelQueue = new LinkedList<>();
        this.labelSourceStack = new Stack<>();
    }

    public void newPoint() {
        labelQueue.add(label);
        ++label;
    }

    public void newPoint(String cmd) {
        labelSourceStack.push(cmd);
    }

    public int dequeue() {
        return labelQueue.remove();
    }
    public String dequeueSrc() { return labelSourceStack.pop(); }

}
