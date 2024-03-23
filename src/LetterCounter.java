import java.io.*;
import java.util.Scanner;

public class LetterCounter {

    int[] counter = new int[128];       //Массив для хранения повторений буквы, где индекс - её ASCII код

    /**
     * main запрашивает имена входного и выходного файлов
     * вызывает метод Reader для входного файла
     * вызывает метод Writer для выходного файла
     */
    public static void main(String[] args) {
        String infile = "";
        String outfile = "";
        LetterCounter LetCounter = new LetterCounter();
        Reader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(inputStreamReader);

        try {
            System.out.print("Введите имя входного файла\n");
            infile = buf.readLine();
        } catch (IOException Ex1) {
            System.out.print("Ошибка ввода\n");
        }

        /*
         * Если возникла ошибка при чтении входного файла, программа завершает работу
         */
        if (LetCounter.Reader(infile) == 1){ return;
        }

        try {
            System.out.print("Введите имя выходного файла\n");
            outfile = buf.readLine();
        } catch (IOException Ex2) {
            System.out.print("Ошибка ввода\n");
        }

        LetCounter.Writer(outfile);
    }

    /**
     * метод Reader открывает файл для чтения
     * Подсчитывает сколько раз встречается каждый символ в файле
     * @param filename имя файла для чтения
     * @return возвращает 1, если возникла ошибка
     */
    private int Reader(String filename){
        try {
            FileReader fr = new FileReader(filename);
            Scanner scan = new Scanner(fr);

            /*
            * Файл построчно считывается
            * количество повторений буквы записывается в массив по ASCII коду
            */
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                for (int j = 0; j < line.length(); j++) {
                    int id = line.charAt(j);                    //Получаение ASCII кода символа
                    counter[id]++;
                }
            }

            fr.close();
            return 0;
        } catch (FileNotFoundException Ex1) {
            System.out.print(filename + " не найден\n");
            return 1;
        } catch (IOException Ex2) {
            System.out.print("Ошибка\n");
            return 1;
        }
    }

    /**
     * метод Writer открывает файл для записи
     * записывает количество повторений для каждой буквы
     * @param filename имя файла для записи
     */
    private void Writer(String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 65; i < 91; i++) {                     //Запись количества повторений A-Z
                char letter = (char) i;                         //ASCII код символа
                fw.write(letter + ": " + counter[i] + "\n");
            }

            for (int k = 97; k < 123; k++) {                    //Запись количества повторений a-z
                char letter = (char) k;                         //ASCII код символа
                fw.write(letter + ": " + counter[k] + "\n");
            }

            fw.close();
        } catch (IOException Ex2) {
            System.out.print("Ошибка открытия файла для записи\n");
        }
    }
}
