package com.yoj.collections.level3.part5.strange_mistake;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class RealBean {
    protected final int id;
    protected final String name;


    protected Map<String, Object> additionalMap = new HashMap<>();

    @JsonCreator
    public RealBean(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalMap() {
        return additionalMap;
    }

    @JsonAnySetter
    public void setAdditionalMap(String name, Object value) {
        additionalMap.put(name, value);
    }


}
