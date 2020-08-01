package cana;

import java.util.Arrays;

public class HandleContrato {
    private float[][][] fornecedores;
    private Contrato[] contratos;
    private int mesesCount;
    private int fornecedoresCount;
    private int contratosCountPerFornecedor;

    public HandleContrato(float[][][] fornecedores, Contrato[] contratos, int mesesCount, int fornecedoresCount, int contratosCountPerFornecedor) {
        this.fornecedores = fornecedores;
        this.contratos = contratos;
        this.mesesCount = mesesCount;
        this.fornecedoresCount = fornecedoresCount;
        this.contratosCountPerFornecedor = contratosCountPerFornecedor;
    }

    //Θ (n² * m) -> Melhor caso
    //Θ(n^4 * m²) -> Pior caso
    public Contrato[] insertionSort() {
        Contrato[] clone = this.contratos.clone();

        long tempoInicial = System.currentTimeMillis();

        Contrato aux;
        int j;

        for (int i = 1; i < clone.length; i++) {
            aux = clone[i];
            j = i;

            while (j > 0 && clone[j - 1].getValor() > aux.getValor()) {
                clone[j] = clone[j - 1];
                j--;
            }

            clone[j] = aux;
        }

        long tempoFinal = System.currentTimeMillis();

        System.out.println("insertionSort: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        return clone;
    }

    public Contrato[] mergeSort() {
        Contrato[] clone = this.contratos.clone();

        long tempoInicial = System.currentTimeMillis();

        this.mergeSort(clone, 0, clone.length - 1);

        long tempoFinal = System.currentTimeMillis();
        System.out.println("mergeSort: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        return clone;
    }

    public Contrato[] heapSort() {

        Contrato[] clone = this.contratos.clone();
        long tempoInicial = System.currentTimeMillis();

        int size = clone.length;

        int startIndex = (size / 2) - 1;

        for (int i = startIndex; i >= 0; i--) {
            heapify(clone, size, i);
        }

        for (int i = size - 1; i >= 0; i--) {
            Contrato tmp = clone[0];
            clone[0] = clone[i];
            clone[i] = tmp;

            heapify(clone, i, 0);
        }

        long tempoFinal = System.currentTimeMillis();
        System.out.println("heapSort: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        return clone;
    }

    public Contrato[] quickSort() {
        Contrato[] clone = this.contratos.clone();
        long tempoInicial = System.currentTimeMillis();

        int size = contratos.length;

        this.quickSort(clone, 0, size - 1);

        long tempoFinal = System.currentTimeMillis();
        System.out.println("quickSort: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        return clone;
    }

    //Θ(n² * m lg(n² * m))
    public float[] bestSort() {
        long tempoInicial = System.currentTimeMillis();

        float[] sort = new float[this.fornecedoresCount * this.contratosCountPerFornecedor];

        Contrato[] fornecedoresHeap = new Contrato[this.fornecedoresCount * this.mesesCount];

        int index = 0;

        for (int i = 0; i < fornecedoresCount; i++) {
            for (int j = 0; j < mesesCount; j++) {
                fornecedoresHeap[index] = new Contrato(i, j + 1, j + 1, fornecedores[i][j][0]);
                index++;
            }
        }

        int size = fornecedoresHeap.length;

        int startIndex = (size / 2) - 1;

        for (int j = startIndex; j >= 0; j--) {
            buildMinHeap(fornecedoresHeap, size, j);
        }

        for (int i = 0; i < sort.length; i++) {
            sort[i] = fornecedoresHeap[0].getValor();

            int fornecedor = fornecedoresHeap[0].getFornecedor();
            int mesInicial = fornecedoresHeap[0].getMesInicio();
            int mesFinal = fornecedoresHeap[0].getMesFinal();

            if (mesFinal == mesesCount) {
                fornecedoresHeap[0] = new Contrato(-1, -1, -1, Float.MAX_VALUE);
            } else {
                fornecedoresHeap[0] = new Contrato(fornecedor, mesInicial, mesFinal + 1, fornecedores[fornecedor][mesInicial - 1][mesFinal - mesInicial + 1]);
            }

            buildMinHeap(fornecedoresHeap, size, 0);
        }

        long tempoFinal = System.currentTimeMillis();

        System.out.println("bestSort: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        return sort;
    }

    // Θ(n² * m lg(n² * m))
    //T(N) = A * T(N/B) + F(N)
    private void mergeSort(Contrato[] contratos, int inicio, int fim) {

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(contratos, inicio, meio);
            mergeSort(contratos, meio + 1, fim);
            auxMergeSort(contratos, inicio, meio, fim);
        }
    }

    private void auxMergeSort(Contrato[] contratos, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        Contrato[] L = new Contrato[n1 + 1];
        Contrato[] R = new Contrato[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = contratos[inicio + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = contratos[meio + j + 1];
        }

        L[n1] = new Contrato(Float.MAX_VALUE);
        R[n2] = new Contrato(Float.MAX_VALUE);

        int vetorEsquerda = 0;
        int vetorDireita = 0;

        for (int k = inicio; k <= fim; k++) {

            if (L[vetorEsquerda].getValor() <= R[vetorDireita].getValor()) {
                contratos[k] = L[vetorEsquerda++];
            } else {
                contratos[k] = R[vetorDireita++];
            }
        }
    }

    private void heapify(Contrato[] contratos, int size, int i) {
        int l = (2 * i) + 1;
        int r = (2 * i) + 2;

        int maior = i;

        if (l < size && contratos[l].getValor() > contratos[maior].getValor()) {
            maior = l;
        }

        if (r < size && contratos[r].getValor() > contratos[maior].getValor()) {
            maior = r;
        }

        if (maior != i) {

            Contrato tmp = contratos[i];
            contratos[i] = contratos[maior];
            contratos[maior] = tmp;

            heapify(contratos, size, maior);
        }
    }

    private int partition(Contrato[] contratos, int begin, int end) {
        Contrato pivot = contratos[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (contratos[j].getValor() < pivot.getValor()) {
                i++;

                Contrato temp = contratos[i];
                contratos[i] = contratos[j];
                contratos[j] = temp;
            }
        }
        Contrato temp = contratos[i + 1];
        contratos[i + 1] = contratos[end];
        contratos[end] = temp;

        return i + 1;
    }

    //Θ(n^4 * m²) -> Pior caso
    //Θ(n² * m log(n² * m)) -> Melhor caso
    private void quickSort(Contrato[] contratos, int begin, int end) {
        if (begin < end) {

            int index = partition(contratos, begin, end);

            quickSort(contratos, begin, index - 1);
            quickSort(contratos, index + 1, end);
        }
    }

    public float[] mergeArray(float[] arr1, float[] arr2) {
        int arr1Size = arr1.length;
        int arr2Size = arr2.length;

        float[] newArray = new float[arr1Size + arr2Size];

        int vetorEsquerda = 0;
        int vetorDireita = 0;

        int k = 0;

        while (vetorEsquerda < arr1Size && vetorDireita < arr2Size) {
            if (arr1[vetorEsquerda] <= arr2[vetorDireita]) {
                newArray[k] = arr1[vetorEsquerda];
                vetorEsquerda++;
            } else {
                newArray[k] = arr2[vetorDireita];
                vetorDireita++;
            }
            k++;
        }

        while (vetorEsquerda < arr1Size) {
            newArray[k] = arr1[vetorEsquerda];
            vetorEsquerda++;
            k++;
        }

        while (vetorDireita < arr2Size) {
            newArray[k] = arr2[vetorDireita];
            vetorDireita++;
            k++;
        }

        return newArray;
    }

    private void buildMinHeap(Contrato[] contratos, int size, int i) {
        int l = (2 * i) + 1;
        int r = (2 * i) + 2;

        int maior = i;

        if (l < size && contratos[l].getValor() < contratos[maior].getValor()) {
            maior = l;
        }

        if (r < size && contratos[r].getValor() < contratos[maior].getValor()) {
            maior = r;
        }

        if (maior != i) {

            Contrato tmp = contratos[i];
            contratos[i] = contratos[maior];
            contratos[maior] = tmp;

            buildMinHeap(contratos, size, maior);
        }
    }

    public void mergeble(float[][] contratosArray, float[] result) {
        int size = contratosArray.length;

        if (size != 1) {

            float[] arr1 = contratosArray[0];

            float[] arr2 = contratosArray[size - 1];

            float[][] newArray = new float[size - 1][];

            newArray[0] = this.mergeArray(arr1, arr2);

            for (int i = 1; i < size - 1; i++) {
                newArray[i] = contratosArray[i];
            }

            mergeble(newArray, result);
        } else {
            for (int i = 0; i < result.length; i++) {
                result[i] = contratosArray[0][i];
            }
        }
    }

    public void printMatriz() {

        int max = fornecedores[0][0].length;
        for (int j = 0; j < mesesCount; j++) {
            int count = fornecedores[0][j].length;

            System.out.print("[ ");

            for (int i = 0; i < max - count; i++) {
                System.out.print("__________| ");
            }

            for (int i = 0; i < count; i++) {

                System.out.printf("%10.1f| ", fornecedores[0][j][i]);
            }

            System.out.println(" ]");
        }
    }

    static void imprimeHeap(Contrato[] A) {
        int h = (int) (Math.log(A.length) / Math.log(2));

        int espacos = calculaEspacos(h);

        for (int i = 0; i <= h; i++) {
            for (int j = 1; j <= Math.pow(2, i); j++) {
                if ((int) (Math.pow(2, i)) - 1 + (j - 1) >= A.length) break;
                imprimeEspacos(espacos);
                System.out.printf("%.0f", A[(int) (Math.pow(2, i)) - 1 + (j - 1)].getValor());
                imprimeEspacos(espacos);
                if (j < Math.pow(2, i)) {
                    System.out.printf("%3s", "");
                }
            }
            espacos = (espacos - 3) / 2;
            System.out.println();
        }
    }

    static void imprimeHeap(float[] A) {
        int h = (int) (Math.log(A.length) / Math.log(2));

        int espacos = calculaEspacos(h);

        for (int i = 0; i <= h; i++) {
            for (int j = 1; j <= Math.pow(2, i); j++) {
                if ((int) (Math.pow(2, i)) - 1 + (j - 1) >= A.length) break;
                imprimeEspacos(espacos);
                System.out.printf("%.0f", A[(int) (Math.pow(2, i)) - 1 + (j - 1)]);
                imprimeEspacos(espacos);
                if (j < Math.pow(2, i)) {
                    System.out.printf("%3s", "");
                }
            }
            espacos = (espacos - 3) / 2;
            System.out.println();
        }
    }

    static int calculaEspacos(int h) {
        int espacos = 3;
        for (int i = 1; i <= h; i++) {
            espacos = 2 * espacos + 3;
        }
        return espacos;
    }

    static void imprimeEspacos(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(" ");
        }
    }

    static void imprimeVetor(Contrato[] A) {
        for (Contrato contrato : A) {
            System.out.printf("%6.0f", contrato.getValor());
        }
        System.out.print("\n\n\n");
    }
}
