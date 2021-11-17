package ru.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        String str;
        Boolean stop = false;
        Boolean test = false;
        byte n = 0;
        byte i0 = 0, j0 = 0;    // координаты пустой клетки
        byte in = 0, jn = 0;    // координаты выбранной для перемещения клетки
        byte[][] field = new byte[4][4];
        // создадим коллекцию list из неповторяющихся чисел от 0 до 15, 0 считаем пустой клеткой
        ArrayList<Byte> list = new ArrayList<>();
        for (byte i = 0; i < 17; i++) {
            list.add(i);
        }
        // перемешаем коллекцию list
        Collections.shuffle(list);

        // заполним 2-мерного массива элементами перемешанной коллекции list
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 4; j++) {
                field[i][j] = list.get(i * 4 + j + 1);
            }
        }
        // остаемся в цикле, пока метод checkResult не вернет true
        while (!checkResult(field) && (!stop)) {
            for (byte i = 0; i < 4; i++) {
                System.out.println(Arrays.toString(field[i]));
            }
            System.out.println("Введите целое число от 1 до 15, которое хотите переместить или 'stop'");
            str = scanner.nextLine();
            if (str.equals("stop")) {
                System.out.println("Игра завершена");
                stop = true;
            } else {
                    try
                    {
                        n = Byte.parseByte(str);

                    } catch (NumberFormatException e) {
                        System.out.println("Введено не число");
                        test = true;
            }
                if ((!test) && (n > 16 | n < 1)) {
                    System.out.println("Введено некорректное число");
                } else {
                    for (byte i = 0; i < 4; i++) {
                        for (byte j = 0; j < 4; j++) {
                            // получаем координаты пустой клетки в текущем варианте массива
                            if (field[i][j] == 0) {
                                i0 = i;
                                j0 = j;
                            }
                            // получаем координаты клетки с введенным игроком числом
                            if (field[i][j] == n) {
                                in = i;
                                jn = j;
                            }
                        }
                    }
                    if ((!test)&&(!isNeighbor(i0, j0, in, jn))) {
                        System.out.println("Невозможно переместить это число");
                    } else { // меняем число с пустой клеткой (0)
                        field[i0][j0] = field[in][jn];
                        field[in][jn] = 0;
                    }
                }
            }
        }
    }
    // метод проверяет выполненость условия выигрыша
    public static boolean checkResult (byte [][] arr){
        for (byte i = 0; i < 4; i++){
            for (byte j = 0; j < 4; j++){
                if (arr[i][j] != i*4+j+1 && i*4+j+1 != 16) {
                    return false;
                }
            }
        }
        return true;
    }
    // метод проверяет, соседствует ли введеное число с пустой клеткой (0)
    public static boolean isNeighbor(byte i0, byte j0, byte ip, byte jp) {
        if (((i0 == ip) && (Math.abs(j0 - jp) == 1)) | ((j0 == jp) && (Math.abs(i0 - ip) == 1))){
            return true;
        }
        return false;
    }
}

