package com.aston.landmarks.components;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Order;
import static org.springframework.data.domain.Sort.unsorted;

@Component
public class SortCreatorImpl implements SortCreator {

    @Override
    public Sort create(List<String> data) {
        if (data.isEmpty()) {
            return unsorted();
        }
        List<Order> orders = new ArrayList<>();

        if (data.get(0).contains(",")) {
            processEachOrder(orders, data);
        } else {
            processOneOrder(orders, data);
        }
        return Sort.by(orders);
    }

    private void processEachOrder(List<Order> orders, List<String> data) {
        int pair = 2;

        for (String text : data) {
            String[] prototype = text.split(",");
            if (prototype.length < pair) {
                if (prototype[0].isEmpty()) {
                    return;
                }
                orders.add(Order.desc(prototype[0]));
            } else {
                orders.add(getOrder(prototype[1], prototype[0]));
            }
        }
    }

    private void processOneOrder(List<Order> orders, List<String> data) {
        int pair = 2;

        if (data.size() < pair) {
            if (data.get(0).isEmpty()) {
                return;
            }
            orders.add(Order.desc(data.get(0)));
            return;
        }
        orders.add(getOrder(data.get(1), data.get(0)));
    }

    private Order getOrder(String direction, String property) {
        return (direction.equalsIgnoreCase("asc")) ? Order.asc(property) : Order.desc(property);
    }
}