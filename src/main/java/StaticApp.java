class Foo{
    public static String classVar = "I class var";
    public String instanceVar = "I instance var";
    public static void classMethod(){
        System.out.println(classVar);   // ok
//      System.out.println(instanceVar); // error
    }
    public void instanceMethod(){
        System.out.println(classVar);    // ok
        System.out.println(instanceVar);    // ok
    }
}

public class StaticApp {
    public static void main(String[] args){
        System.out.println(Foo.classVar);   // ok
//      System.out.println(Foo.instanceVar);    // error
        Foo.classMethod();  // ok
//      Foo.instanceMethod();   // error

        Foo f1 = new Foo();
        Foo f2 = new Foo();

        System.out.println(f1.classVar);    // I class var
        System.out.println(f1.instanceVar); // I instance var

        // static으로 선언된 변수를 수정하면 원본 class와 복제된 인스턴스 모두 값이 변경된다. (같은 값을 참조하고 있기 때문)
        f1.classVar = "changed by f1";
        System.out.println(Foo.classVar);   // changed by f1
        System.out.println(f2.classVar);    // changed by f1

        // static으로 선언되지 않은 변수를 수정하면 해당 인스턴스의 변수 값만 변경된다. (static으로 선언되지 않은 변수는 인스턴스에서 독립적으로 사용된다.)
        f1.instanceVar = "changed by f1";
        System.out.println(f1.instanceVar); // changed by f1
        System.out.println(f2.instanceVar); // I instance var
    }
}
