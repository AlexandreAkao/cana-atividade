package cana;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "entrada.txt";
        BufferedReader br;
        HandleContrato handleContrato;

        ArrayList<ArrayList<Contrato>> fornecedores = new ArrayList<>();

        int lineCount = 0;
        int meses = 0;
        int fornecedoresCount = 0;
        long tempoInicial = System.currentTimeMillis();

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO_8859_1"));

            while (br.ready()) {
                if (lineCount == 0) {
                    String firstLine = br.readLine();

                    meses = Integer.parseInt(firstLine.split(" ")[0]);
                    fornecedoresCount = Integer.parseInt(firstLine.split(" ")[1]);

                    for (int i = 0; i < fornecedoresCount; i++) {
                        fornecedores.add(new ArrayList<>());
                    }

                } else {
                    String line = br.readLine();
                    Contrato contrato = new Contrato(line.split(" "));

                    fornecedores.get(contrato.getFornecedor() - 1).add(contrato);
                }

                lineCount++;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }

        System.out.println(1);
        handleContrato = new HandleContrato(fornecedores, meses, fornecedoresCount);
        System.out.println(handleContrato.menorValorByFornecedor(1));
        long tempoFinal = System.currentTimeMillis();

        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) / 1000f);

    }
}
