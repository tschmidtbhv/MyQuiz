package com.example.android.myquiz.data;

import com.example.android.myquiz.R;

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
            case R.id.random_question:
                generateQuestionWithOneAnswer();
                break;

            case R.id.multiple_choice:
                generateQuestionsWithMultipleAnswers();
                break;

            case R.id.write_answer:
                generateQuestionWithWriting();
                break;

        }
    }

    /**
     * Create Questions with one possible answer
     */
    private void generateQuestionWithOneAnswer() {

        createQuestion("Welchen Tag haben wir heute", "Montag", "Freitag","Sonntag", new int[]{2,3});

    }


    /**
     * Create Questions with more than one possible answer
     */
    private void generateQuestionsWithMultipleAnswers() {

    }

    /**
     * Generate Question for writing
     */
    private void generateQuestionWithWriting() {

    }

    /**
     * Get the available Questions
     * @return ArrayList
     */
    public ArrayList<Question> getQuestions() {

        if(mQuestions == null)return mQuestions = new ArrayList<Question>();

        return mQuestions;
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

            isRightAnswer = checkForRightAnswer(i, indexesOfRightAnswer);

            Answer answer = new Answer(tempOpt, isRightAnswer);
            answerArrayList.add(answer);
        }

        question.setAnswers(answerArrayList);
        mQuestions = new ArrayList<Question>();
        mQuestions.add(question);

    }

    /**
     * Check the currentIndex with given indexes of right answers
     * @param currentIndex the current index
     * @param indexesOfRightAnswer int array of right indexes
     * @return boolean
     */

    private boolean checkForRightAnswer(int currentIndex, int[] indexesOfRightAnswer) {

        for(int index : indexesOfRightAnswer) {
            if(index == currentIndex) {
                return true;
            }
        }
        return false;
    }
}
