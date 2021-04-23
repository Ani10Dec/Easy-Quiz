package com.easy.easyquizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.easy.easyquizapp.Modal.Question
import com.easy.easyquizapp.R
import com.easy.easyquizapp.ResultActivity

class QuizActivity : AppCompatActivity() {
    lateinit var stName: String
    lateinit var tvOption1: TextView
    lateinit var tvOption2: TextView
    lateinit var tvOption3: TextView
    lateinit var tvOption4: TextView
    lateinit var btnSubmit: Button
    lateinit var progressBar: ProgressBar
    lateinit var tvProgress: TextView
    lateinit var tvQuestion: TextView
    lateinit var flagImage: ImageView
    lateinit var questArrayList: ArrayList<Question>
    private var mCurrentPosition: Int = 1
    private var mSelectedOption: String = ""
    private var mSelectedQuestion: Int = 0
    private var mSelectedAnswer: String = ""
    private var correctAnswerResult: Int = 0
    private var progressInt: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // HOOKS
        stName = intent.getStringExtra("name").toString()
        tvOption1 = findViewById(R.id.option1)
        tvOption2 = findViewById(R.id.option2)
        tvOption3 = findViewById(R.id.option3)
        tvOption4 = findViewById(R.id.option4)
        btnSubmit = findViewById(R.id.btn_submit)
        progressBar = findViewById(R.id.progress_horizontal)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tv_question)
        flagImage = findViewById(R.id.flag_image)
        resetValue()

        // Adding Question in ArrayList
        questArrayList = ArrayList()
        questArrayList.add(
            Question(
                1,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_argentina,
                "Argentina",
                "USA",
                "India",
                "Egypt",
                "Argentina"
            )
        )
        questArrayList.add(
            Question(
                2,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_australia,
                "Argentina",
                "Australia",
                "India",
                "USA",
                "Australia"
            )
        )
        questArrayList.add(
            Question(
                3,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_belgium,
                "Belgium",
                "Norway",
                "Russia",
                "India",
                "Belgium"
            )
        )
        questArrayList.add(
            Question(
                4,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_brazil,
                "Myanmar",
                "USA",
                "Brazil",
                "Iceland",
                "Brazil"
            )
        )
        questArrayList.add(
            Question(
                5,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_denmark,
                "Armenia",
                "Belgium",
                "Bhutan",
                "Denmark",
                "Denmark"
            )
        )
        questArrayList.add(
            Question(
                6,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_fiji,
                "Canada",
                "Fiji",
                "Botswana",
                "Egypt",
                "Fiji"
            )
        )
        questArrayList.add(
            Question(
                7,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_germany,
                "Germany",
                "Chile",
                "China",
                "Egypt",
                "Germany"
            )
        )
        questArrayList.add(
            Question(
                8,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_india,
                "India",
                "Germany",
                "Australia",
                "Egypt",
                "India"
            )
        )
        questArrayList.add(
            Question(
                9,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_kuwait,
                "Russia",
                "Kuwait",
                "Iran",
                "Israel",
                "Kuwait"
            )
        )
        questArrayList.add(
            Question(
                10,
                "Identify Country's Flag",
                R.drawable.ic_flag_of_new_zealand,
                "Belgium",
                "Australia",
                "New Zealand",
                "USA",
                "New Zealand"
            )
        )

        // Initialize First Question
        initQuestion()

        // Checking Answer
        btnSubmit.setOnClickListener {
            if (btnSubmit.text == resources.getString(R.string.submit)) {
                if (mSelectedOption.isNotEmpty()) {
                    if (mSelectedOption == mSelectedAnswer) {

                        // Increasing Correct Answer Count
                        correctAnswerResult++

                        // Increasing Question Position Count
                        mCurrentPosition++

                        // Checking last question count
                        if (mCurrentPosition <= questArrayList.size + 1) {
                            if (mCurrentPosition > questArrayList.size) {
                                btnSubmit.text = resources.getString(R.string.result)
                            } else
                                initQuestion()
                        }
                    } else
                    // Checking Answer
                        checkCorrectAnswer(questArrayList[mCurrentPosition - 1].answer)
                } else
                    Toast.makeText(this, "Choose Answer to Submit", Toast.LENGTH_SHORT).show()

            } else if (btnSubmit.text == resources.getString(R.string.next)) {
                if (mCurrentPosition >= questArrayList.size + 1) {
                    btnSubmit.text = resources.getString(R.string.result)
                } else
                    initQuestion()

            } else if (btnSubmit.text == resources.getString(R.string.result)) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("count", correctAnswerResult.toString())
                startActivity(intent)
                finish()
            }
        }
    }

    private fun initQuestion() {
        val questionNumber = questArrayList[mCurrentPosition - 1]
        tvQuestion.text = questionNumber.ques
        tvOption1.text = questionNumber.optOne
        tvOption2.text = questionNumber.optTwo
        tvOption3.text = questionNumber.optThree
        tvOption4.text = questionNumber.optFour
        mSelectedAnswer = questionNumber.answer
        flagImage.setImageResource(questionNumber.img)
        progressBar.progress = mCurrentPosition
        tvProgress.text = "${mCurrentPosition}/${progressBar.max}"
        resetValue()
    }

    fun onOptionClick(view: View) {
        when (view as TextView) {
            tvOption1 -> {
                mSelectedOption = tvOption1.text.toString()
                tvOption1.background = resources.getDrawable(R.drawable.selected_option_background)
                tvOption2.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption3.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption4.background = resources.getDrawable(R.drawable.default_text_background)
            }
            tvOption2 -> {
                mSelectedOption = tvOption2.text.toString()
                tvOption1.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption2.background = resources.getDrawable(R.drawable.selected_option_background)
                tvOption3.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption4.background = resources.getDrawable(R.drawable.default_text_background)
            }
            tvOption3 -> {
                mSelectedOption = tvOption3.text.toString()
                tvOption1.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption2.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption3.background = resources.getDrawable(R.drawable.selected_option_background)
                tvOption4.background = resources.getDrawable(R.drawable.default_text_background)
            }
            tvOption4 -> {
                mSelectedOption = tvOption4.text.toString()
                tvOption1.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption2.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption3.background = resources.getDrawable(R.drawable.default_text_background)
                tvOption4.background = resources.getDrawable(R.drawable.selected_option_background)
            }
        }
    }

    private fun resetValue() {
        btnSubmit.text = resources.getString(R.string.submit)
        tvOption1.background = resources.getDrawable(R.drawable.default_text_background)
        tvOption2.background = resources.getDrawable(R.drawable.default_text_background)
        tvOption3.background = resources.getDrawable(R.drawable.default_text_background)
        tvOption4.background = resources.getDrawable(R.drawable.default_text_background)
        tvOption1.setTextColor(resources.getColor(R.color.black))
        tvOption2.setTextColor(resources.getColor(R.color.black))
        tvOption3.setTextColor(resources.getColor(R.color.black))
        tvOption4.setTextColor(resources.getColor(R.color.black))
        mSelectedOption = ""
        tvOption1.isEnabled = true
        tvOption2.isEnabled = true
        tvOption3.isEnabled = true
        tvOption4.isEnabled = true
    }

    private fun checkCorrectAnswer(ans: String) {
        if (tvOption1.text.contains(ans)) {
            tvOption1.background =
                resources.getDrawable(R.drawable.correct_option_background)
        } else if (tvOption2.text.contains(ans)) {
            tvOption2.background =
                resources.getDrawable(R.drawable.correct_option_background)
        } else if (tvOption3.text.contains(ans)) {
            tvOption3.background =
                resources.getDrawable(R.drawable.correct_option_background)
        } else if (tvOption3.text.contains(ans)) {
            tvOption4.background =
                resources.getDrawable(R.drawable.correct_option_background)
        }

        tvOption1.isEnabled = false
        tvOption2.isEnabled = false
        tvOption3.isEnabled = false
        tvOption4.isEnabled = false
        btnSubmit.text = resources.getString(R.string.next)
        // Increasing question count
        mCurrentPosition++
    }
}