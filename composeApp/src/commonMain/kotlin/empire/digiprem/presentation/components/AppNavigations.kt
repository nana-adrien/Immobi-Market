package empire.digiprem.presentation.components


/*
@Composable
fun NavigationApp(
    navigationRail: NavigationTypeEnum,
    firstContent: @Composable() (() -> Unit?)? = null,
    secondContent: @Composable() (() -> Unit?)? = null,
) {
    var leftWidth by remember { mutableStateOf(300f) }
    val resizerWidth = 5.dp
    var pointerIcon by remember { mutableStateOf(PointerIcon.Companion.Crosshair) }

    var isDragging by remember { mutableStateOf(false) }
    LaunchedEffect(isDragging) {

    }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.width(leftWidth.dp).fillMaxHeight()) {
            firstContent?.invoke()
        }
        Box(
            modifier = Modifier
                .width(resizerWidth)
                .fillMaxHeight()
                // .cursorForHorizontalResize() // Curseur de redimensionnement
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        leftWidth = (leftWidth + delta).coerceIn(250f, 600f)
                    },
                    onDragStarted = {
                        pointerIcon = PointerIcon.Hand
                    },
                    onDragStopped = {
                        pointerIcon = PointerIcon.Default
                    }
                )
                .background(Color.LightGray)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            secondContent?.invoke()
        }
    }
}*/
