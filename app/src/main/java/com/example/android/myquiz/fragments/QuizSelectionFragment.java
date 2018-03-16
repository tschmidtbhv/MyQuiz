package com.example.android.myquiz.fragments;

import android.widget.CompoundButton;

import com.example.android.myquiz.Interfaces.QuestionInterface;

import java.util.ArrayList;

/**
 * Created by ithom on 15.03.2018.
 */

public abstract class QuizSelectionFragment extends QuizFragment implements QuestionInterface {


    private ArrayList<CompoundButton> mCompoundButtonArrayList;

    public void setCompoundButtonArrayList(ArrayList<CompoundButton> compoundButtonArrayList) {
        mCompoundButtonArrayList = compoundButtonArrayList;
    }

    /**
     * Add the index for the selected CompoundButton
     *
     * @return ArrayList<Integer> all seletions of CompoundButton
     */

    public ArrayList<Integer> getLastSelections() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < mCompoundButtonArrayList.size(); i++) {
            CompoundButton compoundButton = mCompoundButtonArrayList.get(i);
            if (compoundButton.isChecked()) integerArrayList.add(i);
        }
        return integerArrayList;
    }

    /**
     * Set the selection for CompoundButton
     *
     * @param integerArrayList holds the last selectionIndexes
     */

    public void setLastSelections(ArrayList<Integer> integerArrayList) {
        for (Integer selectedIndex : integerArrayList) {
            CompoundButton compoundButton = mCompoundButtonArrayList.get(selectedIndex);
            compoundButton.setChecked(true);
        }
    }
}
