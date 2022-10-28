package io.netty.example._quickstart.innerclass;

public class InnerClassTest {
    public static void main(String[] args) {

        OuterClass o = new OuterClass();
        OuterClass.InnerClass i = o.new InnerClass();
        System.out.println(o);


    }
}
