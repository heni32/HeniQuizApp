package edu.miu.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.utils.Quiz
import edu.miu.quizapp.utils.QuizDatabase
import edu.miu.quizapp.utils.BaseFragment
import edu.miu.quizapp.utils.PreferanceManager
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var tvWelcome: TextView
    private var prefManager: PreferanceManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefManager = PreferanceManager(context)
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        tvWelcome = view.findViewById(R.id.logo_welcome)
        addQuestionsToDB()
        return view
    }

    fun addQuestionsToDB(){

        val quiz2 = Quiz(2L,"2)Kotlin is a statically-typed programming language which runs on the?", "A. JCM","B. JVM","C.C. JPM","D. JDM","b","Answer: (b) Explanation: Kotlin is a statically-typed programming language which runs on the JVM. It can be compiled either using Java source code and LLVM compiler.")
        val quiz3 = Quiz(3L,"3) Why you should switch to Kotlin from Java?","A. Kotlin language is quite simple compared to Java", "B. It reduces may redundancies in code as compared to Java","C. Kotlin can offer some useful features which are not supported by Java","D. All of the above","d","Answer: (d) Explanation: Kotlin language is quite simple compared to Java. It reduces may redundancies in code as compared to Java. Kotlin can offer some useful features which are not supported by Java.")
        val quiz4 = Quiz(4L,"4) Which of the following is the first mobile phone released that ran the Android OS?","A. HTC Hero", "B. Google gPhone","C. T - Mobile G1","D. None of the above","c","Answer: (c) T - Mobile G1\n" +
                "\n" +
                "Explanation: The first Android mobile was publicly released with Android 1.0 of the T-Mobile G1 (aka HTC Dream) in October 2008.")
        val quiz5 = Quiz(5L,"5) Which of the following virtual machine is used by the Android operating system?","A. JVM", "B. Dalvik virtual machine","C. Simple virtual machine","D. None of the above","b","Answer: (b) Dalvik virtual machine\n" +
                "\n" +
                "Explanation: The Dalvik Virtual Machine (DVM) is an android virtual machine optimized for mobile devices. It optimizes the virtual machine for memory, battery life, and performance. Dalvik is a name of a town in Iceland. The Dalvik VM was written by Dan Bornstein.")
        val quiz6 = Quiz(6L,"6) Android is based on which of the following language?","A. Java", "B. C++","C. C","D. None of the above","a","Answer: (a) Java\n" +
                "\n" +
                "Explanation: Java language is mainly used to write the android code even though other languages can be used.")
        val quiz7 = Quiz(7L,"7) APK stands for -","A. Android Phone Kit", "B. Android Page Kit","C. Android Package Kit","D. None of the above","c","Answer: (c) Android Package Kit\n" +
                "\n" +
                "Explanation: An APK is a short form of the Android Package Kit. An APK file is the file format used to install the applications on the android operating system.")
        val quiz8 = Quiz(8L,"8) What does API stand for?","A. Application Programming Interface", "B. Android Programming Interface","C. Android Page Interface","D. Application Page Interface","a","Answer: (a) Application Programming Interface\n" +
                "\n" +
                "Explanation: API stands for application program interface. It is a set of routines, protocols, and tools for building software and applications. It may be any type of system like a web-based system, operating system, or database system.")
        val quiz9 = Quiz(9L,"9) Which of the following converts Java byte code into Dalvik byte code?","A. Dalvik converter", "B. Dex compiler","C. Mobile interpretive compiler (MIC)","D. None of the above","b","Answer: (b) Dex compiler\n" +
                "\n" +
                "Explanation: The Dex compiler converts the class files into a .dex file that runs on the Dalvik VM. Multiple class files are converted into one dex file.")
        val quiz10 = Quiz(10L,"10) How can we stop the services in android?","A. By using the stopSelf() and stopService() method", "B. By using the finish() method","C. By using system.exit() method","D. None of the above","a","Answer: (a) By using the stopSelf() and stopService() method\n" +
                "\n" +
                "Explanation: A service is started when a component (like activity) calls the startService() method; now, it runs in the background indefinitely. It is stopped by the stopService() method. The service can stop itself by calling the stopSelf() method.")
        val quiz11 = Quiz(11L,"11) What is an activity in android?","A. android class", "B. android package","C. A single screen in an application with supporting java code","D. None of the above","c","Answer: (c) A single screen in an application with supporting java code\n" +
                "\n" +
                "Explanation: An activity is a single screen in android. It is like a window or frame of Java. By the help of activity, you can place all your UI components or widgets in a single screen. Activity is like a frame or window in java that represents GUI. It represents one screen of android.")
        val quiz12 = Quiz(12L,"12) How can we kill an activity in android?","A. Using finish() method", "B. Using finishActivity(int requestCode)","C. Both (a) and (b)","D. Neither (a) nor (b)","c","Answer: (c) Both (a) and (b)\n" +
                "\n" +
                "Explanation: The finish() method is used to close the activity. Whereas the finishActivity(int requestCode) also closes the activity with requestCode.")
        val quiz13 = Quiz(13L,"13) ADB stands for -","A. Android debug bridge", "B. Android delete bridge","C. Android destroy bridge","D. None of the above","a","Answer: (a) Android debug bridge\n" +
                "\n" +
                "Explanation: ADB stands for Android Debug Bridge. It is a command-line tool that is used to communicate with the emulator instance.")
        val quiz14 = Quiz(14L,"14) On which of the following, developers can test the application, during developing the android applications?",
            "A. Third-party emulators", "B. Emulator included in Android SDK","C. Physical android phone","D. All of the above","d","Answer: (d) All of the above\n" +
                    "\n" +
                    "Explanation: We can use the Android emulator, physical android phone, or third-party emulator as a target device to execute and test our Android application.")
        val quiz15 = Quiz(15L,"15)____________ feature allows removing the risk of occurrence of NullPointerException in real time.","A. Null Risk\n", "B. Null Safety","C. Null Pointer","D. All of the above","b","Answer: (b) Explanation: Null Safety feature allows removing the risk of occurrence of NullPointerException in real time. It is also possible to differentiate between nullable references and non-nullable references.")

        val quiz1 = Quiz(1L,"1) what is an immutable variable?","A. variable that can't change,read-only", "B. A variable that can change","C. A variable used for string interpolation","D. none of the above","a","Answer: (a) Immutable variable are a variable that cannot change, it is read-only.")

        launch {
            context?.let {
                QuizDatabase(it)
                    .getQuizDao().deleteAllQuiz()
                QuizDatabase(it)
                    .getQuizDao().addQuizzes(
                        quiz9,quiz10,quiz11,quiz12,quiz13,quiz14,quiz1,quiz2,quiz3,quiz4,quiz5,quiz6,quiz7,quiz8,quiz15)
            }
        }
    }

    override fun onResume(){
        super.onResume()
        tvWelcome.postDelayed({
            if (!prefManager?.isFirstTimeLaunch()!!) {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_resultFragment)
            }
        }, 1000)
    }



}