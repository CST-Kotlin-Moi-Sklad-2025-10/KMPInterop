
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.otus.kmpinterop.StopwatchUiState
import ru.otus.kmpinterop.StopwatchViewModel

// Wrapper class for JS export
@JsExport
class JsStopwatchViewModel {
    private val coroutineScope = MainScope()
    private val viewModel = StopwatchViewModel()
    
    fun onStartClicked() {
        viewModel.onStartClicked()
    }
    
    fun onStopClicked() {
        viewModel.onStopClicked()
    }
    
    fun onDestroy() {
        viewModel.onDestroy()
        coroutineScope.cancel()
    }

    @OptIn(ExperimentalJsExport::class)
    fun observeState(callback: (state: StopwatchUiStateWrapper) -> Unit) {
        coroutineScope.launch {
            viewModel.uiState.collect {
                callback(it.toJsObject())
            }
        }
    }
}

@ExperimentalJsExport
@JsExport
class StopwatchUiStateWrapper(
    val currentTimeMillis: Double,
    val initialTimeMillis: Double,
    val formattedTime: String
)

fun StopwatchUiState.toJsObject() = StopwatchUiStateWrapper(
    currentTimeMillis = currentTimeMillis.toDouble(),
    initialTimeMillis = initialTimeMillis.toDouble(),
    formattedTime = formattedTime
)

@JsExport
@JsName("createStopwatchViewModel")
fun createStopwatchViewModel(): JsStopwatchViewModel {
    return JsStopwatchViewModel()
}

