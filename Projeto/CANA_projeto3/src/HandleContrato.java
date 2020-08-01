public class HandleContrato {
    private float[][][] fornecedores;
    private float[][] valorContratos;
    private int[][] auxFornecedores;
    private float[][] menorValor;
    private int[][] menorFornecedor;
    private float taxa;
    private int mesesCount;
    private int fornecedoresCount;
    private int contratosCountPerFornecedor;

    public HandleContrato(float[][][] fornecedores, int mesesCount, int fornecedoresCount, int contratosCountPerFornecedor, float taxa) {
        this.fornecedores = fornecedores;
        this.mesesCount = mesesCount;
        this.fornecedoresCount = fornecedoresCount;
        this.contratosCountPerFornecedor = contratosCountPerFornecedor;
        this.valorContratos = new float[mesesCount + 1][mesesCount + 1];
        this.auxFornecedores = new int[mesesCount + 1][mesesCount + 1];

        this.menorValor = new float[mesesCount + 1][mesesCount + 1];
        this.menorFornecedor = new int[mesesCount + 1][mesesCount + 1];
        this.taxa = taxa;


        for (int i = 0; i < mesesCount + 1; i++) {
            for (int j = 0; j < mesesCount + 1; j++) {
                this.auxFornecedores[i][j] = -1;
                this.menorFornecedor[i][j] = -1;
            }
        }

        this.fillValorContratos();
        this.fillMenorValor();

//        this.printTaxas(this.valorContratos);
//        System.out.println("----------------");
        this.printTaxas(this.menorValor);
//        this.printFornecedor(this.menorFornecedor);
        this.dijkstra(0, this.mesesCount);

//         long tempoInicial = System.currentTimeMillis();
//        float a = this.guloso(0, this.mesesCount);
//         long tempoFinal = System.currentTimeMillis();
//         System.out.println("guloso: " + (tempoFinal - tempoInicial) / 1000f + " segundos");
        
//        System.out.println("total: " + a);
//        this.printMatriz();
//        this.contratoDinamico(this.menorValor, this.menorFornecedor, this.mesesCount + 1);
    }

    public void fillValorContratos() {
        for (int i = 0; i < this.mesesCount; i++) {
            for (int j = i; j < this.mesesCount; j++) {
                if (i != 0) {
                    float[] menorValorFornecedor = menorValorMesByFornecedor(i, j - i);
                    this.auxFornecedores[i][j + 1] = (int) menorValorFornecedor[0];
                    this.valorContratos[i][j + 1] = menorValorFornecedor[1] + this.taxa;

                } else {
                    float[] menorValorFornecedor = menorValorMesByFornecedor(i, j - i);
                    this.auxFornecedores[i][j + 1] = (int) menorValorFornecedor[0];
                    this.valorContratos[i][j + 1] = menorValorFornecedor[1];
                }
            }
        }
    }

    public void fillMenorValor() {
        for (int i = 0; i < this.mesesCount; i++) {
            for (int j = i; j < this.mesesCount; j++) {
                if (i != 0) {
                    float[] menorValorFornecedor = menorValorMesByFornecedor(i, j - i);
                    this.menorFornecedor[i + 1][j + 1] = (int) menorValorFornecedor[0] + 1;
                    this.menorValor[i + 1][j + 1] = menorValorFornecedor[1] + this.taxa;

                } else {
                    float[] menorValorFornecedor = menorValorMesByFornecedor(i, j - i);
                    this.menorFornecedor[i + 1][j + 1] = (int) menorValorFornecedor[0] + 1;
                    this.menorValor[i + 1][j + 1] = menorValorFornecedor[1];
                }
            }
        }
    }

    public float[] menorValorMesByFornecedor(int begin, int end) {
        float menor = this.fornecedores[0][begin][end];
        int indexFornecedor = 0;

        for (int i = 1; i < this.fornecedoresCount; i++) {
            if (menor > this.fornecedores[i][begin][end]) {
                menor = this.fornecedores[i][begin][end];
                indexFornecedor = i;
            }
        }

//        return menor;
        return new float[]{indexFornecedor, menor};
    }

    void printSolution(int[] pred) {

        int i = pred.length - 1;
        System.out.println("Melhor Caminhos: ");
        while (i > 0) {
            System.out.println(pred[i] + " --> " + i);
            i = pred[i];
        }
    }

    public int minDistance(float[] dist, Boolean[] sptSet) {
        float min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < mesesCount + 1; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    public void dijkstra(int src, int n) {
        long tempoInicial = System.currentTimeMillis();

        float[] dist = new float[n + 1];
        Boolean[] sptSet = new Boolean[n + 1];
        int[] pred = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < n; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < n + 1; v++) {
                if (!sptSet[v] && this.valorContratos[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + this.valorContratos[u][v] < dist[v]) {
                    dist[v] = dist[u] + this.valorContratos[u][v];
                    pred[v] = u;
                }
            }
        }

//        System.out.println("---" + dist[n]);

        long tempoFinal = System.currentTimeMillis();

        System.out.println("dijkstra: " + (tempoFinal - tempoInicial) / 1000f + " segundos");

        printSolution(pred);
    }

    public float guloso(int begin, int end) {

        float qMin = this.valorContratos[begin][end];
        int kMin = -1;

        for (int i = begin + 1; i < end; i++) {
            if (qMin > this.valorContratos[begin][i] + this.valorContratos[i][end]) {
                kMin = i;
                qMin = this.valorContratos[begin][i] + this.valorContratos[i][end];
            }
        }

        if (kMin == -1) {
            System.out.println(qMin);
            return qMin;
        }

//        System.out.println("kmin: " + kMin);
        float qEsq = guloso(begin, kMin);
        float qDir = guloso(kMin, end);

//        System.out.println("qEsq: " + qEsq);
//        System.out.println("qDir: " + qDir);
        return qEsq + qDir;
    }

    public void contratoDinamico(float[][] contratos, int[][] fornecedores, int fim) {
        long tempoInicial = System.currentTimeMillis();

        float m[][] = new float[fim][fim];
        int s[][] = new int[fim][fim];

        for (int l = 1; l < fim; l++) {
            for (int i = 1; i <= fim - l; i++) {
                int j = i + l - 1;
                m[i][j] = contratos[i][j];
                s[i][j] = j;

                for (int k = i; k < j; k++) {

                    float q = m[i][k] + m[k + 1][j];
//                    System.out.println("[" + i + "]" + "[" + k + "] ----" + "---- [" + (k + 1) + "]" + "[" + j + "]");
//                    System.out.println("Q: " + q);
//                    System.out.println("M: " + m[i][j]);
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        imprime(s, contratos, fornecedores, 1, fim - 1);
        System.out.printf("Total: %1.1f\n", m[1][fim - 1]);

        long tempoFinal = System.currentTimeMillis();
        System.out.println("Dinamico: " + (tempoFinal - tempoInicial) / 1000f + " segundos");
    }

    static void imprime(int[][] s, float[][] v, int[][] f, int i, int j) {
        if (s[i][j] == j) {
            System.out.printf("%d %d %d %1.1f\n", f[i][j], i, j, v[i][j]);
        } else {
            imprime(s, v, f, i, s[i][j]);
            imprime(s, v, f, s[i][j] + 1, j);
        }
    }

    public void printTaxas(float[][] valores) {
        for (float[] valorContrato : valores) {
            for (int j = 0; j < this.valorContratos.length; j++) {
                if (valorContrato[j] == 0f) {
                    System.out.printf("[%8.1s] ", "X");
                } else {
                    System.out.printf("[%8.1f] ", valorContrato[j]);
                }
            }
            System.out.println("");
        }
    }

    public void printFornecedor(int[][] fornecedores) {
        for (int[] index : fornecedores) {
            for (int j = 0; j < fornecedores.length; j++) {
                if (index[j] == -1) {
                    System.out.printf("[%5.1s] ", "X");
                } else {
                    System.out.printf("[%5d] ", index[j]);
                }
            }
            System.out.println("");
        }
    }

    public void printMatriz() {

        int max = fornecedores[0][0].length;
        for (int j = 0; j < mesesCount; j++) {
            int count = fornecedores[0][j].length;

            for (int i = 0; i < max - count; i++) {
                System.out.print("[_____] ");
            }

            for (int i = 0; i < count; i++) {

                System.out.printf("[%5.1f] ", fornecedores[0][j][i]);
            }

            System.out.println("");
        }
    }

}
