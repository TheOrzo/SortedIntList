public class SortedIntList {

    private SortedIntList next = null;
    private SortedIntList prev = null;

    private Integer value = null;

    public SortedIntList() {

    }

    public SortedIntList(SortedIntList prev) {
        this.prev = prev;
    }


    public void add(int e) {
        if (value == null) {
            value = e;
            return;
        }

        if (e < value) {
            SortedIntList element = new SortedIntList(this);

            if (prev == null) {
                element.setNextElement(next);
                next.setPrevElement(element);
                next = element;
                element.set(0, value);
                value = e;
            } else {
                element.set(0, e);
                element.setNextElement(this);
                element.setPrevElement(prev);
                prev.setNextElement(element);
            }
            return;
        }

        if (next == null) {
            next = new SortedIntList(this);
            next.add(e);
        } else {
            next.add(e);
        }
    }

    public void setNextElement(SortedIntList e) {
        next = e;
    }

    public void setPrevElement(SortedIntList e) {
        prev = e;
    }

    public void set(int id, int e) {
        if (id == 0) {
            value = e;
        } else {
            next.set(--id, e);
        }
    }

    public int get(int id) {
        if (id == 0) {
            return value;
        } else {
            return next.get(--id);
        }
    }

    private SortedIntList getNextElement() {
        return next;
    }

    public String print() {
        StringBuilder list = new StringBuilder();
        list.append("[");
        print(list);
        return list.toString();
    }

    private void print(StringBuilder list) {
        if (value != null) {
            list.append(value);
        }

        if (next == null) {
            list.append("]");
        } else {
            list.append(",");
            next.print(list);
        }
    }

    public boolean isEmpty() {
        return next == null && prev == null;
    }

    public int size() {
        if (value == null) {
            return size(0);
        } else {
            return size(1);
        }
    }

    private int size(int i) {
        if (next == null) {
            return i;
        } else {
            return next.size(++i);
        }
    }

    public void clear() {
        next = null;
        value = null;
    }

    public int indexOf(int num) {
        return indexOf(num, 0);
    }

    private int indexOf(int num, int i) {
        if (value == num) {
            return i;
        }

        if (next != null) {
            return next.indexOf(num, ++i);
        }

        return -1;
    }

    public int lastIndexOf(int num) {
        return lastIndexOf(num, -1, 0);
    }

    private int lastIndexOf(int num, int last, int i) {
        if (value == num) {
            last = i;
        }

        if (next == null) {
            return last;
        } else {
            return next.lastIndexOf(num, last, ++i);
        }
    }

    public void remove(int i) {
        if (i == 0) {
            if (prev == null) {
                value = next.get(0);
                next = next.getNextElement();
            } else {
                prev.setNextElement(next);
                next.setPrevElement(prev);
            }
        } else {
            if (next != null) {
                next.remove(--i);
            }
        }
    }

    public boolean contains(int num) {
        if (value == num) {
            return true;
        }

        if (next == null) {
            return false;
        } else {
            return next.contains(num);
        }
    }
}
