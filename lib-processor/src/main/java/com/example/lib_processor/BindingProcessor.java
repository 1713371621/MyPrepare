package com.example.lib_processor;

import com.example.lib_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class BindingProcessor extends AbstractProcessor {

  Filer filer;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    filer = processingEnvironment.getFiler();
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    System.out.println("Annotation Processor run");

    // 测试代码
    /*ClassName className = ClassName.get("com.example.my", "Test" );
    TypeSpec typeSpec = TypeSpec.classBuilder(className).build();
    try {
      JavaFile.builder("com.example.my",typeSpec).build().writeTo(filer);
    } catch (IOException e) {
      e.printStackTrace();
    }*/

    /*ClassName className = ClassName.get("com.example.myprepare", "AnnotationTestActivityBinding");
    TypeSpec typeSpec = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addMethod(MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addParameter(ClassName.get("com.example.myprepare.annotation_test", "AnnotationTestActivity"), "activity")
            .addStatement("activity.textView = activity.findViewById(R.id.annotation_test_text)")
            .build())
        .build();
    try {
      JavaFile.builder("com.example.myprepare", typeSpec)
          .build()
          .writeTo(filer);
    } catch (IOException e) {
      e.printStackTrace();
    }*/

    // 自动版本
    for (Element element: roundEnvironment.getRootElements()) {
      String packageStr = element.getEnclosingElement().toString();
      String classStr = element.getSimpleName().toString();
      System.out.println("packageStr = " + packageStr + ", classStr = " + classStr);
      ClassName className = ClassName.get(packageStr,classStr + "Binding" );
      MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
          .addModifiers(Modifier.PUBLIC)
          .addParameter(ClassName.get(packageStr, classStr), "activity");
      boolean hasBinding = false;

      for (Element enclosedElement: element.getEnclosedElements()) {
        if (enclosedElement.getKind() == ElementKind.FIELD) {
          BindView bindView = enclosedElement.getAnnotation(BindView.class);
          if (bindView != null) {
            hasBinding = true;
            constructorBuilder.addStatement(
                "activity.$N = activity.findViewById($L)",
                enclosedElement.getSimpleName(),
                bindView.value()
            );
          }
        }
      }

      Modifier[] modifiers;
      MethodSpec methodSpec;
      TypeSpec buildClass = TypeSpec.classBuilder(className)
          .addModifiers(Modifier.PUBLIC)
          .addMethod(constructorBuilder.build())
          .build();

      if(hasBinding) {
        try {
          JavaFile.builder(packageStr, buildClass).build().writeTo(filer);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(BindView.class.getCanonicalName());
  }
}