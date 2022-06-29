# KspLearn

> 有一说一，ksp的学习资料是真的少... 官方文档也及其不完善（也许是我没找到真正的文档？）
> 
> 掘金上只有两篇ksp的文章，他们写的东西都或多或少有问题，踩了不少坑

实现了一个`@IntSummable`注解，被这个注解修饰的`data class`会生成一个`sum`方法，用于所有整型属性的求和。

以下代码

~~~kotlin
@IntSummable
data class TestClass(
    val num1: Int,
    val num2: Int,
    val num3: Int
)
~~~

在执行`gradle kspKotlin`之后（该操作在编译前会执行）

ksp将会在`build/generated/ksp/main/kotlin`目录下生成如下代码

~~~kotlin
public fun TestClass.sumInts(): Int {
  val sum = num1 + num2 + num3
  return sum
}
~~~

## Note

### 提供Provider路径

在编写ksp代码时，我们需要提供一个实现了`SymbolProcessorProvider`的类, 
并且在`resources/META-INF/com.google.devtools.ksp.processing.SymbolProcessorProvider`文件中添加其路径
以便ksp能够找到它。(这个掘金上的文章都没说，结果我最开始写的时候ksp不生成代码，后来翻了bennyHuo的代码才发现)

### 让IDE能够识别ksp生成的代码

这里以idea为例, 使用idea的gradle插件，将ksp生成代码的路径加入sourceSrc中，
同时也加到编译路径中

~~~kts
plugins {
    idea
}

idea {
    module {
        // Not using += due to https://github.com/gradle/gradle/issues/8749
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin") // or tasks["kspKotlin"].destination
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs = generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}

sourceSets {
    main {
        java {
            srcDirs += file("build/generated/ksp/main/kotlin")
        }
    }
}
~~~

### Processor的process方法返回的list一旦不为空将重复执行

这一点掘金的文章也没讲，照抄他的代码都有问题，有点无语

## What is Ksp?

> Kapt的替代品，可以实现且不止实现注解处理器的功能
>
> 可以实现像类似lombok或compose的`@Compose`的一些功能

## Why Ksp?

> 我想bennyHuo已经讲得很详细了，相较于Kapt，Ksp具有更快的速度（少一个生成`java stub`的过程），更好的kotlin兼容性。

[Kotlin 元编程：从注解处理器(KAPT)到符号处理器(KSP) - 霍丙乾](https://www.bilibili.com/video/BV1wg411A7Lo)
