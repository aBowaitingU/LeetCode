### 编译

使用javac编译器时，加上文件名，斜杆是路径分隔符，带拓展名.java。

使用java虚拟机时，加上类名，点号分隔包名，无拓展名。
```
javac pack1/demo.java
java pack.demo
```

---

### 数值

可以给数字添加下划线。如1_000_000表示100w，java编译器会直接删除它们。

Double.NaN表示“非数值”。所有的“非数值”都被认为彼此不同。因此，不能使用if(x == Double.NaN)
检查x是否为NaN。可以使用Double.isNaN(x)检查“非数值”，Double.isInfinite测试是否为正负无穷大，
使用Double.isFinite检查浮点数既不是无穷也不是NaN。

Math类可以提供一些让整数算术运算更安全的方法。普通的计算溢出，数学操作符会默默返回错误结果。
Math.incrementExact等方法，在溢出时会抛出异常。

BigDecimal.valueOf(n, e)返回BigDecimal实例，值为n*10^-e

---

### break和continue使用标签

break声明只能跳出直接封闭的循环或者switch。如果想要跳到另外一个封闭声明的结束，则使用带有标签的break声明。
在应该退出的声明加上标签。标签可以是任何名称。
```
outer:
while(...) {
    while(...) {
    if(...) break outer;
    }
}
```
continue声明也可以带标签，可以跳转到标签循环的下一个迭代。

---

### 访问

Accessor（访问器）和Mutator（更改器）方法：如果一个方法改变了调用它的对象，则为更改器方法；
否则，则为访问器方法。**修改可能会有风险，特别是在同一个对象上同时计算时**。解决这种问题的一种方式，
就是只提供访问器方法，使得对象都是不可变的。

Java中，变量只能持有对象的引用。实际的对象在其他地方，引用时与实现相关的一种定位对象的方式。
即，Java中，所有参数——对象引用以及基本类型值——都是值传递。

---

### 类中的构造函数和静态变量与静态初始块

系统会自动给没有构造函数的类指定一个什么也不做的无参构造函数。但是如果一个类已经有一个构造函数，则系统不会再自动给它一个无参的构造函数。

static静态变量，属于类的，而不是对象，每个类只有一个。相比之下，每个对象都有一个实例变量的拷贝。

静态初始化块。当类第一次加载时，执行静态初始化。所有的静态变量初始化和静态初始化块以它们在类声明中出现的顺序执行。
```
public class ClassA{

    static{
    ···
    }

}
```

区分两个构造方法的唯一方法是它们的参数类型，因此，如果两个不同的构造函数具有相同的参数类型，无法区分。
这时，我们可以使用工厂方法。此外，工厂方法能返回共享对象，无须每次构建一个新的对象。

---

### JAR文件简介

可以将class文件放到一个或多个被称为JAR文件的归档文件中，使用jar工具制作归档文件，jar工具是JDK的一部分。
```
jar cvf library.jar com/mycompany/*.class
```
同时，可以使用JAR文件将程序打包，而不仅仅是类库。
```
jar cvfe program.jar com.mycompany.MainClass com/mycompany/*.class
// com.mycompany.MainClass为运行JAR文件时调用的类
// 然后这样运行程序
java -jar program.jar
```
当要使用JAR文件时，需要指定class path告诉编译器和JVM这些JAR文件在哪里。
javac和java命令都有-classpath选项。

---

### java中文件的导入

一个.java源文件可以包含多个类，但是最多只有一个可以声明为public。如果一个源文件有个public类，
那么文件名和类名必须匹配。

当import导入多个包时，可能有名称冲突（import java.util.*与import java.sql.*）。
这是需要指定类的全限定名。

静态导入
```
import static java.lang.Math.*
```
此时，无须类名称作为前缀，就可以使用Math类的静态方法和静态变量。
你也可以导入具体的静态方法或变量。
```
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
```
你不能从默认包中的类导入静态方法或域。

---

### 嵌套类

我们可以将一个类放在另外一个类的内部，这种类被成为嵌套类。嵌套类分为两种，静态嵌套类和内部类，
区别是这两个类是否被static修饰。

静态嵌套类，由static修饰。不需要外部类的实例即可访问到类内部的静态嵌套类，即通过OuterClass.InnerClass。
当嵌套类不需要知道它属于哪个实例时，使用静态嵌套类。

内部类，不由static修饰。内部类的方法而已访问它外部类的实例变量。每个内部类对象都有自己外部类对象的引用，
可以用OuterClass.this表示。内部类可以通过它的外部类实例调用外部类的方法。除了编译时常量以外，
内部类不能声明静态成员变量。（静态嵌套类可以）

---

### 接口

接口的所有方法默认为公有方法，定义在接口中的任何变量自动为public static final。
当子类型T的任何值不需要转换就能赋值给父类型S的变量时，类型S是类型T（子类）的父类。
从父类型转换为子类，需要使用强制类型转换（cast）。你只能将一个对象强制转换为它的实际类或它的父类之一。
否则会发生编译时错误或者强制类型转换异常。可以使用instanceof操作符先测试对象是否是期望的类型。

java8起接口支持静态方法和默认方法。我们可以给接口的任意方法提供默认实现，必须给这样的方法加上default修饰符标签。
默认方法的一个重要用途是接口演化。
如果一个类实现了两个接口，两个接口中存在名称和参数类型相同的方法，如果有接口为共享方法提供了默认实现，
则会产生冲突。我们需要通过指定想要哪个父类型等方式解决冲突。（如果没有任何接口为共享方法提供默认实现，就没有冲突）
如果一个类继承了一个父类且实现了一个接口，且从两者都继承了同样的方法，此时会忽略来自接口的默认方法，只关心父类的方法。

在Java中，方法能够访问它自己所在类对象的任何私有特性。
```
public class A implements Comparable<A> {
    private double x;
    ···
    public int compareTo(A other) {
      // compare方法访问other.x是完全合法的，因为它在类A的方法中，other是类A的一个实例。
        return Double.compare(x, other.x);
    }
}
```

诸如Arrays.sort、Collections.sort方法，要么输入的对象实现了Comparable接口，此时会按照对象实现的compareTo方法进行比较；
或者输入对象以及对象的比较器，比较器为一个实现了Comparator接口的对象实例，会按照该比较器的compare方法进行比较。

---

### 函数式编程

函数式接口是一个有且仅有一个抽象方法，但是可以有多个非抽象方法（静态或默认方法）的接口。
函数式接口可以被隐式转换为lambda表达式。可以用@FunctionalInterface注释定义。
用@FunctionInterface注释标记函数式接口有两个好处：
1. 编译器会检查出被注解实体是一个带有单个抽象方法的接口。
2. javadoc页面会包含该接口是函数式接口的声明。

方法引用，操作符::把方法名称与类或对象名称分隔开 。有以下三种使用方式：
1. 类::实例方法
2. 类::静态方法
3. 对象::实例方法

第一种使用方式，第一个参数变成方法的接收者，并且其他参数也传递给该方法。如String::compareToIgnoreCase
等同于(x, y) -> x.compareToIgnoreCase(y)。

第二种使用方式，所有参数传递给静态方法。如Objects::isNull等同于x -> Objects.isNull(x)。

第三种使用方式，再给定的对象上调用方法，并且参数传递给实例方法。如System.out::println
等同于x -> System.out.println(x)。

当有多个同名的重载方法时，编译器会试图从上下文中找匹配的那个。
你可以在方法引用中捕获this参数。如this::equals。

另外，我们可以使用构造函数引用，如ClassName::new。

lambda表达式的方法体与嵌套代码块有着相同的作用域。因此，也适用同样的命名冲突和屏蔽规则。
“相同作用域”的另一个结果式是，lambda表达式中的关键字this代表的是创建lambda表达式的方法的this参数。

lambda表达式有三个部分：
1. 代码块。
2. 参数。
3. 自由变量的值——自由变量是指，既不是参数变量，也不是代码内部定义的变量。

描述带有自由变量值的代码块的技术名称是闭包，lambda表达式就是闭包。lambda表达式会捕获自由变量的值。
为了确保被捕获的值是被良好定义的，我们要求lambda表达式中只能引用那些值不变的变量。
即，lambda表达式只能访问来自闭合作用域的final局部变量。（同样的规则适用于被局部内部类捕获的变量）
增强的for循环（foreach）中的变量是有效final的，因为它的作用域是单个迭代。

我们可以让方法的参数和返回值都是函数，处理和返回函数的函数被称为高阶函数。

我们可以在方法内定义一个类，这个类被成为局部类。局部类没有声明为public或private，因为对方法外部而言它永远是不可访问的。
创建局部类的两个好处：一、类名称隐藏在方法范围内；二、局部类的方法可以访问来自闭合作用域的变量（同lambda表达式）。

我们可以用匿名类来简化创建内部类，表达式
```
new intrface() {methods}
```
当你需要提供两个或者多个方法时，匿名类才是必须的。如果仅一个方法，可以使用lambda表达式。

---

### 类的继承

方法重写（@Override） 时，必须匹配准确的参数类型，不过可以将返回类型改成子类型（协变返回类型是允许的），
另外，重写时，子类方法的可见性至少与父类一样。

子类的构造函数中，关键字super表示调用父类的构造函数，但是父类的构造函数必须是子类的构造函数中的第一条语句 。

将一个子类赋给一个父类是合法的，而在父类变量上调用一个方法时，虚拟机会查看对象的实际类型，并且定位方法的版本，
即会调用相应子类的方法。这个过程称为动态方法查找。但是我们无法在父类变量上调用子类的方法，
我们需要使用instanceof操作符让父类引用转化为子类引用。

当方法声明为final时，子类不能重写它。当类被声明为final时，不能被继承。

我们可以拥有继承父类的匿名子类。
```
ArrayList<String> array = new ArrayList<>(100) {
    public void add(int index, String element) {
        super.add(index, element);
        System.out.printf("Add %s at %d\n", element, index);
    }
}
```
双括号初始化，外面大括号创建匿名子类，里面的大括号是初始代码块。（不建议使用）
```
new ArrayList<String>(){{add("AAA");    add("BBB")}}
```

类比接口优先，父类的实现总是赢过接口的实现。

---

### Object类

Object的toString方法默认返回类名称和hash码。无论何时，当一个对象与一个字符串连接时，
Java编译器自动调用该对象的toString方法。
打印数组可以调用Arrays.toString（array）方法。多维数组则为Arrays.deepToString。

equals方法的常规步骤：
1. 一般认为两个相等的对象是完全相同的，而且这个检测的耗费很小。
2. 当与null比较时，所有的equals方法都要返回false。
3. 由于重载了Object类的equals方法，而其参数为Object类型，因此我们需要将其转换为实际的类型，
这样你才能查看它的实例变量。在转换之前，先使用getClass方法，或instanceof操作进行类型检查。
（为了equals的对称性，建议使用getClass）
4. 最后，比较实例变量。对于基本类型使用==操作符，对于对象，使用Objects.equals，一个null安全的方法。

如果是数组类型的实例变量，使用静态方法Array.equals检查。

hashCode和equals方法必须是兼容的：如果x.equals(y)，则x.hashCode() == y.hashCode()。
因此，如果你重写了equals方法，则必须重写hashCode方法。

protected void finalize()方法，当垃圾回收器回收该对象时，此方法会被调用，不能覆盖它。

protected Object clone()方法，拷贝对象。方法为protected，如果想让类克隆，必须覆盖它。
Object.clone是浅拷贝，简单地从原对象中拷贝所有实例变量到被拷贝对象中，如果是引用，则只会拷贝引用。
当我们要拷贝对象时，类必须实现Cloneable接口。这是一个没有任何方法的接口，称为标签接口。
Object.clone方法执行浅拷贝时，会检查这个接口是否被实现，没有则会抛出cloneNotSupportedException异常。
最后，还需要处理CloneNotSupportedException异常，要么声明它，要么捕获它。

---

### 枚举类

```
public enum Size {SMALL, MEDIUM, LARGE};
```
每个枚举类型都有固定的实例集，可以直接用==来比较它们。也不需要提供toString方法，它会自动产生枚举对象的名称。
静态方法valueOf，针对枚举类型而合成，如`Size.valueOf("SMALL")`，如果给定的名称不存在，会抛出异常。
另外每个枚举类型都有一个静态方法values，返回一个按照其声明次序排列的包含所有枚举实例的数组。
ordinal方法返回实例在枚举声明中的位置，每个枚举类型E继承了Enum<E>,自动实现Comparable<E>，
仅允许比较它自己的对象，且比较是基于序数值的。

我们可以给枚举类型添加构造函数、方法和域。枚举类型的构造方法是私有的，每个枚举实例保证只被构造一次。
我们也可以给单个enum实例添加方法，但是要覆盖枚举类中定义的方法。
```
public enum Operation {
    ADD {
        public int eval(int arg1, int arg2) {return arg1 + arg2;}
    },
    SUBTRACT {
        public int eval(int arg1, int arg2) {return arg1 - arg2;}
    };
    
    public abstract int eval(int arg1, int arg2);
}
```
枚举类可以拥有静态成员，但是要小心构造次序。由于枚举常量在静态成员变量之前构建，
所以不能在构造函数里引用任何静态成员。解决方法是在一个静态初始化块中进行初始化工作。

---

### Class类

java中使用Class类来描述某个对象的具体信息。`Class<?> class = obj.getClass()`。
可以使用Class.forName("className")方法，使用反射机制，构造一个可能在编译时还不被知晓的类的Class对象，
当发生错误时会抛出检查异常ClassNotFoundException。

我们可以调用java.lang.Class<T>类中的方法，获取该类的父类、被实现接口、包名、域和方法等信息，
也能构建相应的实例，具体参看该类。

Class类的一个有用服务就是定位应用程序可能需要的资源。
```
InputStream stream = MyClass.class.getRescourceAsStream("config.txt");
Scanner in = new Scanner(stream);
```

### 类加载器

类加载器负责加载字节流，并且在JVM中将它们转化为一个类或者接口。虚拟机根据需要加载类文件，从类的main方法开始。

当执行Java程序时，至少涉及三个类加载器。
1. bootstrap类加载器会加载Java类库（一般来自jre/lib/rt.jar文件），它是虚拟机的一部分。
（没有与bootstrap类加载器对应的ClassLoader对象，使用getClassLoader方法会返回null）
2. 扩展类加载器从jre/lib/ext目录中加载”标准库扩展“部分。
3. 系统类加载器加载应用程序类，它定位classpath中目录和JAR文件的类。

扩展类和系统类加载器都是在Java中实现的 ，都是URLClassLoader类的实例。

通过创建自己的URLClassLoader实例，可以从classpath以外的目录或者JAR文件中加载类。这常用来加载插件。
```
URL[] urls = {
    new URL("file:///path/to/directory");
    new URL("file:///path/to/jarfile.jar");
}
String className = "com.mycompany.plugins.Entry";
try (URLClassLoader loader = new URLClassLoader(urls)) {
    // 其中的第二个参数ture确保类的静态初始化块发生在加载之后
    // 不要使用ClassLoader.loadeClass方法，它不会执行静态初始化代码块
    Class<?> cl = Class.forName(className, true, loader);
}
```
URLCLassLoader从文件系统中加载类。如果想要从其他地方加载类，需要编写自己的类加载器。
继承ClassLoader类，实现方法findClass。

//TODO
**上下文加载器和服务加载器看不太懂。**

---

### 反射

反射机制允许程序在运行时检查任意对象的内容，并调用它们的任意方法。这个功能对实现一些工具是有用的。

java.lang.reflect包中包含三个类，Field类、Method类和Constructor类，分别描述一个类的域、方法、构造函数。
都具有一个getName方法，返回成员名称。Field类有getType方法，返回一个Class类型的对象，描述域的类型信息。
Method和Constructor类有可以输出参数的类型信息的方法，并且Method类还拥有可以输出返回值类型信息的方法。
这三个类都有一个getModifiers方法，表示所使用的修饰符。Method和Constructor的共同父类Executable类的getParameters方法
返回一个描述方法参数信息的参数对象数组。

```
// 枚举一个对象的所有域内容
Object obj = ···;
for (Field f : obj.getClass().getDeclaredFields()) {
    f.setAccessible(true);
    Object value = f.get(obj);
    System.out.println(f.getName() + ":" + value);
}
```
在使用私有的Field和Method对象之前，必须让它们是可访问的。默认JVM在没有安全管理器的情况下运行，
并且setAccessible方法”解锁“了域。但是，安全管理器可以阻塞请求，并以这种方式保护对象不被访问。
```
// 调用一个对象的给定方法
Method m = ...;
// obj为调用该方法的实例对象，如果为静态方法，则为null
Object result = m.invoke(obj, arg1, arg2, ...);
```

要使用无参构造函数构造对象，可以直接调用Class对象的newInstance方法。
要调用其他构造函数，首先需要找到Constructor对象，然后调用它的newInstance方法。

内省：要求类为JavaBeans，属性与getter/setter方法对应。具体可以参考网上的一些资料。
```
// 该类为一个JavaBeans
Class<?> cl = ...;
BeanInfo info = Interospector.getBeanInfo(cl);
PropertyDecriptor[] props = info.getPropertyDescriptors();

String propertyName = ...;
Object propertyValue = null;
for (PropertyDescriptor prop : props) {
    if (prop.getName().equals(propertyName)) 
        propertyValue = prop.getReadMethod().invoke(obj);
}
```

//TODO
**代理部分，也看不懂**

---

### 异常处理

Java中，所有异常都派生自Throwable类。Throwable类分为Error和Exception两种异常。
Error子类异常不是期望应用程序处理的，比如内存耗尽，这种情况下我们能做的事情不多。
Exception类分两种：
1. 未检查异常（unchecked exception），属于RuntimeException的子类。
未检查异常表明程序员造成的逻辑错误，不是不可避免的外部风险导致的。
2. 已检查异常（checked exception）。已检查异常用于错误可被提前预知的情况。
要么被捕获处理，要么在方法头通过throws声明。

当覆盖一个方法时，它不能抛出比父类方法声明中还要多的已检查异常。覆盖后的方法可以抛出比父类少的异常。
如果父类方法没有throws语句，则覆盖后的方法不能抛出已检查异常。我们不能指定lambda表达式的异常类型。
但是，如果lambda表达式会抛出一个已检查异常，则只能把它传给一个其方法声明了该异常的函数式接口。
```
// 一个错误的例子
// write方法为一个会抛出IOException的方法
list.forEach(obj -> write(obj, "output.dat"));

forEach方法的参数是函数式接口：
public interface Comsumer<T> {
    void accept(T t);
}
accept方法被声明为不抛出任何已检查异常
```

异常捕获
```
try {
    statements
} catch (ExceptionClass1 ex) {
    handler1
} catch (ExceptionClass2 ex) {
    handler2
} catch (ExceptionClass3 ex) {
    handler3
}
// catch语句从上到下匹配，所以最具体的异常类型必须放在前面

try {
    statement
} catch (ExceptionClass1 | ExceptionClass2 | ExceptionClass3 ex) {
    handler
}
// 这种情况下，处理器只能在异常变量上调用所有异常类共有的方法。
```

我们可以使用try-with-resources语句解决因异常而导致文件无法正常关闭的问题。
另外，try-with-resources语句可以可选的加上catch子句，以捕获任何异常。
```
try (ResourceType1 res1 = init1; ResourceType2 res2 = init2; ...) {
    statements
} 
```
其中，每个资源必须属于一个实现了AutoCloseable接口的类 。AutoCloseable接口有一个单独的方法。
`public void close() throws Exception`。还有一个Closeable接口，是AutoCloseable的子接口，
同样也只有一个close方法，但是它抛出的是IOException异常。

当tyr块退出时，只有两种情况：要么是因为正常执行到它的终点，要么是因为抛出了一个异常。
此时，所有资源对象的close方法被触发。资源按照初始化的相反顺序关闭。

某些close方法也会抛出异常。此时有两种情况：
1. try块正常执行完，那么该异常将会抛给调用者；
2. try块的执行中抛出了异常，此时这些资源的close方法被调用，并且它们中的其中一个又抛出了一个异常，
则 此异常很可能比原先那个try块中的异常的重要性要小一些。
这种情况下，原先的异常被重新抛出，来自调用close的异常被捕获，并作为一个”被抑制的（suppressed）“异常附加到原先的异常上。
当你捕获了主要异常时，可以调用getSuppressed方法检索得到第二个异常。
如果你想要自己实现这种机制，则调用`ex.addSuppressed(secondaryException)`方法。

finally子句：当try块到达末尾时，不管是正常完成还是异常导致的，都会执行finally子句。
需要避免在finally子句中抛出异常。如果try块因为一个异常而中断，执行finally子句中的代码，
结果finally子句中也抛出了异常，则try块中抛出的异常会被finally子句中抛出的异常所掩盖。
之前提到的异常抑制机制仅适用于try-with-resources语句。

同样，finally子句中不应该包含return语句。如果try块中也有一个return语句，那么try中的返回值会被finally子句中的返回值替换。
（具体可以看看网上的各类情况分析）

异常链技术
```
try {
    statements
} catch (ExceptionClass1 ex) {
    throw new ExceptionClass2("detail for Exception", ex);
}
// 当捕获到一个异常时，原始的异常可以按照以下方式检索
Throwable cause = ex.getCause();
```
如果一个不允许抛出已检查异常的方法中发生了已检查异常，也可以使用异常链技术，捕获这个已检查异常，任何将它链到一个未检查异常中。

Objects.requireNonNull方法可以检查一个参数是否为null，如果是，则会抛出一个NullPointerException异常。
也可以为异常提供一个消息字符串：`directions = Objects.requireNonNull(directions, "directions must not be null")`

























