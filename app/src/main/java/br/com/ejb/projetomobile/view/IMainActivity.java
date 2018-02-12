package br.com.ejb.projetomobile.view;

import java.util.List;

import br.com.ejb.projetomobile.model.Value;

public interface IMainActivity {
    void updateChart(List<Value> values);

    void updateCard(Value value);

    void showToast(int message);

    void openProgress();

    void closeProgress();
}
