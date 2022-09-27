package layout

import model.DartBaseModel
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.table.AbstractTableModel

/**
 * 转换视图
 */
class CovertViewLayout : JPanel(BorderLayout()) {

    private val jTable = JTable()

    init {
        jTable.model = MyCustomModel(emptyList())
        add(JScrollPane(jTable),BorderLayout.CENTER)
    }


    /**
     * 解析dart模型列表
     */
    fun handleModels(model: List<DartBaseModel>) {
        println("收到模型:${model.size}")
        jTable.model = MyCustomModel(model)
    }

}


/**
 * 自定义模型层
 */
class MyCustomModel(private val models: List<DartBaseModel>) : AbstractTableModel() {
    override fun getRowCount(): Int {
        return models.size
    }

    override fun getColumnCount(): Int {
        return 4
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        val item = models[rowIndex]
        return when (columnIndex) {
            0 -> item.getName()
            1 -> item.getType().toString()
            2 -> item.isObject()
            3 -> item.getNodes().size
            else -> ""
        }
    }

}