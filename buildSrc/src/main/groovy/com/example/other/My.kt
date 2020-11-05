//package com.example.other
//
//import com.android.build.gradle.BaseExtension
//import com.example.plugin.com.example.plugin.MyExtension
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//
//class com.example.plugin.My : Plugin<Project> {
//
//  override fun apply(target: Project) {
//    val extension = target.extensions.create("my", com.example.plugin.MyExtension::class.java)
//    target.afterEvaluate {
//      println("age = ${extension.age}, name = ${extension.name}")
//    }
//
//    val transform = com.example.plugin.com.example.plugin.MyTransform()
//    val baseExtension = target . extensions . getByType (BaseExtension::class.java)
//    baseExtension.registerTransform(transform)
//  }
//}