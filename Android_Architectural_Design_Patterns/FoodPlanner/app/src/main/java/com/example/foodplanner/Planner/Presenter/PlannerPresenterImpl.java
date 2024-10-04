package com.example.foodplanner.Planner.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Planner.Viewer.PlannerView;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.PlannedMeal;

import java.util.Date;
import java.util.List;

public class PlannerPresenterImpl implements PlannerPresenter {

    private PlannerView _view;
    private MealsRepository _repo;
    LiveData<List<PlannedMeal>> planMealList;
    //LiveData<List<PlannedMeal>> getPlannedMeals

    public PlannerPresenterImpl(PlannerView view, MealsRepository repo) {
        this._view = view;
        this._repo = repo;

    }

    public PlannerPresenterImpl(MealsRepository repo) {
        this._repo = repo;
    }



    @Override
    public void AddtoPlannedTable(PlannedMeal meal, Date date) {
        _repo.insertPlannedMeal(meal,date);
    }

    @Override
    public void removeFromPlannedTable(PlannedMeal meal) {
        _repo.deletePlannedMeal(meal);
    }


    @Override
    public LiveData<List<PlannedMeal>> getPlannedMealByDate(Date selectedDate) {
        return _repo.getMealForDay(selectedDate);
    }
}
