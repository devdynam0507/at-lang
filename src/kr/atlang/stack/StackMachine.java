package kr.atlang.stack;

import kr.atlang.token.TokenConst;
import kr.atlang.token.TokenTable;
import kr.atlang.vm.ConsoleBuf;
import kr.atlang.vm.MiddleWare;
import kr.atlang.vm.VirtualMemoryRegister;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class StackMachine {

    private VirtualMemoryRegister memoryRegister;
    private MiddleWare middleWare;
    private TokenTable tokenTable;

    private Map<String, Integer> labelCache;
    private Stack<String> machine;
    private ConsoleBuf console;

    public StackMachine(VirtualMemoryRegister memoryRegister, MiddleWare middleWare,
                        TokenTable tokenTable, ConsoleBuf console) {
        this.memoryRegister = memoryRegister;
        this.middleWare = middleWare;
        this.tokenTable = tokenTable;
        this.console = console;

        this.labelCache = middleWare.getLabelPositions();
        this.machine = new Stack<>();
    }

    /**
     * @return 프로그램 정상 종료 여부
     * */
    public int begin() {
        List<String> vmMiddleWare = middleWare.getVmMiddleWare();
        int len = vmMiddleWare.size();

        for(int i = 0 ; i < len; i++) {
            String line = vmMiddleWare.get(i);
            String[] splitLine = line.split(" ");

            switch (splitLine[0]) {
                case MiddleWare.PUSH:
                    push(splitLine[1]);
                    break;
                case MiddleWare.OPER_ADD:
                    _add();
                    break;
                case MiddleWare.OPER_SUB:
                    _subtract();
                    break;
                case MiddleWare.OPER_MULT:
                    _multiply();
                    break;
                case MiddleWare.OPER_DIVIDE:
                    _divide();
                    break;
                case MiddleWare.CMP_OPER_BL:
                case MiddleWare.CMP_OPER_BLEQ:
                    _bl();
                    break;
                case MiddleWare.CMP_OPER_EQ:
                case MiddleWare.CMP_OPER_NEQ:
                case MiddleWare.CMP_OPER_BR:
                case MiddleWare.CMP_OPER_BREQ:
                    _eq();
                    break;
                case MiddleWare.JMP_ZERO:
                    i = _jz(splitLine[1], i) - 1;
                    break;
                case MiddleWare.JMP_NOT_ZERO:
                    i = _jnz(splitLine[1], i) - 1;
                    break;
                case MiddleWare.JMP_BL:
                    i = _jbl(splitLine[1], i) - 1;
                    break;
                case MiddleWare.JMP_BLEQ:
                    i = _jbleq(splitLine[1], i) - 1;
                    break;
                case MiddleWare.JMP_BR:
                    i = _jbr(splitLine[1], i) - 1;

                    break;
                case MiddleWare.JMP_BREQ:
                    i = _jbreq(splitLine[1], i) - 1;
                    break;
                case MiddleWare.ALLOC:
                    alloc(splitLine[1]);
                    break;
                case MiddleWare.CONBUF:
                    conbuf();
                    break;
                case MiddleWare.CONFLUSH:
                    conflush();
                    break;
            }
        }

        return 0;
    }

    private long getValue(String token) {
        int id = tokenTable.getTokenIdWithRegex(token);
        long v = 0L;

        if(id == TokenConst.INT) {
            v = Long.parseLong(token);
        } else if(id == TokenConst.IDENTIFIER) {
            v = memoryRegister.access(token);
        }

        return v;
    }

    private void push(String operand) {
        machine.push(operand);
    }

    private void _add() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        machine.push(String.valueOf((v1 + v2)));
    }

    private void _subtract() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        machine.push(String.valueOf((v1 - v2)));
    }

    private void _multiply() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        machine.push(String.valueOf((v1 * v2)));
    }

    private void _divide() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        machine.push(String.valueOf((v1 / v2)));
    }

    private void _eq() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        System.out.println("eq: " + v1 + "-" + v2 + "=" + (v1-v2));

        machine.push(String.valueOf((v1 - v2)));
        System.out.println("eq machine: " + machine);
    }

    // oper1 < oper2
    private void _bl() {
        long v1 = getValue(machine.pop());
        long v2 = getValue(machine.pop());

        machine.push(String.valueOf(v2 - v1));
    }

    // o1 @@ 02
    //o1 - o2 == 0
    private int _jz(String label, int line) {
        System.out.println(machine);
        long v1 = getValue(machine.pop());
        System.out.println("jz: " + v1);

        if(v1 != 0 || v1 > 0 || v1 < 0) {
            line = labelCache.get(label);
        } else {
            line++;
        }

        return line;
    }

    private int _jnz(String label, int line) {
        long v1 = getValue(machine.pop());

        if(v1 == 0) {
            line = labelCache.get(label);
        } else {
            line++;
        }

        return line;
    }

    //o1 < o2 2 - 3
    // o1 - o2 < 0 -> true
    private int _jbl(String label, int line) {
        long v1 = getValue(machine.pop());

        if(v1 != 0 && v1 > 0) {
            line = labelCache.get(label);
        } else {
            ++line;
        }

        return line;
    }

    //o1 <= o2 2 - 3
    // o1 - o2 <= 0 -> true
    //2 <= 2
    //2 <= 3
    private int _jbleq(String label, int line) {
        long v1 = getValue(machine.pop());

        if(v1 != 0 && v1 > 0) {
            line = labelCache.get(label);
        } else {
            line ++;
        }

        return line;
    }

    //o1 > o2
    //o1 - o2 > 0
    private int _jbr(String label, int line) {
        long v1 = getValue(machine.pop());

        //- 30
        // 30
        if(v1 != 0 && v1 > 0) {
            line = labelCache.get(label);
        } else {
            ++line;
        }

        return line;
    }

    // 1 >= 2
    // 2 >= 2
    // 3 >= 2
    private int _jbreq(String label, int line) {
        long v1 = getValue(machine.pop());

        if(v1 != 0 && v1 > 0) {
            line = labelCache.get(label);
        } else {
            ++line;
        }

        return line;
    }

    private void alloc(String var) {
        long v1 = getValue(machine.pop());
        memoryRegister.alloc(var, String.valueOf(v1));
    }

    private void conbuf() {
        long v1 = getValue(machine.pop());
        console.addBuf(v1);
    }

    private void conflush() {
        console.out();
    }

//    public static final String JMP_ZERO = "_jz";
//    public static final String JMP_NOT_ZERO = "_jnz";
//    public static final String JMP_BL = "_jbl";
//    public static final String JMP_BLEQ = "_jbleq";
//    public static final String JMP_BR = "_jbr";
//    public static final String JMP_BREQ = "_jbreq";
}
