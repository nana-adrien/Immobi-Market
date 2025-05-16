package empire.digiprem.config

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable


@Composable
expect fun getActualWindowsSize(): WindowSizeClass

@Composable
fun isCompactMobilePlatform():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Compact && (getPlatform()==Platform.ANDROID||getPlatform()==Platform.IOS)
@Composable
fun isMediumWindowSize():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Medium
@Composable
fun isExpandedWindowSize():Boolean= getActualWindowsSize().widthSizeClass==WindowWidthSizeClass.Expanded