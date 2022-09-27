package windown

import layout.CovertViewLayout
import layout.JSONViewLayout
import model.DartBaseModel
import javax.swing.JFrame
import javax.swing.JSplitPane

class MainWindow : JFrame("Json to dart") {

    private val mainLayout = MainLayout()

    init {
        setSize(1000, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        add(mainLayout)
    }
}


class MainLayout : JSplitPane(HORIZONTAL_SPLIT) {

    /**
     * json视图区域
     */
    private val jsonView = JSONViewLayout {
        handleModels(it)
    }

    /**
     * 解析一览表
     */
    private val covertLayout = CovertViewLayout()

    init {
        add(jsonView)
        add(covertLayout)
    }


    /**
     * 处理解析出的模型
     */
    private fun handleModels(models: List<DartBaseModel>) {
        covertLayout.handleModels(models)
    }
}