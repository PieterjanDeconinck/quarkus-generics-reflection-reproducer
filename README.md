# Reproducer for generic type reflection in Quarkus with native image

This repo contains 2 packages:
* vanilla: standard extended class -> this works in JVM & native mode
* generics: extended class using generic type -> this only works in JVM mode

Command used to build & test:
```
./mvnw clean verify -Pnative -Dquarkus.native.container-build=true
```

Error when running:
```
2020-04-14 20:47:49,790 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (executor-thread-1) HTTP Request to /generics failed, error id: 45ba49e0-d0ec-4b0e-9278-5409820b7bed-1: org.jboss.resteasy.spi.UnhandledException: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.test.generics.MyData and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: org.test.generics.ExtendedClass["data"])
	at org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:381)
	at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:216)
	at org.jboss.resteasy.core.SynchronousDispatcher.writeResponse(SynchronousDispatcher.java:610)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:520)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:259)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:160)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:362)
	at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:163)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:245)
	at io.quarkus.resteasy.runtime.standalone.RequestDispatcher.service(RequestDispatcher.java:73)
	at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.dispatch(VertxRequestHandler.java:122)
	at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.access$000(VertxRequestHandler.java:36)
	at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler$1.run(VertxRequestHandler.java:87)
	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2027)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1551)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1442)
	at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
	at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
	at java.lang.Thread.run(Thread.java:834)
	at org.jboss.threads.JBossThread.run(JBossThread.java:479)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:497)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.test.generics.MyData and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: org.test.generics.ExtendedClass["data"])
	at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1191)
	at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:404)
	at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:71)
	at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:33)
	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:727)
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:722)
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:166)
	at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
	at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
	at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1433)
	at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:921)
	at org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider.writeTo(ResteasyJackson2Provider.java:329)
	at org.jboss.resteasy.core.messagebody.AsyncBufferedMessageBodyWriter.asyncWriteTo(AsyncBufferedMessageBodyWriter.java:24)
	at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.writeTo(ServerWriterInterceptorContext.java:87)
	at org.jboss.resteasy.core.interception.jaxrs.AbstractWriterInterceptorContext.asyncProceed(AbstractWriterInterceptorContext.java:203)
	at org.jboss.resteasy.core.interception.jaxrs.AbstractWriterInterceptorContext.getStarted(AbstractWriterInterceptorContext.java:166)
	at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.lambda$getStarted$0(ServerWriterInterceptorContext.java:73)
	at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.aroundWriteTo(ServerWriterInterceptorContext.java:93)
	at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.getStarted(ServerWriterInterceptorContext.java:73)
	at org.jboss.resteasy.core.ServerResponseWriter.lambda$writeNomapResponse$3(ServerResponseWriter.java:162)
	at org.jboss.resteasy.core.interception.jaxrs.ContainerResponseContextImpl.filter(ContainerResponseContextImpl.java:403)
	at org.jboss.resteasy.core.ServerResponseWriter.executeFilters(ServerResponseWriter.java:251)
	at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:101)
	at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:74)
	at org.jboss.resteasy.core.SynchronousDispatcher.writeResponse(SynchronousDispatcher.java:590)
	... 20 more

[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 2.534 s <<< FAILURE! - in org.test.generics.NativeExampleResourceIT
[ERROR] testExtendedEndpoint  Time elapsed: 2.229 s  <<< FAILURE!
java.lang.AssertionError: 
1 expectation failed.
Expected status code <200> but was <500>.
```
