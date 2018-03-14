package com.example.android.myquiz.data;

import com.example.android.myquiz.R;
import com.example.android.myquiz.helper.Config;
import com.example.android.myquiz.helper.QuestionHelper;

import java.util.ArrayList;


/**
 * Created by ithom on 02.03.2018.
 */

public class QuestionsData{

    private ArrayList<Question> mQuestions;

    public QuestionsData(int questionType){
        checkQuestionsForType(questionType);
    }

    /**
     * Check the Questiontype
     * @param questionType The Questiontype
     */
    private void checkQuestionsForType(int questionType) {

        switch (questionType) {
            case Config.ONEANSWER:
                generateQuestionWithOneAnswer();
                break;

            case Config.MULTIPLEANSWER:
                generateQuestionsWithMultipleAnswers();
                break;

            case Config.WRITEANSWER:
                generateQuestionWithWriting();
                break;

        }
    }

    /**
     * Create Questions with one possible answer
     */
    private void generateQuestionWithOneAnswer() {

        for(int i = 0; i < 5; i++) {
            createQuestion("Welchen Tag haben wir heute " + (i +1), "Montag ", "Freitag", "Sonntag", new int[]{3});
        }
    }


    /**
     * Create Questions with more than one possible answer
     */
    private void generateQuestionsWithMultipleAnswers() {
        for(int i = 0; i < 5; i++) {
            createQuestion("Welchen Tag haben wir heute " + (i +1), "Montag ", "Freitag", "Sonntag", new int[]{3});
        }
    }

    /**
     * Generate Question for writing
     * Just add one possible answer and index
     */
    private void generateQuestionWithWriting() {
        createQuestion("Welchen Tag haben wir heute ?", "Montag");
        createQuestion("Welchen Tag haben wir heute 1 ?", "Montag1");
    }

    /**
     * Get the available Questions
     * @return ArrayList
     */
    public ArrayList<Question> getQuestions() {

        if(mQuestions == null)return mQuestions = new ArrayList<Question>();

        return mQuestions;
    }


    private void createQuestion(String question, String answer) {
        createQuestion(question, answer, null, null, null);
    }

    /**
     * Methode is used to create a question
     * using ArrayList for answers.
     * @param questionString The Question itself
     * @param optA Option 1
     * @param optB Option 2
     * @param optC Option 3
     * @param indexesOfRightAnswer The Index(s) for the right answer
     */
    private void createQuestion(String questionString, String optA, String optB, String optC, int[] indexesOfRightAnswer) {

        Question question = new Question(questionString);
        ArrayList<Answer> answerArrayList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {

            String tempOpt = "";
            boolean isRightAnswer = false;

            if (i == 1) {
                tempOpt = optA;
            } else if (i == 2) {
                tempOpt = optB;
            } else {
                tempOpt = optC;
            }

            if(indexesOfRightAnswer != null) {
                isRightAnswer = QuestionHelper.checkForRightAnswer(i, indexesOfRightAnswer);
            }

            Answer answer = new Answer(tempOpt, isRightAnswer);
            answerArrayList.add(answer);
        }

        question.setAnswers(answerArrayList);
        if(mQuestions == null) mQuestions = new ArrayList<Question>();
        mQuestions.add(question);

    }

}
