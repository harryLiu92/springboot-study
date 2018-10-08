package com.home.web.utils;

import com.google.common.collect.Lists;
import com.home.web.logger.BaseService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/2/24 15:20
 * @Description:
 * Pair<IdentityId, LogInfo>
 **/
public class IdentityIdHolder extends BaseService {
    private static ThreadLocal<List<String>> holder = new ThreadLocal<List<String>>();

    /**
     * 强制更新
     */
    public static void setHolder(List<String> list) {
        if (CollectionUtil.isEmpty(list)) list = Lists.newArrayList();
        holder.set(list);
    }

    public static List<String> getHolder() {
        return holder.get();
    }

//    /**
//     * isBlank更新id
//     * @param identityId
//     */
//    public static void setId(String identityId) {
//        try {
//            List<String> list = holder.get();
//            if (CollectionUtil.isEmpty(list)) {
//                list.add(identityId);
//                holder.set(list);
//            } else {
//                if (list.size() > 1 && StringUtils.isBlank(list.get(0))) {
//                    list.set(0, identityId);
//                    holder.set(list);
//                }
//            }
//        } catch (Throwable t) {}
//    }

    public static String getId() {
        String identityId = ObjectId.getIdentityId();
        try {
            List<String> list = holder.get();
            if (CollectionUtil.isEmpty(list)) {
                list = Lists.newArrayList();
                list.add(identityId);
                holder.set(list);
            } else {
                if (list.size() > 1 && StringUtils.isBlank(list.get(0))) {
                    list.set(0, identityId);
                    holder.set(list);
                }
                identityId = list.get(0);
            }
        } catch (Throwable t) {}
        return identityId;
    }

    /**
     * 追加
     * @param name
     */
    public static int appendLog(String name) {
        int i = 1;
        try {
            getId();
            List<String> list = holder.get();
            i = list.size();
            list.add(name);
        } catch (Throwable t) {}
        return i;
    }

    /**
     * 删除全部
     */
    public static void removeAll() {
        holder.set(null);
    }

    /**
     * 只留identity
     */
    public static void removeLogName() {
        try {
            List<String> list = holder.get();
            if (CollectionUtil.isEmpty(list)) {
                return ;
            }
            List<String> newList = Arrays.asList(list.get(0));
            holder.set(newList);
        } catch (Throwable t) {}
    }

    /**
     * 删除指定下标>0
     */
    public static void removeIndex(int index) {
        try {
            List<String> list = holder.get();
            if (CollectionUtil.isEmpty(list) || index < 1 || list.size() <= index) {
                return ;
            }
            list.remove(index);
        } catch (Throwable t) {}
    }

    /**
     * 删除最后追加的数据
     */
    public static void removePeak() {
        try {
            List<String> list = holder.get();
            if (CollectionUtil.isEmpty(list)) {
                return ;
            }
            if (list.size() > 1) {
                list.remove(list.size() - 1);
            }
        } catch (Throwable t) {}
    }

    public static String getLog() {
        List<String> list = Lists.newArrayList();
        try {
            getId();
            list.addAll(holder.get());
        } catch (Throwable t) {}
        return concatLog(list);
    }

    private static String concatLog(List<String> list) {
        StringBuilder log = new StringBuilder();

        try {
            for (String str : list) {
                log.append("[").append(str).append("]-");
            }
        } catch (Throwable t) { }

        return log.toString();
    }
}

