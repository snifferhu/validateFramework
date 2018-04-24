package org.sniffhu.model;

import java.util.Map;

/**
 * @auth snifferhu
 * @date 2018/4/24 20:48
 */
public interface Valve<Input> {
    void exec(Input input,Map content);
}
