package util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import model.DartBaseModel
import model.DartType

class CovertUtil {
    companion object {

        /**
         * 生成待解析模型
         */
        fun parse(jsonString: String): List<DartBaseModel> {
            val result = mutableListOf<DartBaseModel>()
            if (!JSON.isValid(jsonString)) {
                println("无效的json")
                return emptyList()
            }
            val jsonIsArr = JSON.isValidArray(jsonString)
            if (jsonIsArr) {
                val objs = JSON.parseArray(jsonString, Map::class.java)
                objs.forEach { obj ->
                    val models = handleObject(obj as Map<String, Any>)
                    result.addAll(models)
                }
            } else {
                val objs = JSON.parseObject(jsonString, Map::class.java)
                val models = handleObject(objs as Map<String, Any>)
                result.addAll(models)
            }
            return result
        }


        /**
         * 处理对象
         */
        private fun handleObject(obj: Map<String, Any>): List<DartBaseModel> {
            val r = mutableListOf<DartBaseModel>()
            obj.forEach { (t, u) ->
                println("-->${t}  ${u::class.java}")
                r.add(object : DartBaseModel {
                    override fun getName(): String {
                        return t
                    }

                    override fun getType(): DartType {

                        return when (u::class.java) {
                            Boolean::class.java -> DartType.Bool
                            String::class.java -> DartType.String
                            Integer::class.java -> DartType.Int
                            Double::class.java -> DartType.Double
                            JSONObject::class.java -> DartType.Object
                            else -> DartType.Dynamic
                        }
                    }

                    override fun isRequest(): Boolean {
                        return true
                    }

                    override fun isObject(): Boolean {
                        return false
                    }

                    override fun getNodes(): List<DartBaseModel> {
                        return if (u is JSONObject) {
                            handleObject(u)
                        } else {
                            emptyList()
                        }
                    }

                })
            }
            return r
        }
    }
}