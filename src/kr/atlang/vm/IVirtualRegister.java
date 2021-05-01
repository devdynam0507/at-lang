package kr.atlang.vm;

public interface IVirtualRegister extends IVirtualMemory {

    void alloc(String var, String value);

    void free(String var);

    long access(String var);

}
