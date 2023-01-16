package edu.miu.quizapp.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.utils.Quiz
import edu.miu.quizapp.utils.QuizDatabase
import edu.miu.quizapp.utils.MyAppUtils.*
import edu.miu.quizapp.utils.BaseFragment
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private lateinit var tv_Question: TextView
    private lateinit var tv_Score: TextView
    private lateinit var radio_Group: RadioGroup
    private lateinit var myQuestions: List<Quiz>
    private var qstnIndex = 0
    private var home_ViewModel: HomeViewModel? = null
    private var selectedAnswer: String? = null
    private var answersToQuestions: MutableList<String> = mutableListOf()
    private lateinit var current_Quiz: Quiz
    private var isFirstTime = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val skipBtn = view.findViewById<Button>(R.id.btn_qstn_skip)
        val nextBtn = view.findViewById<Button>(R.id.btn_qstn_next)
        tv_Question = view.findViewById(R.id.tv_question)
        tv_Score = view.findViewById(R.id.tv_score)
        home_ViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val scoreLiveData: MutableLiveData<Int> = home_ViewModel!!.getInitialScore()
        scoreLiveData.observe(viewLifecycleOwner) {
            tv_Score.text = String.format("%d/15", it)
        }
        launch {
            context?.let {
                myQuestions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                changeQuestion(view)
            }
        }
        skipBtn.setOnClickListener {
            changeQuestion(view)
        }
        nextBtn.setOnClickListener {
            if (selectedAnswer != null){
                evaluateAnswer(selectedAnswer!!)
                changeQuestion(view)
            } else{
            val myToast =  Toast.makeText(context,getString(R.string.please_provide_answer_toast_message),Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.LEFT,200,200)
            myToast.show()}

        }
        radio_Group = view.findViewById(R.id.question_radio)
        radio_Group.setOnCheckedChangeListener(this::handler)
        return view
    }

    private fun changeQuestion(view:View) {
        if(!isFirstTime){
            val selectedAns = if(selectedAnswer!=null) selectedAnswer else ""
            answersToQuestions.add(selectedAns!!)
        }
        isFirstTime = false
        if (qstnIndex == 15) {
            val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(
                score = home_ViewModel?.getFinalScore()?.value!!, answers = answersToQuestions.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        current_Quiz = myQuestions[qstnIndex]
        tv_Question.text = current_Quiz.question
        val radioGroup = view.findViewById(R.id.question_radio) as RadioGroup
        val questionChoices = listOf(current_Quiz.a, current_Quiz.b, current_Quiz.c, current_Quiz.d)
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = questionChoices[i]
        }
        qstnIndex++
        selectedAnswer = null
        radioGroup.clearCheck()
    }

    private fun handler(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.radio_q1_a -> selectedAnswer = AnswerChoice.A.value
            R.id.radio_q1_b -> selectedAnswer = AnswerChoice.B.value
            R.id.radio_q1_c -> selectedAnswer = AnswerChoice.C.value
            R.id.radio_q1_d -> selectedAnswer = AnswerChoice.D.value
        }
    }

    private fun evaluateAnswer(ans: String) {
        if (current_Quiz.answer == ans) {
            home_ViewModel!!.getCurrentScore()
        }
    }


}