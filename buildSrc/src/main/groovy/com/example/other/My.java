//package com.example.other;
//
//import com.android.build.api.transform.Transform;
//import com.android.build.gradle.BaseExtension;
//import org.gradle.api.Action;
//import org.gradle.api.Plugin;
//import org.gradle.api.Project;
//import org.jetbrains.annotations.NotNull;
//
//class My implements Plugin<Project> {
//
//  @Override
//  public void apply(Project target) {
//    final MyExtension extension = target.getExtensions().create("my", MyExtension.class);
//
//    target.afterEvaluate(new Action<Project>() {
//      @Override
//      public void execute(@NotNull Project project) {
//        System.out.println("name = " + extension.name + ", age = " + extension.age);
//      }
//    });
//
//
//    Transform transform = new MyTransform();
//    BaseExtension baseExtension = target.getExtensions().getByType(BaseExtension.class);
//    baseExtension.registerTransform(transform);
//  }
//}
