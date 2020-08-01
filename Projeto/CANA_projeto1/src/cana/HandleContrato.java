package cana;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HandleContrato {
    private ArrayList<ArrayList<Contrato>> fornecedores;
    private int mesesCount;
    private int fornecedoresCount;

    public HandleContrato(ArrayList<ArrayList<Contrato>> fornecedores, int mesesCount, int fornecedoresCount) {
        this.fornecedores = fornecedores;
        this.mesesCount = mesesCount;
        this.fornecedoresCount = fornecedoresCount;
    }

    Contrato menorValorByFornecedor(int fornecedor) {
        ArrayList<Contrato> contratosFornecedores = fornecedores.get(fornecedor - 1);

        float menorValor = contratosFornecedores.get(0).getValor();
        int indexMenor = 0;

        for (int i = 0; i < contratosFornecedores.size(); i++) {
           if (menorValor > contratosFornecedores.get(i).getValor()) {
               menorValor = contratosFornecedores.get(i).getValor();
               indexMenor = i;
           }
        }

        return contratosFornecedores.get(indexMenor);
    }

    Contrato menorValor() {
        int indexFornecedor = 0;
        int indexContrato = 0;
        float menorValor = fornecedores.get(0).get(0).getValor();

        for (int i = 0; i < fornecedores.size(); i++) {

            for (int j = 0; j < fornecedores.get(i).size(); j++) {
                if (menorValor > fornecedores.get(i).get(j).getValor()) {
                    menorValor = fornecedores.get(i).get(j).getValor();
                    indexFornecedor = i;
                    indexContrato = j;
                }
            }
        }

        return this.fornecedores.get(indexFornecedor).get(indexContrato);
    }

    Contrato menorValorByMes(int mes) {
        int indexFornecedor = 0;
        int indexContrato = 0;
        float menorValor = fornecedores.get(0).get(0).getValor();

        for (int i = 0; i < fornecedores.size(); i++) {
            for (int j = 0; j < fornecedores.get(i).size(); j++) {
                if (mes == fornecedores.get(i).get(j).getMesFinal()) {
                    if (menorValor > fornecedores.get(i).get(j).getValor()) {
                        menorValor = fornecedores.get(i).get(j).getValor();
                        indexFornecedor = i;
                        indexContrato = j;
                    }
                }
            }
        }

        return this.fornecedores.get(indexFornecedor).get(indexContrato);
    }

    List<Contrato> melhoresContratosPeriodo(int mes) {
        float menor = fornecedores.get(0).get(0).getValor();
        ArrayList<Contrato> listaContrato = new ArrayList<>();
        int fornecedor = 0;

        for (int i = 0; i < mes; i++) {
            for (int j = 0; j < fornecedores.size(); j++) {
                if (menor > fornecedores.get(j).get(i).getValor()) {
                    menor = fornecedores.get(j).get(i).getValor();
                    fornecedor = j;
                }
            }
            listaContrato.add(fornecedores.get(fornecedor).get(i));
        }
        return listaContrato;
    }
}
