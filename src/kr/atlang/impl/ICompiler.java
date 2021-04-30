package kr.atlang.impl;

/**
 * 컴파일 인터페이스 입니다. T는 결과값 추론 타입입니다.
 *
 * @author 남대영
 * */
public interface ICompiler<T> {

    ICompiler<T> compile();
    T get();

}
