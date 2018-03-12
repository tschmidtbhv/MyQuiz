package com.example.android.myquiz.helper;


import android.widget.CompoundButton;

import com.example.android.myquiz.data.Answer;
import com.example.android.myquiz.data.Question;
import com.example.android.myquiz.data.QuestionsData;

import java.util.ArrayList;

/**
 * Created by ithom on 10.03.2018.
 */

public class QuestionHelper {

    /**
     * Check the currentIndex with given indexes of right answers
     * Is used to set isRight value to the answer
     * @param currentIndex         the current index
     * @param indexesOfRightAnswer int array of right indexes
     * @return boolean
     */
    public static boolean checkForRightAnswer(int currentIndex, int[] indexesOfRightAnswer) {

        for (int index : indexesOfRightAnswer) {
            if (index == currentIndex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the answers with the selection
     *
     * @param currentQuestionIndex //Index for the current question
     * @param questionsData        //QuestionData contains the question with it answer
     * @param compountArrayList    //ArrayList with CompountButton (RadioButton / Checkbox)
     * @return boolean isRight or not
     */
    public static boolean checkGivenAnswer(int currentQuestionIndex, QuestionsData questionsData, ArrayList<CompoundButton> compountArrayList) {

        boolean isRight = false;
        Question question = questionsData.getQuestions().get(currentQuestionIndex);
        ArrayList<Answer> answers = question.getAnswers();

        for (int i = 0; i < compountArrayList.size(); i++) {
            Answer answer = answers.get(i);
            CompoundButton checkBox = (CompoundButton) compountArrayList.get(i);

            if (answer.isRight()) {

                if (checkBox.isChecked() == answer.isRight()) {
                    isRight = true;
                } else {
                    isRight = false;
                    return isRight;
                }
            } else if (checkBox.isChecked()) {
                isRight = false;
                return isRight;
            }
        }


        return isRight;
    }

    /**
     * Check the right answer with the given one
     * @param givenAnswer
     * @param question
     * @return
     */
    public static boolean checkWritenAnswer(String givenAnswer, Question question){

        String rightAnswer = question.getAnswers().get(0).getAnswer();

        //Convert both Strings to uppercase
        rightAnswer = rightAnswer.toUpperCase();
        givenAnswer = givenAnswer.toUpperCase();

        //Check the right answer equals the given
        if(rightAnswer.equals(givenAnswer)){
            return true;
        }

        return false;
    }
}
