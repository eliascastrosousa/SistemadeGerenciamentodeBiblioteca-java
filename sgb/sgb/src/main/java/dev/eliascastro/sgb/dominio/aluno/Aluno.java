package dev.eliascastro.sgb.dominio.aluno;


import dev.eliascastro.sgb.dominio.endereco.Endereco;

public class Aluno {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private boolean ativo;
    private Integer limiteLivros;
    private Double multa;

    public Aluno() {
    }

    public Aluno( String nome, String email, String cpf, String telefone, Endereco endereco, boolean ativo, Integer limiteLivros, Double multa) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = ativo;
        this.limiteLivros = limiteLivros;
        this.multa = multa;
    }

    public static class Builder {

        private Aluno aluno;

        public Builder() {
            aluno = new Aluno();
        }

        public Builder comNome(String nome){
            aluno.nome = nome;
            return this;
        }

        public Builder comCpf(String cpf){
            aluno.cpf = cpf;
            return this;
        }

        public Builder comEmail(String email){
            aluno.email = email;
            return this;
        }

        public Builder comTelfone(String telefone){
            aluno.telefone = telefone;
            return this;
        }

        public Builder comEndereco(String cep, String logradouro, String numero, String complemento){
            Endereco endereco = new Endereco(logradouro, cep, numero, complemento);
            aluno.endereco = endereco;
            return this;
        }

        public Builder comAtivo(Boolean ativo){
            aluno.ativo = ativo;
            return this;
        }

        public Builder comLimiteLivros(Integer limiteLivros){
            aluno.limiteLivros = limiteLivros;
            return this;
        }

        public Builder comMulta(Double multa){
            aluno.multa = multa;
            return this;
        }

        public Aluno Build(){
            return aluno;
        }

    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Integer getLimiteLivros() {
        return limiteLivros;
    }

    public Double getMulta() {
        return multa;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco=" + endereco +
                ", ativo=" + ativo +
                ", limiteLivros=" + limiteLivros +
                ", multa=" + multa +
                '}';
    }
}
