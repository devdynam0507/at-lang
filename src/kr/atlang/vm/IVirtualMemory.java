package kr.atlang.vm;

public interface IVirtualMemory {

    long access(String var);

    int alloc(long l);

    void free(int offset);

}
