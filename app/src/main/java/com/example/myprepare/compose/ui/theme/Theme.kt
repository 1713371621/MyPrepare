package com.example.myprepare.compose.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
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

  enum class Theme {
    Light, Dark
  }
}

private val DarkColorPalette: MyColors = MyColors(
  primary = purple200,
  primaryVariant = purple700,
  secondary = teal200,
  onBackground = gray2,
  badge = yellow1,
  background = black1,
  textPrimary = white1,
  textSecondary = white2,
  divider = teal200,
  listItem = gray2,
  icon = green2
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
  badge: Color = red1,
  textPrimary: Color = gray1,
  textSecondary: Color = gray2,
  divider: Color = gray3,
  icon: Color = teal1,
  listItem: Color = white1,
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
  var badge by mutableStateOf(badge, structuralEqualityPolicy())
    internal set
  var textPrimary by mutableStateOf(textPrimary, structuralEqualityPolicy())
    internal set
  var textSecondary by mutableStateOf(textSecondary, structuralEqualityPolicy())
    internal set
  var divider by mutableStateOf(divider, structuralEqualityPolicy())
    internal set
  var listItem by mutableStateOf(listItem, structuralEqualityPolicy())
    internal set
  var icon by mutableStateOf(icon, structuralEqualityPolicy())
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
fun MyPrepareTheme(
  theme: MyPrepareTheme.Theme = MyPrepareTheme.Theme.Light,
  content: @Composable() () -> Unit
) {
  val targetColors = when (theme) {
    MyPrepareTheme.Theme.Light -> LightColorPalette
    MyPrepareTheme.Theme.Dark -> DarkColorPalette
  }

//  val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(600))
  val background = animateColorAsState(targetColors.background, TweenSpec(600))
  val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
  val divider = animateColorAsState(targetColors.divider, TweenSpec(600))
//  val chatPage = animateColorAsState(targetColors.chatPage, TweenSpec(600))
  val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
//  val textPrimaryMe = animateColorAsState(targetColors.textPrimaryMe, TweenSpec(600))
  val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
  val onBackground = animateColorAsState(targetColors.onBackground, TweenSpec(600))
  val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
//  val iconCurrent = animateColorAsState(targetColors.iconCurrent, TweenSpec(600))
  val badge = animateColorAsState(targetColors.badge, TweenSpec(600))
//  val onBadge = animateColorAsState(targetColors.onBadge, TweenSpec(600))
//  val bubbleMe = animateColorAsState(targetColors.bubbleMe, TweenSpec(600))
//  val bubbleOthers = animateColorAsState(targetColors.bubbleOthers, TweenSpec(600))
//  val textFieldBackground = animateColorAsState(targetColors.textFieldBackground, TweenSpec(600))
//  val more = animateColorAsState(targetColors.more, TweenSpec(600))
//  val chatPageBgAlpha = animateFloatAsState(targetColors.chatPageBgAlpha, TweenSpec(600))

  val colors = MyColors(
    background = background.value,
    listItem = listItem.value,
    divider = divider.value,
    textPrimary = textPrimary.value,
    textSecondary = textSecondary.value,
    onBackground = onBackground.value,
    icon = icon.value,
    badge = badge.value,
  )

  Providers(LocalMyPrepareColors provides colors) {
    MaterialTheme(
      typography = typography,
      shapes = shapes,
      content = content
    )
  }

}