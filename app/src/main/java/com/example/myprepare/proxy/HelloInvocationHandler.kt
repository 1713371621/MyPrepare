package com.example.myprepare.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class HelloInvocationHandler constructor(private val any: Any) : InvocationHandler {

  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
    val result: Any? = method?.invoke(any, args)
    return result!!
  }
}

fun main() {
  val helloInterface: HelloInterface = HelloImpl()

  val handler: InvocationHandler = HelloInvocationHandler(helloInterface)

  val proxyHello: HelloInterface = Proxy.newProxyInstance(
      helloInterface.javaClass.classLoader,
      helloInterface.javaClass.interfaces,
      handler
  ) as HelloInterface

  proxyHello.doSomething()
  proxyHello.myDeviceNotFound()
}