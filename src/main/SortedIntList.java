package main;

public class SortedIntList {

    private SortedIntList next = null;
    private SortedIntList prev = null;

    private Integer value = null;

    /**
     * Empty constructor
     */
    public SortedIntList() {

    }

    /**
     * Constructor to link in at the end of the list
     * @param prev previous node
     */
    public SortedIntList(SortedIntList prev) {
        this.prev = prev;
    }

    /**
     * Adds a new value to the list, at the correctly sorted position
     * @param e element to be added
     */
    public void add(int e) {
        // add element here if list is empty
        if (value == null) {
            value = e;
            return;
        }

        // Add element here if the current position fits to the element
        if (e <= value) {
            SortedIntList element = new SortedIntList(this);    // element to be added

            if (prev == null && next == null) {                      // there is just the current node in the list
                element.value = value;
                value = e;
                next = element;
                element.setPrevElement(this);
            } else if (prev == null) {                               // the current node is the root node
                element.setNextElement(next);
                next.setPrevElement(element);
                next = element;
                element.set(0, value);
                value = e;
            } else {                                                 // the current node is the last node
                element.set(0, e);
                element.setNextElement(this);
                element.setPrevElement(prev);
                prev.setNextElement(element);
            }
            return;
        }

        // pass element to next node if existing
        if (next == null) {
            next = new SortedIntList(this);
            next.add(e);
        } else {
            next.add(e);
        }
    }

    /**
     * Set the next node to continue the list
     * @param e Next node element.
     */
    private void setNextElement(SortedIntList e) {
        next = e;
    }

    /**
     * Set the previous node of the list
     * @param e Previous node element
     */
    private void setPrevElement(SortedIntList e) {
        prev = e;
    }

    /**
     * Set the value e to position id
     * @param id position in the list
     * @param e replacement value
     */
    @Deprecated
    public void set(int id, int e) {
        if (id == 0) {
            value = e;
        } else {
            next.set(--id, e);
        }
    }

    /**
     *
     * @param id Position of the element
     * @return Value at specified position
     */
    public int get(int id) {
        if (id == 0) {
            return value;
        } else {
            return next.get(--id);
        }
    }

    /**
     * @return Next element of the list
     */
    private SortedIntList getNextElement() {
        return next;
    }

    /**
     * @return Previous element of the list
     */
    private SortedIntList getPrevElement() {
        return prev;
    }

    /**
     *
     * @return Returns the list as string representation
     */
    public String print() {
        StringBuilder list = new StringBuilder();
        list.append("[");
        print(list);
        return list.toString();
    }

    /**
     * Adds string representation for each node to the list.
     * @param list Previous string as StringBuilder
     */
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

    /**
     *
     * @return Returns if list is empty
     */
    public boolean isEmpty() {
        return next == null && prev == null;
    }

    /**
     *
     * @return Returns the size of the list
     */
    public int size() {
        if (value == null) {
            return size(0);
        } else {
            return size(1);
        }
    }

    /**
     * Calculates the size of the list recursive
     * @param i
     * @return
     */
    private int size(int i) {
        if (next == null) {
            return i;
        } else {
            return next.size(++i);
        }
    }

    /**
     * Empty the list
     */
    public void clear() {
        next = null;
        value = null;
    }

    /**
     * Finds the first appearance of the given number.
     * @param num Number to be found.
     * @return Returns the first index of the given number. If the number isn't in the list, returns -1.
     */
    public int indexOf(int num) {
        return indexOf(num, 0);
    }

    /**
     * Finds recursive the first index of the given number.
     * @param num
     * @param i
     * @return
     */
    private int indexOf(int num, int i) {
        if (value == (Integer) num) {
            return i;
        }

        if (next != null) {
            return next.indexOf(num, ++i);
        }

        return -1;
    }

    /**
     * Finds the last appearance of the given Number.
     * @param num Number to be found.
     * @return Retruns the last index of the given number. If the number isn't in the list, returns -1.
     */
    public int lastIndexOf(int num) {
        return lastIndexOf(num, -1, 0);
    }

    /**
     * Finds the last index of the given number recursively.
     * @param num
     * @param last
     * @param i
     * @return
     */
    private int lastIndexOf(int num, int last, int i) {
        if (value == (Integer) num) {
            last = i;
        }

        if (next == null) {
            return last;
        } else {
            return next.lastIndexOf(num, last, ++i);
        }
    }

    /**
     * Removes the first appearance of the given number.
     * @param num Number to be removed
     * @return True when sucess. False when failed.
     */
    public boolean remove(int num) {
        int index = indexOf(num);
        if (index == -1) {
            return false;
        }

        return removeIndex(index);
    }

    /**
     * Removes the element at the given index.
     * @param i Index to be removed.
     * @return True when success. False when failed.
     */
    public boolean removeIndex(int i) {
        if (i == 0) {
            if (prev == null && next == null) {             // singe root node
                if (value == null) {                        // empty list
                    return false;
                } else {
                    value = null;
                }
            } else if (prev == null) {                      // is root node with following nodes
                value = next.get(0);
                next = next.getNextElement();
                if (next != null) next.setPrevElement(this);
            } else if (next == null) {                      // last node in list
                prev.setNextElement(null);
            } else {
                prev.setNextElement(next);
                next.setPrevElement(prev);
            }
            return true;
        } else {
            if (next != null) {
                return next.removeIndex(--i);
            }
        }

        return false;
    }

    /**
     * Check if a number appears in the list.
     * @param num Number to be found
     * @return True when the number is inside the list, False when the number isn't in the list.
     */
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
