package util

import model.DartBaseModel

/**
 * 解析dart列表模型,在左侧生成一个dart类
 */
typealias HandleModels = (model: List<DartBaseModel>)-> Unit