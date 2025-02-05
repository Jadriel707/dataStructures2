import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;

    public LinkedList() {
        head=null;
    }
    
    public void add(T value) {
        if(head==null) {
            head=new Node<T>(value);
        } else {
            Node<T> node=head;
            while(node.next!=null) {
                node=node.next;
            }
            node.next=new Node<T>(value);
        }
        
    }

    // public void add(T value, int index) {
    //     if(head!=null) {
    //         Node<T> node=head;
    //         for(int k=0;k<index-1;k++) {
    //             node=node.next;
    //             if(node==null)
    //                 return;
    //         }
    //         tmp=node
    //     }
    // }

    public void addFirst(T value) {
        if(head==null) 
            head=new Node<T>(value);
        else {
            Node<T> temp=head;
            head=new Node<T>(value);
            head.next=temp;
        }

    }
    public void clear() {
        head=null;
    }

    public boolean contains(T value) {
        if(head!=null) {
            Node<T> node=head;
            while(node.next!=null) {
                if(node.value.equals(value)) 
                    return true;
                node=node.next;
            }
        }
        return false;
    }

    public T getFirst() {
        return head.value;
    }
    public T getLast() {
        if(head!=null) {
            Node<T> node=head;
            while(node.next!=null) {
                node=node.next;
            }
            return node.value;
        }
        return null;
    }

    

    public Iterator<T> iterator() {
      return new LinkedIterator(this);
    }

    public class Node<T> {
        private T value;
        private Node<T> next;
    
        public Node(T value) {
            this.value=value;
        }
    }

    public class LinkedIterator implements Iterator<T> {
        Node<T> current;

        public LinkedIterator(LinkedList<T> list) {
            current=head;
        }

        
        public boolean hasNext() {
           return current!=null;
        }

        
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }
        
    }

    public static void main(String[] args) {
        LinkedList<String> words = new LinkedList<>();
        words.add("One");
        words.add("Two");
        words.add("Three");
        words.add("Four");

        for(String word: words) {
            System.out.println(word);
        }

    }



}