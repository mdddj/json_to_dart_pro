package layout

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import org.fife.ui.rtextarea.RTextScrollPane
import util.CovertUtil
import util.HandleModels
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Panel
import javax.swing.JButton
import javax.swing.JToolBar


const val testJson = """
{
	"name": "梁典典",
	"age": 18	
}
"""

/**
 * json视图
 */
class JSONViewLayout(private val handleModels: HandleModels) : Panel(BorderLayout()) {
    private val jsonEdit = RSyntaxTextArea()
    private val toolBar = JToolBar()
    private val formatButton = JButton("格式化")
    private val generateButton = JButton("生成模型")

    init {
        jsonEdit.text = testJson
        minimumSize = Dimension(500,600)
        jsonEdit.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JSON
        jsonEdit.isCodeFoldingEnabled = false
        add(RTextScrollPane(jsonEdit),BorderLayout.CENTER)
        initToolbar()
    }

    /**
     * 工具条
     */
    private fun initToolbar(){

        //格式化
        formatButton.addActionListener {
        }
        toolBar.add(formatButton)

        //生成dart模型
        generateButton.addActionListener {
            val models = CovertUtil.parse(testJson)
            handleModels.invoke(models)
        }
        toolBar.add(generateButton)
        add(toolBar,BorderLayout.PAGE_START)
    }

}