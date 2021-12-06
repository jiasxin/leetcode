package com.jiasxin.leetcode.type.array.no00006;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: jiasx
 * @date: 2021年12月06日23:57:02
 * @description:
 * @updateUser:
 * @updateDate:
 * @updateDescription:
 */
public class Solution {

    /**
     * 先对数组进行排序
     * 先对两个数组进行排序，然后使用两个指针，分别指向两个数组开始的位置。
     * <p>
     * 如果两个指针指向的值相同，说明这个值是他们的交集，就把这个值加入到集合list中，然后两个指针在分别往后移一步。
     * 如果两个指针指向的值不同，那么指向的值相对小的往后移一步，相对大的先不动，然后再比较
     * 一直重复上面的操作，直到其中一个指针不能再移动为止，最后再把集合list转化为数组即可。
     * <p>
     * 作者：数据结构和算法
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/?discussion=miIug2
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // 先对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                // 如果i指向的值小于j指向的值，，说明i指向的值小了，i往后移一步
                i++;
            } else if (nums1[i] > nums2[j]) {
                // 如果i指向的值大于j指向的值，说明j指向的值小了，j往后移一步
                j++;
            } else {
                // 如果i和j指向的值相同，说明这两个值是重复的，把他加入到集合list中，然后i和j同时都往后移一步
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        //把list转化为数组
        int index = 0;
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[index++] = list.get(k);
        }
        return res;
    }

    /**
     * 使用map解决
     * <p>
     * 遍历nums1中的所有元素，把它存放到map中，其中key就是nums1中的元素，value就是这个元素在数组nums1中出现的次数。
     * <p>
     * 遍历nums2中的所有元素，查看map中是否包含nums2的元素，如果包含，就把当前值加入到集合list中，然后对应的value要减1。
     * <p>
     * 最后再把集合list转化为数组即可，
     * <p>
     * 作者：数据结构和算法
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/?discussion=miIug2
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        //先把数组nums1的所有元素都存放到map中，其中key是数组中的元素，value是这个元素出现在数组中的次数
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }

        //然后再遍历nums2数组，查看map中是否包含nums2的元素，如果包含，就把当前值加入到集合list中，然后再把对应的value值减1。
        for (int i = 0; i < nums2.length; i++) {
            if (map.getOrDefault(nums2[i], 0) > 0) {
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }

        //把集合list转化为数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 排序后双指针解决
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect3(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int len = len1 < len2 ? len1 : len2;
        int[] ans = new int[len];
        if (len1 == 0 || len2 == 0) {  //处理边界条件
            return ans;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                ans[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(ans, 0, k);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[]{1, 1, 2, 3, 3};
        int[] nums2 = new int[]{1, 1, 2, 3, 3};
        int[] ints = solution.intersect3(nums1, nums2);
        System.out.println(JSONUtil.toJsonStr(ints));
    }

}
