package com.validity.ishita.service;

public interface MetricStringDistance extends StringDistance {

    /**
     * Compute and return the metric distance.
     * @param s1
     * @param s2
     * @return
     */
    double distance(String s1, String s2);
}
