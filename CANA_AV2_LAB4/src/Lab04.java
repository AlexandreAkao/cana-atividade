import java.util.Random;

public class Lab04 {
    static class Aluno {
        public String matricula;
        public String nome;
        public int creditos;

        public Aluno() {
            Random randomGenerator = new Random();
            matricula = "";
            for (int i = 1; i <= 8; i++) {
                matricula += (char) ('0' + randomGenerator.nextInt(10));
            }
            nome = "";
            nome += (char)('A' + randomGenerator.nextInt(26));
            for (int i = 2; i <= 12; i++) {
                nome += (char) ('a' + randomGenerator.nextInt(26));
            }
            creditos = randomGenerator.nextInt(161);
        }
    }

    public static void main(String[] args) {
        int n = 40;
        Aluno[] alunos = new Aluno[n];
        for (int i = 0; i < alunos.length; i++) {
            alunos[i] = new Aluno();
        }
        imprime(alunos);
//        alunos = ordenaCreditos(alunos);
//        imprime(alunos);
//        alunos = ordenaMatricula(alunos);
//        imprime(alunos);
        alunos = ordenaNome(alunos);
        imprime(alunos);
    }

    static Aluno[] ordenaCreditos (Aluno[] A) {
        Aluno[] B = new Aluno[A.length];

        int[] C = new int[161];

        for (int i = 0; i < A.length; i++) {
            C[A[i].creditos] += 1;
        }

        for (int i = 1; i < 161; i++) {
            C[i] += C[i - 1];
        }

        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i].creditos] - 1] = A[i];
            C[A[i].creditos] -= 1;
        }

        return B;
    }

    static Aluno[] ordenaMatricula (Aluno[] A) {
        Aluno[] B = null;

        for (int j = 7; j >= 0; j--) {
            B = new Aluno[A.length];

            int[] C = new int[10];

            for (int i = 0; i < A.length; i++) {
                C[(int) A[i].matricula.charAt(j) - '0'] += 1;
            }

            for (int i = 1; i < 10; i++) {
                C[i] += C[i - 1];
            }

            for (int i = A.length - 1; i >= 0; i--) {
                B[C[((int) A[i].matricula.charAt(j) - '0')] - 1] = A[i];
                C[(int) A[i].matricula.charAt(j) - '0'] -= 1;
            }

            A = B;
        }

        return B;
    }

    static Aluno[] ordenaNome (Aluno[] A) {
        Aluno[] B = null;

        for (int j = 11; j >= 0; j--) {
            B = new Aluno[A.length];

            int[] C = new int[26];

            for (int i = 0; i < A.length; i++) {
                C[(int) (A[i].nome).toLowerCase().charAt(j) - 'a'] += 1;
            }

            for (int i = 1; i < 26; i++) {
                C[i] += C[i - 1];
            }

            for (int i = A.length - 1; i >= 0; i--) {
                B[C[((int) (A[i].nome).toLowerCase().charAt(j) - 'a')] - 1] = A[i];
                C[(int) (A[i].nome).toLowerCase().charAt(j) - 'a'] -= 1;
            }

            A = B;
        }

        return B;
    }

    static void imprime (Aluno[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%-15s%-15s%10d\n", A[i].matricula, A[i].nome, A[i].creditos);
        }
        System.out.print("\n\n\n");
    }
}
