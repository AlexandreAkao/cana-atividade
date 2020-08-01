import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "a.txt";
        BufferedReader br;
        HandleContrato handleContrato;

        float[][][] fornecedores = null;

        int lineCount = 0;
        int mesesCount = 0;
        int fornecedoresCount = 0;
        int contratosCountPerFornecedor = 0;
        float taxa = 0;
        long tempoInicial = System.currentTimeMillis();

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO_8859_1"));

            while (br.ready()) {
                String line = br.readLine();

                if (lineCount == 0) {

                    mesesCount = Integer.parseInt(line.split(" ")[0]);
                    fornecedoresCount = Integer.parseInt(line.split(" ")[1]);
                    taxa = Float.parseFloat(line.split(" ")[2]);

                    fornecedores = new float[fornecedoresCount][mesesCount][];

                    contratosCountPerFornecedor = (1 + mesesCount) * mesesCount / 2;

                    int contratosSize = (1 + mesesCount) * mesesCount / 2 * fornecedoresCount;

                    for (int i = 0; i < fornecedores.length; i++) {
                        for (int j = 0; j < mesesCount; j++) {
                            fornecedores[i][j] = new float[mesesCount - j];
                        }
                    }

                } else {

                    String[] contratoInfo = line.split(" ");

                    fornecedores
                        [Integer.parseInt(contratoInfo[0]) - 1]
                        [Integer.parseInt(contratoInfo[1]) - 1]
                        [Integer.parseInt(contratoInfo[2]) - Integer.parseInt(contratoInfo[1])] = Float.parseFloat(contratoInfo[3]);
                }

                lineCount++;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        long tempoFinal = System.currentTimeMillis();

        System.out.println("Tempo de execução da leitura: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        handleContrato = new HandleContrato(fornecedores, mesesCount, fornecedoresCount, contratosCountPerFornecedor, taxa);

//        handleContrato.insertionSort();
//        handleContrato.mergeSort();
//        handleContrato.heapSort();
//        handleContrato.quickSort();
//        handleContrato.bestSort();
    }
}
