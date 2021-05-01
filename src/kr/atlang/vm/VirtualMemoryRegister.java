package kr.atlang.vm;

import java.util.HashMap;
import java.util.Map;

public class VirtualMemoryRegister implements IVirtualRegister {

    private Map<String, Integer> memoryOffsetMap;
    private VirtualMemory virtualMemory;

    public VirtualMemoryRegister(VirtualMemory virtualMemory) {
        this.memoryOffsetMap = new HashMap<>();
        this.virtualMemory = virtualMemory;
    }

    @Override
    public void alloc(String var, String value) {
        int memOffset = alloc(Long.parseLong(value));
        memoryOffsetMap.put(var, memOffset);
    }

    @Override
    public int alloc(long l) {
        return virtualMemory.alloc(l);
    }

    @Override
    public void free(int offset) {
        virtualMemory.free(offset);
    }

    @Override
    public void free(String var) {
        int offset = memoryOffsetMap.get(var);

        free(offset);
    }

    @Override
    public long access(String var) {
        int offset = memoryOffsetMap.get(var);

        return virtualMemory.access(offset);
    }

}
