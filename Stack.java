import java.util.Iterator;
import java.util.ListIterator;

public class Stack<T> implements Iterable<T> {
    private Object[] array;
    private T top;

    public Stack() {
        array=new Object[10];
        top=null;       
    }

    public Stack(int size) {
        array=new Object[size];
        top=null;       
    }

    public void push(T value) {
        if(value!=null) {
            top=value;                
            for(int i=0;i<array.length;i++) {
                if(array[i]==null) {
                    array[i]=value;
                    return;
                }
            }   
        }   
    }

    public T pop() {
        for(int i=0;i<array.length;i++) {
            if(array[i]==top) {
                array[i]=null;
                T tmp=top;
                top=(T)array[i-1];
                return((T)tmp);
            }
        }
        return (T)top;
        
    }

    public T peek() {
        return (T)top;
    }

    public boolean isEmpty() {
        if(array[0]==null) {
            return true;
        }
        return false;
    }

    public String toString() {
        String result="[";
        for(int i = 0;i<array.length;i++) {
            result=result+array[i]+", ";
        }
        return result+"]";
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        Stack<String> a = new Stack<String>();
        a.push("hello");
        a.push("hello2");
        a.push("hello3");
        a.push("hello4");
        a.push("hello5");
        a.push("hello6");
        // System.out.println(a);
        // System.out.println(a.pop());
        // System.out.println(a.pop());
        // System.out.println(a.pop());
        // System.out.println(a);
        
        for(String s: a) {
            System.out.println(s);
        }
  
    }

    public class StackIterator implements Iterator<T> {
        private int index;
        
        public StackIterator() {
            index=0;
        }
       
        public boolean hasNext() {
            return index < array.length && array[index] != null;
        }

        
        public T next(){
            T value=(T)array[index];
            index++;
            return value;
        }

    }
}
