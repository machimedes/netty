package io.netty.example._quickstart.innerclass;

public class OuterClass {
    int outer = 1;
    InnerClass innerClass = new InnerClass();

    public InnerClass createInnerClass() {
        return new InnerClass();
    }

    public class InnerClass {
        int inner = 0;
        {
            System.out.println(outer);
            System.out.println(OuterClass.this.outer);
            System.out.println(OuterClass.this);
        }
    }
}
