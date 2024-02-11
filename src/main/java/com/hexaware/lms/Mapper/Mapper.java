package com.hexaware.lms.Mapper;

public interface Mapper<A, B> {

    B mapTo (A a);

    A mapFrom(B b);
}
