package com.example.android.myquiz.data;

import android.content.Context;

import com.example.android.myquiz.PlayActivity;
import com.example.android.myquiz.helper.Config;
import com.example.android.myquiz.helper.QuestionHelper;

import java.util.ArrayList;


/**
 * Created by ithom on 02.03.2018.
 */

public class QuestionsData {

    //Const prefix for different question types used to extract from Resource
    private final static String QUESTIIONONEPRE = "qone";
    private final static String ANSWERTONEPRE = "aone";
    private final static String QUESTIIONMULTIPRE = "qmul";
    private final static String ANSWERTMULTIPRE = "amul";
    private final static String QUESTIIONWRITEPRE = "qwri";
    private final static String ANSWERWRITEPRE = "awri";


    private final static int NUMBEROFQUESTIONS = 10;
    private final static int NUMBEROFPOSSIBLEANSWER = 3;


    private ArrayList<Question> mQuestions;
    private Context mContext;

    public QuestionsData(Context context, int questionType) {
        mContext = context;
        checkQuestionsForType(questionType);
    }

    /**
     * Check the Questiontype
     *
     * @param questionType The Questiontype
     */
    private void checkQuestionsForType(int questionType) {

        switch (questionType) {
            case Config.ONEANSWER:
                generateQuestion(QUESTIIONONEPRE, ANSWERTONEPRE);
                break;

            case Config.MULTIPLEANSWER:
                generateQuestion(QUESTIIONMULTIPRE, ANSWERTMULTIPRE);
                break;

            case Config.WRITEANSWER:
                generateQuestion(QUESTIIONWRITEPRE, ANSWERWRITEPRE);
                break;

        }
    }

    /**
     * Generate question with answer from Resources
     *
     * @param questionPrefix
     * @param answerPrefix
     */
    private void generateQuestion(String questionPrefix, String answerPrefix) {

        for (int i = 1; i <= NUMBEROFQUESTIONS; i++) {

            int[] rightIndexes = new int[NUMBEROFPOSSIBLEANSWER];
            String question = getStringResourceByName(questionPrefix + i);
            ArrayList<String> stringArrayList = new ArrayList<>();


            for (int y = 1; y <= NUMBEROFPOSSIBLEANSWER; y++) {
                if (questionPrefix.equals(QUESTIIONWRITEPRE) && y > 1) break;
                stringArrayList.add(getStringResourceByName(answerPrefix + i + "a" + y));
            }

            if (!questionPrefix.equals(QUESTIIONWRITEPRE)) {

                //Check String contains ISRIGHT and replace it.
                for (int x = 0; x < NUMBEROFPOSSIBLEANSWER; x++) {
                    String string = stringArrayList.get(x);
                    if (string.contains("ISRIGHT")) {
                        stringArrayList.set(x, string.replace("ISRIGHT", ""));
                        rightIndexes[x] = x + 1;
                    }
                }

                createQuestion(question, stringArrayList.get(0), stringArrayList.get(1), stringArrayList.get(2), rightIndexes);
            } else {
                //create Question for writing
                createQuestion(question, stringArrayList.get(0));
            }
        }
    }

    /**
     * Get String from Resource by name insteed of "int" id
     *
     * @param aString
     * @return
     */
    private String getStringResourceByName(String aString) {

        int resId = mContext.getResources().getIdentifier(aString, "string", PlayActivity.PACKAGE_NAME);
        return mContext.getString(resId);
    }


    /**
     * Get the available Questions
     *
     * @return ArrayList
     */
    public ArrayList<Question> getQuestions() {

        if (mQuestions == null) return mQuestions = new ArrayList<Question>();

        return mQuestions;
    }


    private void createQuestion(String question, String answer) {
        createQuestion(question, answer, null, null, null);
    }

    /**
     * Methode is used to create a question
     * using ArrayList for answers.
     *
     * @param questionString       The Question itself
     * @param optA                 Option 1
     * @param optB                 Option 2
     * @param optC                 Option 3
     * @param indexesOfRightAnswer The Index(s) for the right answer
     */
    private void createQuestion(String questionString, String optA, String optB, String optC, int[] indexesOfRightAnswer) {

        Question question = new Question(questionString);
        ArrayList<Answer> answerArrayList = new ArrayList<>();

        for (int i = 1; i <= NUMBEROFPOSSIBLEANSWER; i++) {

            String tempOpt = "";
            boolean isRightAnswer = false;

            if (i == 1) {
                tempOpt = optA;
            } else if (i == 2) {
                tempOpt = optB;
            } else {
                tempOpt = optC;
            }

            if (indexesOfRightAnswer != null) {
                isRightAnswer = QuestionHelper.checkForRightAnswer(i, indexesOfRightAnswer);
            }

            Answer answer = new Answer(tempOpt, isRightAnswer);
            answerArrayList.add(answer);
        }

        question.setAnswers(answerArrayList);
        if (mQuestions == null) mQuestions = new ArrayList<Question>();
        mQuestions.add(question);

    }

}
