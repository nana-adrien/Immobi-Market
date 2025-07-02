package empire.digiprem.config

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable


@Composable
expect fun getActualWindowsSize(): WindowSizeClass

@Composable
fun isCompactApplication():Boolean= isCompactMobilePlatform() || isCompactPlatform()

    @Composable
fun isMediumApplication()= isMediumPlatform() || isMediumMobilePlatform()
@Composable
fun isExpandedApplication():Boolean= isExpandedMobilePlatform() || isExpandedPlatform()
@Composable
fun isCompactMobilePlatform():Boolean=( getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Compact||getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Medium) && (getPlatform()==Platform.ANDROID||getPlatform()==Platform.IOS)
@Composable
fun isMediumMobilePlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Medium && (getPlatform()==Platform.ANDROID||getPlatform()==Platform.IOS)
@Composable
fun isExpandedMobilePlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Expanded && (getPlatform()==Platform.ANDROID||getPlatform()==Platform.IOS)

@Composable
fun isCompactPlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Compact && (getPlatform()==Platform.DESKTOP||getPlatform()==Platform.WEB)
@Composable
fun isMediumPlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Medium  && (getPlatform()==Platform.DESKTOP||getPlatform()==Platform.WEB)
@Composable
fun isExpandedPlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Expanded && (getPlatform()==Platform.DESKTOP||getPlatform()==Platform.WEB)