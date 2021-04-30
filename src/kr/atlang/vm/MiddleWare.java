package kr.atlang.vm;

import java.util.ArrayList;
import java.util.List;

public class MiddleWare {

    private List<String> vmMiddleWare;

    public MiddleWare() {
        this.vmMiddleWare = new ArrayList<>();
    }

    public void addMiddle(String middleWareSrc) {
        vmMiddleWare.add(middleWareSrc);
    }

}
