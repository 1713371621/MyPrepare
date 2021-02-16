package com.example.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
//import com.android.utils.FileUtils

class MyTransform extends Transform {

  @Override
  String getName() {
    return "tmufuijwyk"
  }

  @Override
  Set<QualifiedContent.ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS
  }

  @Override
  Set<? super QualifiedContent.Scope> getScopes() {
    return TransformManager.SCOPE_FULL_PROJECT
  }

  @Override
  boolean isIncremental() {
    return false
  }

  @Override
  void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
    def inputs = transformInvocation.inputs
    def outputProvider = transformInvocation.outputProvider

    inputs.each {
      it.jarInputs.each {
        File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.JAR)
        println("jar: file = \"${it.file}\", dest = \"${dest.getPath()}\"")
//        FileUtils.copyFile(it.file, dest)
      }

      it.directoryInputs.each {
        File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
        println("directory: file = \"${it.file}\", dest = \"${dest.getPath()}\"")
//        FileUtils.copyDirectory(it.file, dest)
      }
    }

  }
}