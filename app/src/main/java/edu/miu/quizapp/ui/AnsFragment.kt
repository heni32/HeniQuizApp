package edu.miu.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import edu.miu.quizapp.R
import edu.miu.quizapp.utils.Quiz
import edu.miu.quizapp.utils.QuizDatabase
import edu.miu.quizapp.utils.BaseFragment
import kotlinx.coroutines.launch


class AnsFragment : BaseFragment() {

    private lateinit var myQuestions: List<Quiz>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout_view = inflater.inflate(R.layout.fragment_answer, container, false)
        val listView = layout_view.findViewById<ListView>(R.id.list_view)
        val myAnswers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        launch {
            context?.let {
                myQuestions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                myQuestions.forEach{ q ->
                    q.answer
                }
                listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    resultAnalysis(myQuestions, myAnswers.toList()))
            }
        }

        return layout_view
    }


    private fun resultAnalysis(questions: List<Quiz>, answers: List<String>): List<String> {
        val items = mutableListOf<String>()
        questions.forEachIndexed { index, quiz ->
            val listItem = String.format("%s\nYour answer: %s\nCorrect answer: %s",quiz.question,answers[index],quiz.explanation)
            items.add(listItem)
        }
        return items
    }

}