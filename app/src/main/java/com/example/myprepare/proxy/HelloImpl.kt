package com.example.myprepare.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class HelloImpl : HelloInterface {

//  val invocationHandler: InvocationHandler = object : InvocationHandler {
//    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
//      val result: Any? = method?.invoke(this@HelloImpl, args)
//      return result!!
//    }
//
//  }

  override fun doSomething(operation: String, name: String) {
//    val method: Method = HelloImpl::class.java.getDeclaredMethod("doSomething", String::class.java)
//    invocationHandler.invoke(this, method, arrayOf(operation, name))
  }

  override fun myDeviceNotFound() {

  }
}