package br.com.ejb.projetomobile.model;

import com.orm.SugarRecord;

public class Value extends SugarRecord {
    private long x;
    private double y;

    public Value() {
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
