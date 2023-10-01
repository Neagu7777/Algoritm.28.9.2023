import java.util.Stack;
/*
 Реализовать структуру данных SpecialStack, которая поддерживает все операции со стеком,
  такие как push(), pop(), isEmpty(), … и дополнительную операцию getMin(),
  которая должна возвращать минимальный элемент из SpecialStack.

При этом взаимодействия со стеком в функции getMin() должно происходить через push и pop,
 а также после того как функция getMin() найдет минимальный элемент нужно восстановить
 стек в изначальное состояние (например у вас был стек и так как вы перебирали все
 элементы в нем через pop вы изменяли его ибо pop удаляет при доставании элемент из стека,
  поэтому в процессе перебирания вам необходимо достанные элементы куда-то сохранять и
  после того как вы все переберете и найдете минимальный элемент вам нужно вернуть все
  значения на свои места в стеке)
 */

class SpecialStack {
    public static void main(String[] args) {
    }

    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public SpecialStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        mainStack.push(value);

        // Проверяем, является ли новое значение минимальным
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        if (!mainStack.isEmpty()) {
            int poppedValue = mainStack.pop();

            // Если вытолкнули минимальное значение, удаляем его из minStack
            if (poppedValue == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        if (!mainStack.isEmpty()) {
            return mainStack.peek();
        }
        throw new IllegalStateException("Стек пуст.");
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            int minValue = minStack.peek();

            // Восстанавливаем mainStack после поиска минимального значения
            Stack<Integer> tempStack = new Stack<>();
            while (!mainStack.isEmpty()) {
                tempStack.push(mainStack.pop());
            }
            while (!tempStack.isEmpty()) {
                mainStack.push(tempStack.pop());
            }

            return minValue;
        }
        throw new IllegalStateException("Стек пуст.");
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }
}