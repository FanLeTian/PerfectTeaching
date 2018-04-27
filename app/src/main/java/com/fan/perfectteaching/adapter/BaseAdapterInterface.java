package com.fan.perfectteaching.adapter;

import java.util.List;

public interface BaseAdapterInterface<T> {

    void concatItems(List<T> items);

    void refreshItems(List<T> items);

}
