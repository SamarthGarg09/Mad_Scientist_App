

package com.example.android.navigation

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)


    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "Nuclear sizes are expressed in a unit named",
                    answers = listOf("Fermi", "Newton", "Tesla", "Angstrom")),
            Question(text = "The speed of light will be minimum while passing through",
                    answers = listOf("Glass", "Water", "Air", "Vaccum")),
            Question(text = "Which of the following is not a vector quantity?",
                    answers = listOf("Speed", "Velocity", "Torque", "Displacement")),
            Question(text = "Which of the following is not the unit of time",
                    answers = listOf("Parallactic second ", "Micro Second", "Solar Day", "Leap Year")),
            Question(text = "An air bubble in water will act like a",
                    answers = listOf("Concave Lens", "Convex Lens", "Concave Mirror", "Convex Mirror")),
            Question(text = "A solid ball of metal has a spherical cavity inside it.When the ball is heated the volume of the cavity will",
                    answers = listOf("increase", "decrease", "remain unaffected", "have its shape changed")),
            Question(text = "It is more difficult to walk on ice than on a concrete road because ",
                    answers = listOf("there is very little friction between the  ice and feet pressing it", " ice is soft when compared to concrete", " there is more friction between the ice and feet", "None of these")),
            Question(text = "Rain drops acquire spherical shape due to ",
                    answers = listOf("surface tension", "Viscosity", "Elasticity", "friction")),
            Question(text = "If a sound travels from air to water, the quantity that remain unchanged is",
                    answers = listOf("frequency", "velocity", "amplitude", "wavelength")),
            Question(text = "Air pressure is usually highest when the air is ",
                    answers = listOf("cool and moist", "warm and moist", "warm and dry", "cool and dry")),
            Question(text = "Sound following a flash of lightning is called",
                    answers = listOf("Thunder", "Stoning", "Cloud Clash", "Bolting")),
            Question(text = "When a bottle of perfume is opened in one corner of a room the smell spreads soon throughout the room.This is an example of ",
                    answers = listOf("diffusion", "viscosity", "capillarity", "surface tension")),
            Question(text = "The rate of change of linear momentum of a body falling freely under gravity is equal to it's ____ ?",
                    answers = listOf("Weight", "Kinetic Energy", "Potential Energy", "Impulse")),
            Question(text = "Which is best used as a sound absorbing material in partition walls ?",
                    answers = listOf("Glass - wool", "Glass pieces", "Steel", "Stone Chips")),
            Question(text = "The minimum number of Non zero non collinear vectors required to produce a zero vector is",
                    answers = listOf("3", "4", "2", "1")),
            Question(text = "Pieces of camphor placed on water move about rapidly. This is because of",
                    answers = listOf("surface tension","diffusion", "viscosity", "capillarity")),
            Question(text = "Mirage is an example of",
                    answers = listOf("Total Internal Reflection", "reflection", "refraction", "dispersion")),
            Question(text = "No current will flow between two charged bodies if they have the same ",
                    answers = listOf("potential", "resistance", "charge", "charge/potential ratio")),
            Question(text = "A periscope works by the principle of",
                    answers = listOf("total internal reflection", "diffraction", "refraction", "reflection")),
            Question(text = "The core of an electromagnet is made of soft iron because soft iron has",
                    answers = listOf("large susceptibility and small retentivity", "small density and large retentivity", "large density and large retentivity", " small susceptibility and small retentivity"))

    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding = DataBindingUtil.inflate<FragmentQuestionsBinding>(
                inflater, R.layout.fragment_questions, container, false)


        randomizeQuestions()


        binding.game = this


        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }

                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++

                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {

                        view.findNavController().navigate(QuestionsFragmentDirections.actionGameFragmentToCongratulationFragment(numQuestions,questionIndex))
                    }
                } else {

                    view.findNavController().navigate(QuestionsFragmentDirections.actionGameFragmentToLostFragment())
                }
            }
        }
        return binding.root
    }


    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }


    private fun setQuestion() {
        currentQuestion = questions[questionIndex]

        answers = currentQuestion.answers.toMutableList()

        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.madScientistGameQuestions, questionIndex + 1, numQuestions)
    }
}
