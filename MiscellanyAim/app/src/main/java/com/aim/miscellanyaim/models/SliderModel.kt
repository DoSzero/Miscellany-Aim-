package com.aim.miscellanyaim.models

data class SliderModel(
    var sliderId:String,
    var sliderImage:Int,
) {
    constructor():this(
        "",
        0
    )
}
