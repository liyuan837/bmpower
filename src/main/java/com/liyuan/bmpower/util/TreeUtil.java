package com.liyuan.bmpower.util;

import com.liyuan.bmpower.vo.power.PowerVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caowei
 * @date 2017/8/4
 */
/**
 * @author caowei
 * @date 2017/8/4
 */
public class TreeUtil {

    /**
     * 树状化根
     *
     * @param treeList
     * @param <T>
     * @return
     */
    public static <T extends Tree> TreeContainer<T> treeFormat(List<T> treeList) {

        TreeContainer<T> root = null;
        Map<Object, List<TreeContainer<T>>> contentMap = new HashMap<>();
        for (T tree : treeList) {
            if (tree.getParentId() == null) {
                root = new TreeContainer<>(tree);
            } else {
                List<TreeContainer<T>> list = contentMap.getOrDefault(tree.getParentId(), new ArrayList<>());
                TreeContainer<T> treeContainer = new TreeContainer<>(tree);
                list.add(treeContainer);
                contentMap.putIfAbsent(tree.getParentId(), list);
            }
        }

        if (root == null) {
            throw new IllegalArgumentException("找不到根");
        }

        // 获取所有子对象
        root.setChildren(queryAllChildren(contentMap, root.getTree().getId()));
        return root;
    }

    /**
     * 树状化List
     *
     * @param treeList
     * @param <T>
     * @return
     */
    public static <T extends Tree> List<TreeContainer<T>> treeFormatList(List<T> treeList, Object rootId) {

        Map<Object, List<TreeContainer<T>>> contentMap = new HashMap<>();
        treeList.forEach(tree -> {
            List<TreeContainer<T>> list = contentMap.getOrDefault(tree.getParentId(), new ArrayList<>());
            TreeContainer<T> treeContainer = new TreeContainer<>(tree);
            list.add(treeContainer);
            contentMap.putIfAbsent(tree.getParentId(), list);
        });

        // 获取所有子对象
        return queryAllChildren(contentMap, rootId);
    }

    public static <T extends Tree> List<T> findSubList(List<T> list, Object levelId){
        List<T> subList = new ArrayList<>();
        list.forEach(t -> {
            if(levelId.equals(t.getParentId())){
                subList.add(t);
                subList.addAll(findSubList(list,t.getId()));
            }
        });
        return subList;
    }

    /**
     * 获取父编号下面所有的子对象
     *
     * @param contentMap
     * @param parentId
     * @param <T>
     * @return
     */
    private static <T extends Tree> List<TreeContainer<T>> queryAllChildren(Map<Object, List<TreeContainer<T>>> contentMap, Object parentId) {
        List<TreeContainer<T>> children = contentMap.getOrDefault(parentId, new ArrayList<>());
        children.forEach(tree -> {
            List<TreeContainer<T>> list = new ArrayList<>();
            list.addAll(queryAllChildren(contentMap, tree.getTree().getId()));
            tree.setChildren(list);
        });

        return children;
    }

}
