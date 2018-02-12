package br.com.ejb.projetomobile.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.ejb.projetomobile.R;
import br.com.ejb.projetomobile.model.Value;
import br.com.ejb.projetomobile.presenter.impl.MainPresenter;
import br.com.ejb.projetomobile.util.Constants;
import br.com.ejb.projetomobile.view.IMainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity implements IMainActivity, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.lineChart)
    LineChartView lineChart;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tvDateCard)
    TextView tvDateCard;
    @BindView(R.id.tvValueCard)
    TextView tvValueCard;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.cardviewChart)
    CardView cardviewChart;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.loadCharts(Constants.TIMESPAN_1WEEK);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_oneweek:
                mainPresenter.loadCharts(Constants.TIMESPAN_1WEEK);
                return true;
            case R.id.navigation_fifteendays:
                mainPresenter.loadCharts(Constants.TIMESPAN_15DAYS);
                return true;
            case R.id.navigation_thirtydays:
                mainPresenter.loadCharts(Constants.TIMESPAN_30DAYS);
                return true;
        }
        return false;
    }

    /**
     * atualiza o grafico/chart com as cotacoes selecionadas
     *
     * @param values lista de cotacoes
     */
    @Override
    public void updateChart(final List<Value> values) {
        lineChart.setLineChartData(mainPresenter.generateDataChart(getResources(), values));
        lineChart.setZoomEnabled(false);
        lineChart.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(MainActivity.this, NumberFormat.getCurrencyInstance(Locale.US).format(value.getY()) + " - " +
                        mainPresenter.convertTimestampToDate(false, values.get(pointIndex).getX()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    /**
     * atualiza o card com a ultima cotacao
     *
     * @param value ultima cotacao
     */
    @Override
    public void updateCard(Value value) {
        tvDateCard.setText(mainPresenter.convertTimestampToDate(false, value.getX()));
        tvValueCard.setText(NumberFormat.getCurrencyInstance(Locale.US).format(value.getY()));
    }

    /**
     * mostra um toast com a mensagem do arquivo de strings
     *
     * @param message mensagem do arquivo de strings
     */
    @Override
    public void showToast(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    /**
     * mostra o progress e esconde os cards
     */
    @Override
    public void openProgress() {
        progressBar.setVisibility(View.VISIBLE);
        cardview.setVisibility(View.GONE);
        cardviewChart.setVisibility(View.GONE);
    }

    /**
     * fecha o progress e ja mostra os cards carregados
     */
    @Override
    public void closeProgress() {
        progressBar.setVisibility(View.GONE);
        cardview.setVisibility(View.VISIBLE);
        cardviewChart.setVisibility(View.VISIBLE);
    }
}
