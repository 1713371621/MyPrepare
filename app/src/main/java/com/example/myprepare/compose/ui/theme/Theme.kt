package com.example.myprepare.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val LocalMyPrepareColors: ProvidableCompositionLocal<MyColors> = compositionLocalOf {
  LightColorPalette
}

@Stable
object MyPrepareTheme {
  val colors: MyColors
    @Composable
    get() = LocalMyPrepareColors.current
}

private val DarkColorPalette: MyColors = MyColors(
  primary = purple200,
  primaryVariant = purple700,
  secondary = teal200,
  onBackground = gray2
)

private val LightColorPalette: MyColors = MyColors(
  primary = gray1,
  primaryVariant = gray2,
  secondary = teal1

  /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Stable
class MyColors(
  primary: Color = gray1,
  primaryVariant: Color = gray2,
  secondary: Color = teal1,
  secondaryVariant: Color = gray3,
  background: Color = Color.White,
  surface: Color = Color.White,
  error: Color = Color(0xFFB00020),
  onPrimary: Color = Color.White,
  onSecondary: Color = Color.Black,
  onBackground: Color = Color.White,
  onSurface: Color = Color.Black,
  onError: Color = Color.White
) {
  var primary by mutableStateOf(primary, structuralEqualityPolicy())
    internal set
  var primaryVariant by mutableStateOf(primaryVariant, structuralEqualityPolicy())
    internal set
  var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
    internal set
  var secondaryVariant by mutableStateOf(secondaryVariant, structuralEqualityPolicy())
    internal set
  var background by mutableStateOf(background, structuralEqualityPolicy())
    internal set
  var surface by mutableStateOf(surface, structuralEqualityPolicy())
    internal set
  var error by mutableStateOf(error, structuralEqualityPolicy())
    internal set
  var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
    internal set
  var onSecondary by mutableStateOf(onSecondary, structuralEqualityPolicy())
    internal set
  var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
    internal set
  var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
    internal set
  var onError by mutableStateOf(onError, structuralEqualityPolicy())
    internal set

}

@Composable
fun MyPrepareTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
  val colors: MyColors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  Providers(LocalMyPrepareColors provides colors) {
    MaterialTheme(
      typography = typography,
      shapes = shapes,
      content = content
    )
  }

}