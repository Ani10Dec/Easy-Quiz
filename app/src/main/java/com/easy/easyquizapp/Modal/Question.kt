package com.easy.easyquizapp.Modal

data class Question(
    val id: Int,
    val ques: String,
    val img: Int,
    val optOne: String,
    val optTwo: String,
    val optThree: String,
    val optFour: String,
    val answer: String
)