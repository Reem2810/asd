package nl.han.asd;


public class DequeOfCircularArray<T> implements IDeque<T> {
        private static final int DEFAULT_CAPACITY = 8;
        private T[] elements;
        private int head;
        private int tail;
        private int size;

        /**
         * Constructs an empty deque with the default initial capacity.
         */
        @SuppressWarnings("unchecked")
        public DequeOfCircularArray() {
            elements = (T[]) new Object[DEFAULT_CAPACITY];
            head = 0;
            tail = 0;
            size = 0;
        }

        /**
         * Constructs an empty deque with the specified initial capacity.
         *
         * @param initialCapacity the initial capacity of the deque
         */
        @SuppressWarnings("unchecked")
        public DequeOfCircularArray(int initialCapacity) {
            if (initialCapacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero.");
            }
            elements = (T[]) new Object[initialCapacity];
            head = 0;
            tail = 0;
            size = 0;
        }

        @Override
        public void InsertLeft(T element) {
            ensureCapacity();
            head = (head - 1 + elements.length) % elements.length;
            elements[head] = element;
            size++;
        }

        @Override
        public void InsertRight(T element) {
            ensureCapacity();
            elements[tail] = element;
            tail = (tail + 1) % elements.length;
            size++;
        }

        @Override
        public T DeleteLeft() {
            if (isEmpty()) {
                return null;
            }
            T element = elements[head];
            elements[head] = null; // Help garbage collection
            head = (head + 1) % elements.length;
            size--;
            return element;
        }

        @Override
        public T DeleteRight() {
            if (isEmpty()) {
                return null;
            }
            tail = (tail - 1 + elements.length) % elements.length;
            T element = elements[tail];
            elements[tail] = null; // Help garbage collection
            size--;
            return element;
        }

        @Override
        public int size() {
            return size;
        }

        /**
         * Checks whether the deque is empty.
         *
         * @return true if the deque is empty, false otherwise
         */
        private boolean isEmpty() {
            return size == 0;
        }

        /**
         * Ensures that the underlying array has sufficient capacity to add new elements.
         * If the array is full, it resizes by doubling its current capacity.
         */
        @SuppressWarnings("unchecked")
        private void ensureCapacity() {
            if (size == elements.length) {
                int newCapacity = elements.length * 2;
                T[] newElements = (T[]) new Object[newCapacity];

                // Copy elements to the new array starting from head
                for (int i = 0; i < size; i++) {
                    newElements[i] = elements[(head + i) % elements.length];
                }

                elements = newElements;
                head = 0;
                tail = size;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CircularArrayDeque: [");
            for (int i = 0; i < size; i++) {
                sb.append(elements[(head + i) % elements.length]);
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
