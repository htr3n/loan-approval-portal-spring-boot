# NOTES

## Technical Notes

* Apache CXF WSDL2Java ~3.2.4 (3.2.5+ complains about empty `wsdlLocation`)
* Java 8 `java.time.LocalDate` and JPA's `AttributeConverter`
* Shared data access configuration (due to Apache CXF servlet creating a separate context and therefore, cannot have access to Spring managed beans)
* `LEFT JOIN FETCH` for resolving some LAZY loading cases
* Explicit JAXB adapters, including Java 8 `java.time.LocalDate`
* Separate environments for different types of tests, e.g. data, web, etc.

## TODO

### Access checking for every controller? 

    * https://stackoverflow.com/questions/12371770/spring-mvc-checking-if-user-is-already-logged-in-via-spring-security 
    * https://medium.com/@trevormydata/week-3-spring-security-under-the-hood-from-a-spring-boot-mvc-point-of-view-c5497060d11d
    * https://spring.io/projects/spring-security

## Debugging

### $ODE/WEB-INF/conf/ode-axis2.properties

```properties
ode-axis2.event.listeners=org.apache.ode.bpel.common.evt.DebugBpelEventListener
```

* https://stackoverflow.com/questions/47558879/how-to-log-soap-messages-of-bpel-process-in-apache-ode

* WEB-INF/classes/log4j2.xml

```xml
    <Loggers>
        <Logger name="org.apache.ode" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <!--
        <Logger name="org.apache.ode.sql" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.ode.scheduler" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        -->
        <Logger name="org.apache.ode.bpel" level="debug" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.ode.bpel.engine.cron" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.ode.utils.WatchDog" level="warn" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.ode.il.DynamicService" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.axis2" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.hibernate" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.apache.openjpa" level="info" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="org.springframework" level="error" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="httpclient.wire.content" level="debug" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Logger name="httpclient.wire.header" level="debug" additivity="false">
            <AppenderRef ref="FILE"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Async"/>
        </Root>
    </Loggers>
```

