package projetoEliminaçãoDeGaos;
import java.util.Scanner;

public class EliminacaoDeGaos 
{
    public static void main(String[] args) {
        //receber do usuario número de equações
        Scanner scanner = new Scanner(System.in);
        System.out.println("digite o numero de variaveis \n");
        int numeroDeEquacoes = scanner.nextInt();

        double[][] matriz = new double[numeroDeEquacoes][numeroDeEquacoes+1];
        
        //pedir para o usuario informar cada valor de sistema de equações
        for(int i=0;i < numeroDeEquacoes; i++)
        {
            System.out.println("digite o valor referente a linha " + i + 1 + " : ");
            for(int j=0;j <= numeroDeEquacoes ; j++)
            {
                matriz[i][j] = scanner.nextDouble();
            }
        }
        
        resolverEquaçãoDeGaos(matriz, numeroDeEquacoes);

        double[] solucoes = substituicaoRegressiva(matriz, numeroDeEquacoes);

        // Exibir a solução
        System.out.println("Soluções do sistema:");
        for (int i = 0; i < numeroDeEquacoes; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, solucoes[i]);
        }

        scanner.close();
    }

    public static void resolverEquaçãoDeGaos(double[][] matriz, int numeroDeEquacoes)
    {
        for (int i = 0; i < numeroDeEquacoes; i++) {
            // Pivoteamento parcial: encontrar o maior elemento na coluna
            int maxLinha = i;
            for (int k = i + 1; k < numeroDeEquacoes; k++) {
                if (Math.abs(matriz[k][i]) > Math.abs(matriz[maxLinha][i])) {
                    maxLinha = k;
                }
            }
            // Trocar a linha atual pela linha do maior pivô
            double[] temp = matriz[i];
            matriz[i] = matriz[maxLinha];
            matriz[maxLinha] = temp;

            // Normalizar a linha do pivô
            double pivot = matriz[i][i];
            if (pivot == 0) {
                System.out.println("Sistema sem solução única.");
                return;
            }
            for (int j = i; j <= numeroDeEquacoes; j++) {
                matriz[i][j] /= pivot;
            }

            // Eliminar os elementos abaixo do pivô
            for (int k = i + 1; k < numeroDeEquacoes; k++) {
                double fator = matriz[k][i];
                for (int j = i; j <= numeroDeEquacoes; j++) {
                    matriz[k][j] -= fator * matriz[i][j];
                }
            }
        }
    }
    public static double[] substituicaoRegressiva(double[][] matriz, int numeroDeEquacoes) {
        double[] solucoes = new double[numeroDeEquacoes];

        // Resolver de trás para frente
        for (int i = numeroDeEquacoes - 1; i >= 0; i--) {
            double soma = 0;
            for (int j = i + 1; j < numeroDeEquacoes; j++) {
                soma += matriz[i][j] * solucoes[j];
            }
            solucoes[i] = matriz[i][numeroDeEquacoes] - soma;
        }

        return solucoes;
    }
}
