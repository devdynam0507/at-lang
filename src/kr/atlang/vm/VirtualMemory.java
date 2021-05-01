package kr.atlang.vm;

public class VirtualMemory {

    private byte[] memory;
    private int offset;

    public VirtualMemory(int memorySize) {
        this.memory = new byte[memorySize];
        this.offset = 0;
    }

    public int alloc(long l) {
        write(l);

        return offset;
    }

    public long access(int offset) {
        long l = 0L;

        for(int i = offset - 8; i < offset; i++) {
            l <<= 8;
            l |= (memory[i] & 0xff);
        }

        return l;
    }

    public void free(int offset) {
        for(int i = offset; i < offset + 8; i++) {
            memory[i] = 0x00000000;
        }

        this.offset = offset;
    }

    private void write(long l) {
        for(int i = offset + 7; i >= offset; i--) {
            memory[i] = (byte) (l & 0xff);
            l >>= 8;
        }

        offset += 8;
    }

}
