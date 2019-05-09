
####### springboot logback 配置

[参考博客 	logback节点配置详解](https://blog.csdn.net/yamaxifeng_132/article/details/86702875)


```
logback节点配置详解
一 ：根节点  <configuration></configuration>

属性 ：

debug : 默认为false ，设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。

scan : 配置文件如果发生改变，将会重新加载，默认值为true。

scanPeriod : 检测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位时毫秒，当scan为true时，这个属性生效，默认时间间隔为1min。

    <?xml version="1.0" encoding="utf-8"?>
    <configuration debug="true" scan="true" scanPeriod="2">
        <!--TODO : 子节点信息-->
    </configuration>

可以这样描述配置文件的基本结构：以<configuration>开头，后面有零个或多个<appender>元素，有零个或多个<logger>元素，有最多一个<root>元素。

二：<configuration> 子节点

    <appender></appender>

    <logger></logger>

    <root></root>

1.1 <appender>  是  <configuration> 的子节点，是负责写日志的组件。

appender 有两个必要属性 name，class 。name指定appender 的名称， class 指定appender的全限定名。

class 包括 ：ch.qos.logback.core.ConsoleAppender / ch.qos.logback.core.FileAppender / ch.qos.logback.core.RollingFileAppender

    <?xml version="1.0" encoding="utf-8"?>
    <configuration debug="true" scan="true" scanPeriod="2">
        <!-- conf console out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender"></appender>

        <!-- conf file out -->
        <appender name="file_out" class="ch.qos.logback.core.FileAppender"></appender>

        <!-- conf file out -->
        <appender name="file_out" class="ch.qos.logback.core.RollingFileAppender"></appender>

        <root></root>
        <loger></loger>
    </configuration>

1.1.1 ：ConsoleAppender

把日志添加到控制台，有如下节点：

<encoder> : 对日志进行格式化。

<target> : 字符串System.out 或者 System.err, 默认 System.out;

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%date [%thread] %-5level %logger - %message%newline</pattern>
            </encoder>
        </appender>

        <root level="INFO">
            <appender-ref ref="console_out" />
        </root>
    </configuration>

1.1.2 : FileAppender

把日志添加到文件，有如下节点：

<file>         : 被写入的文件名,可以是相对目录 , 也可以是绝对目录 , 如果目录不存在则会自动创建

<append>  : 如果是true , 日志被追加到文件结尾 , 如果是false,清空现存文件 , 默认是true

<encoder> : 对日志进行格式化  [具体的转换符说明请参见官网.]

<prodent> : 如果是true，日志会被安全的写入文件 , 即使其他的FileAppender也会向此文件做写入操作 , 默认是false

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%date [%thread] %-5level %logger - %message%newline</pattern>
            </encoder>
        </appender>

        <!-- conf file out -->
        <appender name="file_out" class="ch.qos.logback.core.FileAppender">
            <file>logs/debug.log</file>
            <encoder>
                <pattern>%date [%thread] %-5level %logger - %message%newline</pattern>
            </encoder>
        </appender>
    </configuration>

1.1.3 : RollingFileAppender [常用]

滚动纪录文件，先将日志记录到指定文件，当符合某种条件时，将日志记录到其他文件，有如下节点：

<file> : 被写入的文件名，可以是相对目录，也可以解决目录，如果目录不存在则自动创建。

<append> : 如果是true，日志被追加到文件结尾，如果是false，清空现存文件，默认是true；

<encoder> : 对日志进行格式化  [具体的转换符说明请参见官网.]

<rollingPolicy> : 当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名。

1.1.3.1 ：

TimeBaseRollingPolicy ：最常用的滚动策略，根据时间来制定滚动策略，即负责滚动也负责触发滚动。有如下节点；

1) : <fileNamePattern>

必要节点，包含文件及“％d” 转换符，“％d”可以包含一个java.text.SimpleDateFormat 制定的时间格式，如：％d｛yyyy-MM｝,如果直接使用 ％d ，默认格式是 yyyy－MM－dd。

RollingFileAppender 的file 子节点可有可无，通过设置file，可以为活动文件和归档文件制定不同位置，当前日志总是纪录到file指定的文件，活动文件的名称不会改变，如果没有设置file，活动文件的名称会根据fileNamePattern的值，每隔一段时间改变一次，“／”或者“\” 会被当作目录分隔符。

2) : <maxHistory>

可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件，假设设置每个月滚动，且<maxHistory> 是 6，则只保存最近6个月的文件，删除之前的旧文件，注意：删除旧文件是哪些为了归档而创建的目录也会被删除。

3) : <filenamePattern>

必须包含“%i” 例如：设置最小值，和最大值分别为1和2，命名模式为 log%i.log,会产生归档文件log1.log和log2.log，还可以指定文件压缩选项，例如：log％i.log.gz 或者 log%i.log.zip

4) : <triggeringPolicy>

告知RollingFileAppender   激活RollingFileAppender滚动

    <!-- 03:conf errorAppender out -->
    <appender name="errorAppender" class="ch.qos.logback.core.RollingFileAppender">
        <file>logs/error.log</file>

        <!-- 设置滚动策略 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--设置日志命名模式-->
            <fileNamePattern>errorFile.%d{yyyy-MM-dd}.log</fileNamePattern>

            <!--最多保留30天log-->
            <maxHistory>30</maxHistory>
        </rollingPolicy>

        <!-- 超过150MB时，触发滚动策略 -->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <maxFileSize>150</maxFileSize>
        </triggeringPolicy>

        <encoder>
            <pattern>%d [%p] %-5level %logger - %msg%newline</pattern>
        </encoder>
    </appender>

1.1.3.2 ：

SizeBasedTriggeringPolicy : 查看当前活动文件的大小 , 如果超过指定大小会告知 RollingFileAppender , 触发当前活动滚动 , 只有一个节点 , 用来规定文件大小

<maxFileSize> : 活动文件的大小 , 默认10MB

<prudent>：当为true时 , 不支持FixedWindowRollingPolicy , 支持TimeBasedRollingPolicy , 但是有两个限制 , 1不支持也不允许文件压缩 , 2不能设置file属性 . 必须留空

至此 , Appender 节点已经介绍完毕.。
2.1 ：logger 节点

logger 是 <configuration> 的子节点

来设置某一个包或者具体的某一个类的日志打印级别，以及指定<appender>, logger 仅有一个name属性，两个可选属性 level／addtivity

name ： 用来指定受此loger约束的某一个包或者具体的某一个类

level ：用来设置打印级别，大小写无关，TRACE,DEBUG,INFO,WARE,ERROR,ALL和OFF,还有一个特俗值INHERITED 或者 同义词NULL，代表强制执行上级的级别。如果未设置此属性，那么当前logger将会继承上级的级别。

level 大小 ： ERROR > WARN > INFO > DEBUG > TRACE 程序会打印高于或等于所设置级别的日志

addtivity ： 是否向上级loger传递打印信息，默认为true；

<loger> 可以包含零个或多个<appender-ref>元素，表示这个appender将会添加到loger

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.LevelFilter">
                <!-- 过滤掉非INFO级别 -->
                <level>INFO</level>
                <onMatch>ACCEPT</onMatch>
                <onMismatch>DENY</onMismatch>
            </filter>
        </appender>

        <!--  conf infoAppender out -->
        <appender name="infoAppender" class="ch.qos.logback.core.RollingFileAppender">
            <file>logs/info.log</file>
            <!-- 设置滚动策略 -->
            <rollingPoliy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--设置日志命名模式-->
                <fileNamePattern>infoFile.%d{yyyy-MM-dd}.log</fileNamePattern>
                <!--最多保留30天log-->
                <maxHistory>30</maxHistory>
            </rollingPoliy>
            <!-- 超过150MB时，触发滚动策略 -->
            <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
                <maxFileSize>150</maxFileSize>
            </triggeringPolicy>
            <encoder>
                <pattern>%d [%p] %-5level %logger - %msg%newline</pattern>
            </encoder>
        </appender>

        <!-- 指定在logback.olf.log包中的log -->
        <logger name="logback.olf.log" level="info">
            <appender-ref ref = "console_out"/>
            <appender-ref ref = "infoAppender"/>
        </logger>
    </configuration>

2.2 : root 节点

元素配置根 logger。该元素有一个 level 属性。没有 name 属性，因为已经被命名 为“root”。

Level 属性的值大小写无关，其值为下面其中一个字符串：TRACE、DEBUG、INFO、 WARN、ERROR、ALL 和 OFF。注意不能设置为“INHERITED” 或“NULL”。 元素可以包含零个或多个元素。与元素类似，声明 元素后，会先关闭然后移除全部当前 appender，只引用声明了的 appender。如果 root 元素没 有引用任何 appender，就会失去所有 appender。

如下完整案例配置 :

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.LevelFilter">
                <!-- 过滤掉非INFO级别 -->
                <level>INFO</level>
                <onMatch>ACCEPT</onMatch>
                <onMismatch>DENY</onMismatch>
            </filter>
        </appender>

        <!-- 01:conf infoAppender out -->
        <appender name="infoAppender" class="ch.qos.logback.core.RollingFileAppender">
            <file>logs/info.log</file>
            <!-- 设置滚动策略 -->
            <rollingPoliy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--设置日志命名模式-->
                <fileNamePattern>infoFile.%d{yyyy-MM-dd}.log</fileNamePattern>
                <!--最多保留30天log-->
                <maxHistory>30</maxHistory>
            </rollingPoliy>
            <!-- 超过150MB时，触发滚动策略 -->
            <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
                <maxFileSize>150</maxFileSize>
            </triggeringPolicy>
            <encoder>
                <pattern>%d [%p] %-5level %logger - %msg%newline</pattern>
            </encoder>
        </appender>

        <!-- 02:conf debugAppender out -->
        <appender name="debugAppender" class="ch.qos.logback.core.RollingFileAppender">
             <file>logs/debug.log</file>
             <!-- 设置滚动策略 -->
             <rollingPoliy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                 <!--设置日志命名模式-->
                 <fileNamePattern>debugFile.%d{yyyy-MM-dd}.log</fileNamePattern>
                 <!--最多保留30天log-->
                 <maxHistory>30</maxHistory>
             </rollingPoliy>
             <!-- 超过150MB时，触发滚动策略 -->
             <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
                 <maxFileSize>150</maxFileSize>
             </triggeringPolicy>
             <encoder>
                 <pattern>%d [%p] %-5level %logger - %msg%newline</pattern>
             </encoder>
        </appender>

        <!-- 03:conf errorAppender out -->
        <appender name="errorAppender" class="ch.qos.logback.core.RollingFileAppender">
            <file>logs/error.log</file>
            <!-- 设置滚动策略 -->
            <rollingPoliy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--设置日志命名模式-->
                <fileNamePattern>errorFile.%d{yyyy-MM-dd}.log</fileNamePattern>
                <!--最多保留30天log-->
                <maxHistory>30</maxHistory>
            </rollingPoliy>
            <!-- 超过150MB时，触发滚动策略 -->
            <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
                <maxFileSize>150</maxFileSize>
            </triggeringPolicy>
            <encoder>
                <pattern>%d [%p] %-5level %logger - %msg%newline</pattern>
            </encoder>
        </appender>

        <root level="ALL">
            <appender-ref ref="infoAppender"/>
            <appender-ref ref="debugAppender"/>
            <appender-ref ref="errorAppender"/>
        </root>
    </configuration>

三 : <filter> 过滤节点

Logback 的过滤器基于三值逻辑（ternary logic），允许把它们组装或成链，从而组成任 意的复合过滤策略。

这里的所谓三值逻辑是说，过滤器的返回值只能是 ACCEPT、DENY 和 NEUTRAL 的其中一个。

过滤器一般分为如下几类 ：
3.1 : 级别过滤器（LevelFilter）

LevelFilter 根据记录级别对记录事件进行过滤。如果事件的级别等于配置的级别，过滤 器会根据 onMatch 和 onMismatch 属性接受或拒绝事件。下面是个配置文件例子:

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.LevelFilter">
                <!-- 过滤掉非INFO级别 -->
                <level>INFO</level>
                <onMatch>ACCEPT</onMatch>
                <onMismatch>DENY</onMismatch>
            </filter>

            <encoder>
                <pattern>%-4relative [%thread] %-5level %logger{30} - %msg%n</pattern>
            </encoder>
        </appender>

        <root level="DEBUG">
            <appender-ref ref="console_out" />
        </root>
    </configuration>

3.2 : 临界值过滤器（ThresholdFilter）

ThresholdFilter 过滤掉低于指定临界值的事件 . 当记录的级别等于或高于临界值时 , ThresholdFilter 的decide()方法会返回NEUTRAL ; 当记录级别低于临界值时 , 事件会被拒绝 下面是个配置文件例子 :

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
                <!-- 过滤掉TRACE和DEBUG级别的日志 -->
                <level>INFO</level>
            </filter>

            <encoder>
                <pattern>%-4relative [%thread] %-5level %logger{30} - %msg%n</pattern>
            </encoder>
        </appender>

        <root level="DEBUG">
            <appender-ref ref="console_out" />
        </root>
    </configuration>

3.3 : 求值过滤器（EvaluatorFilter）

EvaluatorFilter 封装了 EventEvaluator（ch.qos.logback.core.boolex.EventEvaluator) , 评估 是否符合指定的条件

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.EvaluatorFilter">
                <evaluator>
                    <!--过滤掉所有日志中不包含hello字符的日志-->
                    <expression>
                        message.contains("hello")
                    </expression>
                    <onMatch>NEUTRAL</onMatch>
                    <onMismatch>DENY</onMismatch>
                </evaluator>
            </filter>

            <encoder>
                <pattern>%-4relative [%thread] %-5level %logger{30} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="DEBUG">
            <appender-ref ref="console_out" />
        </root>
    </configuration>

3.4 : 匹配器（Matchers）

尽管能通过调用 String 类的 matches()方法进行模式匹配，但这会导致每次调用过滤器 时都会创建一个全新的 Pattern 对象。为消除这种开销，你可以预先定义一个或多个 Matcher 对象。一旦定义 matcher 后，就可以在求值表达式里重复引用它。

    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
        <!-- conf consoel out -->
        <appender name ="console_out" class="ch.qos.logback.core.ConsoleAppender">
            <filter class="ch.qos.logback.classic.filter.EvaluatorFilter">
                <evaluator>
                    <matcher>
                        <Name>odd</Name>
                        <!-- 过滤掉序号为奇数的语句-->
                        <regex>statement [13579]</regex>
                    </matcher>
                    <expression>odd.matches(formattedMessage)</expression>
                        <onMatch>NEUTRAL</onMatch>
                        <onMismatch>DENY</onMismatch>
                </evaluator>
            </filter>

            <encoder>
                <pattern>%-4relative [%thread] %-5level %logger{30} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="DEBUG">
            <appender-ref ref="console_out" />
        </root>
    </configuration>

logback 执行流程 :

1 : 获得过滤链的策略

依据过滤器链返回的结果做出不同的响应。共有三个响应结果：
FilterReply.DENY，直接退出，不执行后续流程
FilterReply.NEUTRA，继续向下执行
FilterReply.ACCEPT，不进行步骤二,即类型输出类型检查

2 : 执行基本的选择规则

主要是比较下level，如果级别低直接退出后续执行

3 : 创建LoggingEvent对象

这个对象包裹一些基本信息，包括日志界别，信息本身，可能的异常信息，执行时间，执行线程，其实一些随日志请求一起发出的数据和MDC。其中MDC是用来装一些额外的上下文信息的。

4 : 调用appenders

此时logback会调用appender的doAppender，如果appender里有一些filer的话，此时也会调用

5 : 格式化输出结果

通常情况下都是由layout层将event格式化成String型。当然也有意外比如说SocketAppender就是将event格式化成流。

6 : 输出LoggingEvent

将格式化好的结果，输出到appender中记录的地址

```



