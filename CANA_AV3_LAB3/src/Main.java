import java.util.Random;

public class Main {

    static class Atividade {
        public int s;
        public int f;

        public Atividade(int s, int f) {
            this.s = s;
            this.f = f;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        Atividade[] atividades = new Atividade[n + 2];
        Random random = new Random();
        int s;
        int f = 2;

        atividades[0] = new Atividade(0, 0);

        for (int i = 1; i <= n; i++) {
            f = random.nextInt(3) + f + 1;
            s = random.nextInt(f - 1) + 1;
            atividades[i] = new Atividade(s, f);
        }

        atividades[n + 1] = new Atividade(Integer.MAX_VALUE, Integer.MAX_VALUE);
        imprimeAtividades(atividades);

        System.out.println("Guloso");
        selecaoGuloso(atividades);

        System.out.println("Dinamico");
        selecaoDinamico(atividades);

    }

    static void selecaoGuloso(Atividade[] a) {
        int n = a.length - 2;
        System.out.print("a1");
        int count = 1;
        int i = 1;

        for (int j = 2; j <= n; j++) {
            if (a[j].s >= a[i].f) {
                System.out.print(" a" + j);
                count++;
                i = j;
            }
        }

        System.out.println();
    }

    static void selecaoDinamico(Atividade[] a) {
        int[][] c = new int[a.length][a.length];
        int[][] s = new int[a.length][a.length];

        int n = a.length;

        for (int l = 3; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                c[i][j] = 0;
                s[i][j] = 0;

                for (int k = i + 1; k < j; k++) {
                    if (a[k].s >= a[i].f && a[k].f <= a[j].s) {
                        int q = c[i][k] + c[k][j] + 1;
                        if (q > c[i][j]) {
                            c[i][j] = q;
                            s[i][j] = k;
                        }
                    }
                }
            }
        }

        impressaoDinamico(s, 0, n - 1);
        System.out.println();
    }

    static void impressaoDinamico(int[][] s, int i, int j) {
        if (s[i][j] != 0) {
            System.out.print(" a" + s[i][j]);
            impressaoDinamico(s, s[i][j], j);
        }

        System.out.println();
    }

    static void imprimeAtividades(Atividade[] a) {
        System.out.println("Atividades");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("a%d\t%12d\t%12d\n", i, a[i].s, a[i].f);
        }
        System.out.println();
    }

}
