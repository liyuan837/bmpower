package com.liyuan.bmpower.util;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:LiYuan
 * @description:树形结构
 * @Date:Create in 19:11 2018/9/19
 * @Modified By:
 */
public class TreeContainer<T extends Tree> implements Serializable {
    private T tree;
    private List<TreeContainer<T>> children;

    public TreeContainer(T tree, List<TreeContainer<T>> children) {
        this.tree = tree;
        this.children = children;
    }

    public TreeContainer() {
    }

    public TreeContainer(T tree) {
        this.tree = tree;
    }

    public T getTree() {
        return this.tree;
    }

    public void setTree(T tree) {
        this.tree = tree;
    }

    public List<TreeContainer<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<TreeContainer<T>> children) {
        this.children = children;
    }
}
