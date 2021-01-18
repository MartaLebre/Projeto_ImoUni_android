package amsi.dei.estg.ipleiria.imouni.modelo;

public class Anuncio {
    private int id, id_proprietario, idcasa, preco, despesas_inc;
    private String titulo, data_criacao, data_disponibilidade, descricao;
    //o atributo autoIncrementedId é static: comum a todas as instâncias/objetos da classe
    //private static int autoIncrementedId = 1;

    public Anuncio(int id, int id_proprietario, int idcasa, String titulo, int preco, String data_criacao, String data_disponibilidade, int despesas_inc, String descricao) {
        this.id = id;
        this.id_proprietario = id_proprietario;
        this.idcasa = idcasa;
        this.titulo = titulo;
        this.preco = preco;
        this.data_criacao = data_criacao;
        this.data_disponibilidade = data_disponibilidade;
        this.despesas_inc = despesas_inc;
        this.descricao = descricao;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_proprietario() {
        return id_proprietario;
    }

    public void setId_proprietario(int id_proprietario) {
        this.id_proprietario = id_proprietario;
    }

    public int getIdcasa() {
        return idcasa;
    }

    public void setIdcasa(int idcasa) {
        this.idcasa = idcasa;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }
    public String getData_disponibilidade() {
        return data_disponibilidade;
    }

    public void setData_disponibilidade(String data_disponibilidade) {
        this.data_disponibilidade = data_disponibilidade;
    }
    public int getDespesas_inc() {
        return despesas_inc;
    }

    public void setDespesas_inc(int despesas_inc) {
        this.despesas_inc = despesas_inc;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}