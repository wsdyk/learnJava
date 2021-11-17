## 对不同GC的总结


###Serial GC
####语法：
`
-Xmx512m -Xms512m -PrintGCDetails -XX:+UseSerialGC
`

串行GC共计回收16次，其中年轻代回收9次，FullGC回收7次，平均回收时长38.1ms,FullGC
最长用时70ms，最短用时30ms

####特点：

用单线程处理所有垃圾回收工作，会有较长的STW,适合几百MB堆内存，单核芯CPU，
要么是不工作，要么是全部线程都用于GC

----

###Parallel GC
####语法：
`
-Xmx512m -Xms512m -PrintGCDetails -XX:+UseParallelGC
`

并行GC共计回收32次，其中年轻代回收23次，FullGC回收9次，平均回收时长18.7ms,FullGC
最长用时60ms，最短用时30ms

####特点：
会导致STW,适合多核芯使用，需要GC的时候多核芯一起工作，不需要GC的时候不占用系统资源，
多核可提高吞吐能力，降低STW时间

----
###CMS GC
####语法：
`
-Xmx512m -Xms512m -PrintGCDetails -XX:+UseConcMarkSweepGC
`

并行GC共计回收18次，其中年轻代回收15次，FullGC回收5次。年轻代平均回收时长27.33ms,
年轻代GC最短用时0ms,最长用时90ms。FullGC平均用时50ms, 最长用时70ms，最短用时40ms

####特点：

会导致STW,mark-and-sweep(标记-清除)阶段的大部分工作和应用线程一起执行，默认GC并发数占CPU核芯
的1/4，GC暂停时间短，老年代存在碎片化问题，特定情况下GC存在不可预测的暂停时间

----
###G1 GC
####语法：
`
-Xmx512m -Xms512m -PrintGCDetails -XX:+UseG1GC
`

并行GC共计回收18次，其中年轻代回收15次，FullGC回收5次。年轻代平均回收时长27.33ms,
年轻代GC最短用时0ms,最长用时90ms。FullGC平均用时50ms, 最长用时70ms，最短用时40ms

####特点：

会导致STW,堆不再分为年轻代和老年代，而是分成2048（通常）个可存放对象的堆空间，增量方式GC，每次回收
一小块堆内存，GC时间短，垃圾最多堆小块会被优先回收，每次GC会回收所有年轻代堆内存块，
但只包含部分老年代内存块

----




