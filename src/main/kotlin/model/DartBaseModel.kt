package model

/**
 * dart的数据类型
 */
enum class DartType {
    Int,
    String,
    Bool,
    Double,
    Dynamic,
    Object
}

/**
 * dart对象的组成器
 */
interface DartBaseModel {

    /**
     * 数据名字
     */
    fun getName(): String

    /**
     * 数据类型
     */
    fun getType(): DartType

    /**
     * 是否必填参数
     */
    fun isRequest(): Boolean

    /**
     * 是否为对象类型
     */
    fun isObject(): Boolean

    /**
     * 子类型
     */
    fun getNodes(): List<DartBaseModel>

}