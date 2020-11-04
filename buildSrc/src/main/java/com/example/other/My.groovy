//package com.example.other
//package com.example.plugin
//
//import com.android.build.gradle.BaseExtension
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//
//class My implements Plugin<Project> {
//
//  @Override
//  void apply(Project target) {
//    def extension = target.extensions.create('my', MyExtension)
//    target.afterEvaluate {
//      println("age = ${extension.age}, name = ${extension.name}")
//    }
//
//    def transform = new MyTransform()
//    def baseExtension = target.extensions.getByType(BaseExtension)
//    baseExtension.registerTransform(transform)
//  }
//}