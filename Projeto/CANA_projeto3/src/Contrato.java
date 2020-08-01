import java.util.ArrayList;
import java.util.List;

public class Contrato {
    private int fornecedor;
    private int mesInicio;
    private int mesFinal;
    private float valor;

    public Contrato(String[] line) {
        this.fornecedor = Integer.parseInt(line[0]);
        this.mesInicio = Integer.parseInt(line[1]);
        this.mesFinal = Integer.parseInt(line[2]);
        this.valor = Float.parseFloat(line[3]);
    }


    public Contrato(float valor) {
        this.valor = valor;
    }

    public Contrato(int fornecedor, int mesInicio, int mesFinal, float valor) {
        this.fornecedor = fornecedor;
        this.mesInicio = mesInicio;
        this.mesFinal = mesFinal;
        this.valor = valor;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public int getMesFinal() {
        return mesFinal;
    }

    public float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Contrato {\n" +
                "    fornecedor = " + fornecedor +
                "\n    mesInicio = " + mesInicio +
                "\n    mesFinal = " + mesFinal +
                "\n    valor = " + valor +
                "\n}";
    }
}
