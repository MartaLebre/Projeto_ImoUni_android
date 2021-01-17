package amsi.dei.estg.ipleiria.imouni.modelo;

import java.util.Date;

public class Anuncio{
    private int id;
    private int id_proprietario;
    private int id_casa;
    private String titulo;
    private int preco;
    private String data_criacao;
    private String data_disponiblidade;
    private int despesas_inc;
    private String descricao;

    public Anuncio(int id, int id_proprietario, int id_casa, String titulo, int preco, String data_criacao, String data_disponiblidade, int despesas_inc, String descricao){
        this.id = id;
        this.id_proprietario = id_proprietario;
        this.id_casa = id_casa;
        this.titulo = titulo;
        this.preco = preco;
        this.data_criacao = data_criacao;
        this.data_disponiblidade = data_disponiblidade;
        this.despesas_inc = despesas_inc;
        this.descricao = descricao;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId_proprietario(){
        return id_proprietario;
    }

    public void setId_proprietario(int id_proprietario){
        this.id_proprietario = id_proprietario;
    }

    public int getId_casa(){
        return id_casa;
    }

    public void setId_casa(int id_casa){
        this.id_casa = id_casa;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public int getPreco(){
        return preco;
    }

    public void setPreco(int preco){
        this.preco = preco;
    }

    public String getData_criacao(){
        return data_criacao;
    }

    public void setData_criacao(String data_criacao){
        this.data_criacao = data_criacao;
    }

    public String getData_disponiblidade(){
        return data_disponiblidade;
    }

    public void setData_disponiblidade(String data_disponiblidade){
        this.data_disponiblidade = data_disponiblidade;
    }

    public int getDespesas_inc(){
        return despesas_inc;
    }

    public void setDespesas_inc(int despesas_inc){
        this.despesas_inc = despesas_inc;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
