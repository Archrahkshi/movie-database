package com.archrahkshi.moviedatabase.data

import com.archrahkshi.moviedatabase.data.vo.ViewObject

interface DataObject {
    fun toViewObject(): ViewObject
}
