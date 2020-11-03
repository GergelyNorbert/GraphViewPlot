package com.example.mpandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private LineGraphSeries<DataPoint> series;
    private double lastXPoint = 2;
    private Random rnd = new Random();
    private int min = 0;
    private int max = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph);//Osszekotottem a kepernyovel
        series = new LineGraphSeries<>(new DataPoint[]
        {
            new DataPoint(0, 1),
            new DataPoint(1, 3),
            new DataPoint(2, 3)
        });
        graph.addSeries(series);

        graph.getViewport().setMinX(min);
        graph.getViewport().setMaxX(max);

        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setScalable(true);

        addRandomDataPoint(graph);
    }
    private void addRandomDataPoint(GraphView mygraph)
    {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lastXPoint += 0.5;
                series.appendData(new DataPoint(lastXPoint, rnd.nextDouble()*20), false, 200);
                if (lastXPoint == max)
                {
                    min += 25;
                    max += 25;
                }
                mygraph.getViewport().setMinX(min);
                mygraph.getViewport().setMaxX(max);
                addRandomDataPoint(mygraph);
            }
        }, 10);
    }
}