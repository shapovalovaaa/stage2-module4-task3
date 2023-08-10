package com.mjc.stage2.impl;

import com.mjc.stage2.Observer;
import com.mjc.stage2.entity.Rectangle;
import com.mjc.stage2.entity.RectangleValues;
import com.mjc.stage2.event.RectangleEvent;
import com.mjc.stage2.warehouse.RectangleWarehouse;

public class RectangleObserver implements Observer {

    private final RectangleWarehouse rectangleWarehouse = RectangleWarehouse.getInstance();

    @Override
    public void handleEvent(RectangleEvent event) {
        Rectangle rectangle = event.getSource();
        RectangleValues newRectangleValues = new RectangleValues(findSquare(rectangle), findPerimeter(rectangle));

        rectangleWarehouse.put(rectangle.getId(), newRectangleValues);
    }

    private double findSquare(Rectangle rectangle) {
        return rectangle.getSideA() * rectangle.getSideB();
    }

    private double findPerimeter(Rectangle rectangle) {
        return (rectangle.getSideA() + rectangle.getSideB()) * 2;
    }
}