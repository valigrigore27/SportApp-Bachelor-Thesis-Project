package com.example.sport.models.food;

import java.util.List;

public class RecipeResponse {
    //nr total de retete care corespund cautarii
    private Integer count;
    private List<Hit> hits;

    public Integer getCount() {
        return count;

    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}
