package com.example.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent.ContentType;
import com.android.build.api.transform.QualifiedContent.Scope;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.commons.io.FileUtils;

class MyTransform extends Transform {

  @Override
  public String getName() {
    return "WoDe";
  }

  @Override
  public Set<ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS;
  }

  @Override
  public Set<? super Scope> getScopes() {
    return TransformManager.SCOPE_FULL_PROJECT;
  }

  @Override
  public boolean isIncremental() {
    return false;
  }

  @Override
  public void transform(TransformInvocation transformInvocation)
      throws TransformException, InterruptedException, IOException {
    Collection<TransformInput> inputs = transformInvocation.getInputs();
    final TransformOutputProvider outputProviders = transformInvocation.getOutputProvider();

    inputs.forEach(new Consumer<TransformInput>() {
      @Override
      public void accept(final TransformInput transformInput) {
        transformInput.getJarInputs().forEach(new Consumer<JarInput>() {
          @Override
          public void accept(JarInput jarInput) {
            File dest = outputProviders
                .getContentLocation(jarInput.getName(), jarInput.getContentTypes(),
                    jarInput.getScopes(), Format.JAR);
            System.out.println("jar: file = " + jarInput.getFile().getPath() + ", dest = " + dest.getPath());
            try {
              FileUtils.copyFile(jarInput.getFile(), dest);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });

        transformInput.getDirectoryInputs().forEach(new Consumer<DirectoryInput>() {
          @Override
          public void accept(DirectoryInput directoryInput) {
            File dest = outputProviders
                .getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(),
                    directoryInput.getScopes(), Format.DIRECTORY);
            System.out.println("directory: file = " + directoryInput.getFile().getPath() + ", dest = " + dest.getPath());
            try {
              FileUtils.copyDirectory(directoryInput.getFile(), dest);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
      }
    });
  }
}
