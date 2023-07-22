package 线性表;

/**
 * @title: demo01 线性表 基本操作和求并集
 * @Author zxwyhzy
 * @Date: 2023/7/13 15:41
 * @Version 1.0
 */
public class demo01 {
    public static void main(String[] args) {
        ListDemo<String> a = new ListDemo<>(10);
        ListDemo<String> b = new ListDemo<>(10);
        a.add("迪迦");
        a.add("泰罗");
        a.add("雷欧", 1);
       // a.print();

        b.add("迪迦");
        b.add("戴拿");
        // 求并集
        a.union(b);
        a.print();


    }

}

class ListDemo<T> {
    private Object[] vals;
    private int length = 0;

    public ListDemo(int count) {
        vals = new Object[count];
    }

    // 获取实际长度
    public int ListLength() {
        return this.length;
    }

    // 根据下标获取值
    public T getIndex(int index) {
        return (T) vals[index];
    }

    // 判断是否存在
    public boolean isExists(T val) {
        for (var v : vals) {
            if (null == v) continue;
            if (v.equals(val)) {
                return true;
            }
        }
        return false;
    }

    // 插入数据
    public boolean add(T val, int index) {
        if (index < 0 || index >= length){
            throw new RuntimeException("下标越界");
        }
        T temp = (T) vals[index];
        vals[index] = val;
        for (int i = index + 1; i < vals.length; i++) {
            T temp2 = (T) vals[i];
            vals[i] = temp;
            temp = temp2;
        }
        length++;
        return true;
    }

    public boolean add(T val) {
        vals[ListLength()] = val;
        length++;
        return true;
    }

    public boolean union(ListDemo listDemo) {
        for (var ld : listDemo.getVals()) {
            if (null == ld) continue;
            T temp = (T) ld;
            if (!this.isExists(temp)) {
                this.add(temp);
            }
        }
        return true;
    }

    public Object[] getVals() {
        return vals;
    }

    public void print() {
        for (var v : vals) {
            if (null != v) {
                System.out.println(v);
            }
        }
    }

}