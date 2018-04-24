package org.sniffhu.core;

import org.sniffhu.model.Pipeline;
import org.sniffhu.model.Valve;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auth snifferhu
 * @date 2018/4/24 21:00
 */
public class PipelineExecutor<Input, Output> {
    private ExecutorService executor;

    private Integer size;

    private Pipeline pipeline;

    private Input input;

    PipelineExecutor() {

    }

    PipelineExecutor(Integer size) {
        if (size < 0) {
            throw new IllegalArgumentException("size can't less than 0");
        }
        executor = Executors.newFixedThreadPool(size);
    }

    public Integer getSize() {
        return size;
    }

    public PipelineExecutor setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public PipelineExecutor setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    Output exec() {
        Queue<Valve> valveList = pipeline.getValveList();
        Map content = new ConcurrentHashMap();
        for (Valve valve : valveList) {
            valve.exec(input, content);
        }
        return (Output) content.get("data");
    }

    Output parallelExec() {
        Queue<Valve> valveList = pipeline.getValveList();
        Map content = new ConcurrentHashMap();
        valveList.parallelStream().forEach(valve -> valve.exec(input,content));
        return (Output) content.get("data");
    }
}
