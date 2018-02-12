package br.com.ejb.projetomobile.presenter;

import android.content.res.Resources;

import java.util.List;

import br.com.ejb.projetomobile.model.Value;
import lecho.lib.hellocharts.model.LineChartData;

public interface IMainPresenter {
    void loadCharts(String timespan);

    LineChartData generateDataChart(Resources resources, List<Value> values);
}
